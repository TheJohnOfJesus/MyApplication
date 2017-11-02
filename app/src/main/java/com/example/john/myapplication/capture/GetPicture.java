package com.example.john.myapplication.capture;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.example.john.myapplication.MainActivity;
import com.example.john.myapplication.manager.PermissionManager;
import com.example.john.myapplication.utils.BitmapUtil;
import com.example.john.myapplication.utils.FilePathUtil;
import com.example.john.myapplication.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by John on 2017/7/26.
 */

public class GetPicture {
    private static final String TAG = BitmapUtil.class.getSimpleName();
    private static File tempFile;
    private static final int REQUEST_CODE_900 = 900;
    private static final int REQUEST_CODE_901 = 901;
    private static final int REQUEST_CODE_902 = 902;
    private static int outputX, outputY, maxSize;
    private static int callBack;
    private static Uri fileUri;

    /**
     * 给lua用的获取图片的方法,返回PNG图片的路径
     *
     * @param type      0-拍照  1-相册选择
     * @param pixelW    想要的图片宽度
     * @param pixelH    想要的图片高度
     * @param maxSizeP  想要的图片最大质量(kb)[返回的图片可能会大于maxSizeP,比如PNG格式,无损类图片在压缩时,即使压缩到原图的最低值,该类图片仍然会报纸无损质量的最小值,最小值可能会大于maxSizeP]
     * @param callBackP 回调的id
     */
    public static void getPic(final int type, final int pixelW, final int pixelH, final int
            maxSizeP, final int callBackP) {
        outputX = pixelW;
        outputY = pixelH;
        maxSize = maxSizeP;
        callBack = callBackP;
        if (type == 0) {
            doCaptureImage();//拍照
        } else {
            doSelectImage();//相册选择
        }
    }

    public static void onActivityResullt(int requestCode, int resusltCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_900:
                try {
                    fileUri = data.getData();
                    tempFile = new File(FilePathUtil.getUserImagePath(MainActivity.instance),
                            System.currentTimeMillis() + ".png");
                    FileUtil.saveUriToFile(MainActivity.instance, fileUri, tempFile);
//                    fileUri = FileUtil.getUri(MainActivity.instance, tempFile);//android 7.0处理方式
//                    FileUtil.cropPicture(MainActivity.instance, fileUri, REQUEST_CODE_902,
//                            outputX, outputY);
                    FileUtil.cropPicture(MainActivity.instance, tempFile, REQUEST_CODE_902,
                            outputX, outputY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case REQUEST_CODE_901:
                try {
                    FileUtil.cropPicture(MainActivity.instance, tempFile, REQUEST_CODE_902,
                            outputX, outputY);
                } catch (Exception e) {
//                    toast("未选择文件");
                }
                break;
            case REQUEST_CODE_902:
                try {
                    Bundle extras = data.getExtras();
//                    if (tempFile != null) tempFile.delete();
//                    if (extras != null) {
                    if (extras != null&&tempFile != null) {
                        fileUri = FileProvider.getUriForFile(MainActivity.instance, "com.example" +
                                ".jjtx_dev106.myapplication.fileprovider", tempFile);//android7.0处理方式
                        Bitmap bitmap = BitmapFactory.decodeStream(MainActivity.instance
                                .getContentResolver().openInputStream(fileUri));
                        tempFile.delete();
                        String path = FilePathUtil.getUserImagePath(MainActivity.instance);
                        String name = System.currentTimeMillis() + ".png";
                        tempFile = new File(path, name);
                        if (!tempFile.exists()) {
                            tempFile.createNewFile();
                        }
//                        FileUtil.saveBitmapToFile((Bitmap) data.getExtras().getParcelable("data")
//                                , tempFile, maxSize);
                        FileUtil.saveBitmapToFile(bitmap, tempFile,maxSize);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                if (tempFile != null) tempFile.delete();
//                try {
//                    String path = FilePathUtil.getUserImagePath(MainActivity.instance);
//                    String name = System.currentTimeMillis() + ".jpg";
//                    tempFile = new File(path, name);
//                    if (!tempFile.exists()){
//                        tempFile.createNewFile();
//                    }
//                    FileUtil.saveBitmapToFile((Bitmap) data.getExtras().getParcelable("data"),
//                            tempFile, maxSize);
////                    Cocos2dxLuaJavaBridge.callLuaFunctionWithString(callBack, path + File
////                            .separator + name);
////                    Cocos2dxLuaJavaBridge.releaseLuaFunction(callBack);
//                    tempFile = null;
//                } catch (Exception e) {
//                    Log.i(TAG, "onActivityResullt: REQUEST_CODE_902: Exception:" + e.toString());
//                }
                break;
        }
    }

    private static void doSelectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        try {
            MainActivity.instance.startActivityForResult(Intent.createChooser(intent,
                    "请选择一个要上传的文件"), REQUEST_CODE_900);
        } catch (Exception e) {
            Log.i(TAG, "doSelectImage--->Exception:" + e.toString());
        }
    }


    private static void doCaptureImage() {
        PermissionManager.getInstance().grantPermisson(MainActivity.instance, new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission
                        .CAMERA},
                new PermissionManager.OnPermissinResultListener() {
                    @Override
                    public void onGrantSuccess(String msg) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (Environment.getExternalStorageState().equals(Environment
                                .MEDIA_MOUNTED)) {
                            tempFile = new File(FilePathUtil.getUserImagePath(MainActivity
                                    .instance), System
                                    .currentTimeMillis() + ".png");
//            Uri uri = Uri.fromFile(tempFile);
                            fileUri = FileUtil.getUri(MainActivity.instance, tempFile);//android
                            // 7.0处理方式
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                            MainActivity.instance.startActivityForResult(intent, REQUEST_CODE_901);
                        } else {
                            Toast.makeText(MainActivity.instance, "未找到存储卡,无法储存照片", Toast
                                    .LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onGrantFailure(boolean showAlert, String[] permission) {

                    }

                    @Override
                    public void onInvalidPermission(ArrayList<String> permission) {
                        new AlertDialog.Builder(MainActivity.instance)
                                .setTitle("权限提示")
                                .setMessage("拍照需要拍照权限和存储权限，点击确定去设置相应权限")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent localIntent = new Intent();
                                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        if (Build.VERSION.SDK_INT >= 9) {
                                            localIntent.setAction("android.settings" +
                                                    ".APPLICATION_DETAILS_SETTINGS");
                                            localIntent.setData(Uri.fromParts("package",
                                                    MainActivity
                                                    .instance.getPackageName(), null));
                                        } else if (Build.VERSION.SDK_INT <= 8) {
                                            localIntent.setAction(Intent.ACTION_VIEW);
                                            localIntent.setClassName("com.android.settings", "com" +
                                                    ".android" +
                                                    ".settings.InstalledAppDetails");
                                            localIntent.putExtra("com.android.settings" +
                                                    ".ApplicationPkgName", MainActivity.instance
                                                    .getPackageName());
                                        }
                                        MainActivity.instance.startActivity(localIntent);
                                    }
                                })
                                .setNegativeButton("", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .create()
                                .show();
                    }
                });
    }
}
