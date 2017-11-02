package com.example.john.myapplication.manager;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by John on 2017/3/15.
 */

public class PermissionManager {
    public static boolean PERMISSION_READ_PHONE_STATE = false;
    private static PermissionManager manager;
    private int REQUEST_PERMISSION = 1010;
    private OnPermissinResultListener listener;
    //    private SparseIntArray requestArray=new SparseIntArray();
    private ArrayList<String> permissionArray = new ArrayList<String>();

    public static PermissionManager getInstance() {
        if (manager == null) {
            manager = new PermissionManager();
        }
        return manager;
    }
    public void grantPermisson(Activity context, String permissions[], OnPermissinResultListener listener) {
        int index = 1;
        int permissionCount = 0;
        if (Build.VERSION.SDK_INT >= 23) {
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {//没有给该的权限
                    if (ActivityCompat.shouldShowRequestPermissionRationale(context, permissions[i])) {//是否需要ui展示提示用户选择权限
                        permissionCount++;
                    } else {//已经请求过，但是被拒绝了
                        permissionArray.add(permissions[i]);
                    }
                } else {
                    permissions[i] = "";
                }
            }
            String[] tempPermissions = new String[permissionCount];
            permissionCount = 0;
            for (int i = 0; i < permissions.length; i++) {
                if (!"".equals(permissions[i])) {
                    if (permissionCount >= tempPermissions.length) break;
                    tempPermissions[permissionCount++] = permissions[i];
                }
            }
            if (tempPermissions != null && tempPermissions.length > 0) {
                ActivityCompat.requestPermissions(context, tempPermissions, REQUEST_PERMISSION);
                this.listener = listener;
            } else {
                this.listener = null;
            }
            if (permissionArray.size() > 0) {
                listener.onInvalidPermission(permissionArray);
            }
            if (permissionArray.size() == 0 &&permissionCount==0){
                listener.onGrantSuccess("");
            }
        } else {
            listener.onGrantSuccess("version<23" + "");
        }
    }

    /**
     * 在activity或fragment的onActivityResult使用，不添加该方法，OnPermissinResultListener无回掉
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        for (int i=0;i<requestArray.size();i++){
//            int code=REQUEST_PERMISSION+i;
//            if (requestCode==code&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                listener.onGrantSuccess(permissions[0]);
//            }
//        }
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (listener != null) listener.onGrantSuccess(requestCode + "");
            } else {
                if (listener != null) listener.onGrantFailure(true, permissions);
            }
        }
//        else {
//            if (listener != null) listener.onGrantFailure(true, permissions);
//        }
    }

    public interface OnPermissinResultListener {
        void onGrantSuccess(String msg);

        void onGrantFailure(boolean showAlert, String[] permission);

        void onInvalidPermission(ArrayList<String> permission);
    }
}
