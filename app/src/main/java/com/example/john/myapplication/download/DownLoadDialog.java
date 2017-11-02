//package com.example.jjtx_dev106.myapplication.download;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.jjtx_dev106.myapplication.R;
//
///**
// * Created by jjtx-dev106 on 2017/9/15.
// */
//
//public class DownLoadDialog extends Dialog implements View.OnClickListener {
//    private TextView title, content, cancle, confirm;
//    private ProgressBar progress;
//    private boolean updating=false;
//    private String url;
//    private boolean isFirst=false;
//
//    public DownLoadDialog(Context context,String url) {
//        super(context);
////        super(context, R.style.ShareDialog);
//        this.url=url;
//        setCancelable(false);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dialog_download);
//        title = (TextView) findViewById(R.id.dialog_download_title);
//        content = (TextView) findViewById(R.id.dialog_download_content);
//        cancle = (TextView) findViewById(R.id.dialog_download_cancle);
//        confirm = (TextView) findViewById(R.id.dialog_download_confirm);
//        progress = (ProgressBar) findViewById(R.id.dialog_download_progress);
//        confirm.setOnClickListener(this);
//        cancle.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.dialog_download_confirm:
//                if (!isFirst){
//                    isFirst=true;
//                    content.setVisibility(View.GONE);
//
//                }
//                if (updating){//正在更新中-->暂停更新
//                    DownloadManager.getInstance().cancel(url);
//                    confirm.setText("暂停");
//
//                }else{//暂停或未更新状态-->开始更新更新
//                    DownloadManager.getInstance().download(url, new DownLoadObserver() {
//                        @Override
//                        public void onNext(DownloadInfo value) {
//                            super.onNext(value);
//                            if (progress.getMax()==0)progress.setMax((int) value.getTotal());
//                            progress.setProgress((int) value.getProgress());
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            if (downloadInfo != null) {
//                                Toast.makeText(getContext(), downloadInfo.getFileName() + "-DownloadComplete", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    confirm.setText("更新");
//                }
//                updating=!updating;
//
//                break;
//            case R.id.dialog_download_cancle:
//                DownloadManager.getInstance().cancel(url);
//                confirm.setText("暂停");
//                dismiss();
//                break;
//        }
//    }
//}
