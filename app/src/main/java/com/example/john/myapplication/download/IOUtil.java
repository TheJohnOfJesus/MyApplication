//package com.example.jjtx_dev106.myapplication.download;
//
//import java.io.Closeable;
//import java.io.IOException;
//
///**
// * Created by John on 2017/9/13.
// */
//
//public class IOUtil {
//    public static void closeAll(Closeable... closeables){
//        if(closeables == null){
//            return;
//        }
//        for (Closeable closeable : closeables) {
//            if(closeable!=null){
//                try {
//                    closeable.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
