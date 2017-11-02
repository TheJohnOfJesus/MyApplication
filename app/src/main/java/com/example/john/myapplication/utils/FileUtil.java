package com.example.john.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static Uri getUri(Context context, File file) {
        return FileProvider.getUriForFile(context, "com.example.jjtx_dev106.myapplication" +
                ".fileprovider", file);//android 7.0处理方式
    }

    public static void openFile(File paramFile, Activity paramActivity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(268435456);
        localIntent.setAction("android.intent.action.VIEW");
        String str = getMIMEType(paramFile);
        localIntent.setDataAndType(Uri.fromFile(paramFile), str);
        try {
            paramActivity.startActivity(localIntent);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    public static void saveUriToFile(Context context, Uri uri, File file) {
        FileOutputStream fos = null;
        try {
//            Bitmap bitmap = BitmapFactory.decodeFile(getPath(context, uri));
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
            ByteArrayOutputStream osTemp = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, osTemp);
            fos = new FileOutputStream(file);
            fos.write(osTemp.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param bitmap
     * @param file
     * @param maxSize file的最大大小(M)
     */
    public static void saveBitmapToFile(Bitmap bitmap, File file, int maxSize) {
        try {
            if (bitmap==null||file==null||maxSize==0)return;
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            // scale
            int options = 100;
            // Store the bitmap into output stream(no compress)
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
            int oslength=os.toByteArray().length/1024;
            if (oslength>maxSize){
                float temp=oslength;
                while (temp!=1&&temp>maxSize){
                    temp= temp<=10?((temp-1)==0?1:temp-1):temp-10;
//                    temp-=10;
                }
                os.reset();
                options=(int)temp==0?1:(int)temp;//options只能是整数
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, os);
            }

            // Generate compressed image file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(os.toByteArray());
//            fos.write(osTemp.toByteArray());
            os.flush();
            os.close();
            fos.flush();
            fos.close();
            bitmap.recycle();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context
     * @param uri     本地的地址
     * @param maxSize M(单位)，服务器支持的最大文件为2M，此处建议1M
     */
    public static File compressFile(Context context, Uri uri, int maxSize) {
        if (maxSize <= 0) return null;
        return compressFile(new File(getPath(context, uri)), maxSize);
    }

    public static File compressFile(File file, int maxSize) {
        return file;
    }

    /**
     * 裁剪图片
     *
     * @param context
     * @param tempFile    要处理的图片
     * @param requestCode
     */
    public static void cropPicture(Activity context, File tempFile, int requestCode, int outputX,
                                   int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= 24) {//android7.0以上，该版本对file：//的URI做出了限制，禁止对外公开，所以这里做临时授权
            uri=getUri(context,tempFile);
            ((Context) context).grantUriPermission("com.android.camera", uri, Intent
                    .FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(tempFile);
        }
        cropPicture(context, intent,uri, requestCode, outputX, outputY);
    }

    public static void cropPicture(Activity context, Uri uri, int requestCode, int outputX, int
            outputY) {
        cropPicture(context, new Intent("com.android.camera.action.CROP"), uri, requestCode,
                outputX, outputY);
    }

    private static void cropPicture(Activity context, Intent intent, Uri uri, int requestCode,
                                    int outputX, int
                                            outputY) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", outputX);

        intent.putExtra("aspectY", outputY);

        intent.putExtra("outputX", outputX);

        intent.putExtra("outputY", outputY);

        intent.putExtra("scale", true);

//        intent.putExtra("return-data", true);//此处通过return-data获取bitmap数据进行处理

        intent.putExtra("scaleUpIfNeeded", true);//防止黑边

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//此处通过provider获取bitmap数据进行处理

        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());

        intent.putExtra("noFaceDetection", true); // no face detection

        context.startActivityForResult(intent, requestCode);
    }

    public static String getMIMEType(File paramFile) {
        String str1 = "";
        String str2 = paramFile.getName();
        String str3 = str2.substring(str2.lastIndexOf(".") + 1, str2.length()).toLowerCase();
        str1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str3);
        return str1;
    }

    public static String getPath(Context context, Uri uri) {
        String path = "";
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                String document_id = cursor.getString(0);
                document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
                cursor.close();
                try {
                    cursor = context.getContentResolver().query(android.provider.MediaStore
                            .Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID
                            + " = ? ", new String[]{document_id}, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media
                                .DATA));
                        cursor.close();
                    }
                } catch (Exception e) {
                    //
                }
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            path = uri.getPath();
        }
        return path;
    }
}
