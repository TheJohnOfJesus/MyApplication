package com.example.john.myapplication.download;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.john.myapplication.R;

public class DownLoadActivity extends Activity{

//    @BindView(R.id.main_progress1)
//    ProgressBar progress1;
//    @BindView(R.id.main_progress2)
//    ProgressBar progress2;
//    @BindView(R.id.main_progress3)
//    ProgressBar progress3;
    private String url1, url2, url3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
//        ButterKnife.bind(this);
        url1 = "http://www.jcodecraeer.com/plus/download.php?open=3&id=8153&uhash=0e5d7f4a6ad915432c933343";
        url2 = "http://www.jcodecraeer.com/plus/download.php?open=3&id=8510&uhash=62b47dc17243518365094bfd";
        url3 = "http://www.jcodecraeer.com/plus/download.php?open=3&id=8511&uhash=8f98644a2a0a4dee8e8c8d34";
        initView();
    }
    private void initView(){
        Button download= (Button) findViewById(R.id.main_btn_down3);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (dialog == null) dialog = new DownLoadDialog(DownLoadActivity.this, url1);
//                if (!dialog.isShowing()) dialog.show();
            }
        });
        Button cancle= (Button) findViewById(R.id.main_btn_cancel3);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DownloadManager.getInstance().cancel(url1);
            }
        });
    }

    private Dialog dialog;

//    @OnClick({R.id.main_btn_down1, R.id.main_btn_cancel1, R.id.main_btn_down2, R.id.main_btn_cancel2, R.id.main_btn_down3, R.id.main_btn_cancel3})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.main_btn_down1:
//                if (dialog == null) dialog = new DownLoadDialog(DownLoadActivity.this, url1);
//                if (!dialog.isShowing()) dialog.show();
////                DownloadManager.getInstance().download(url1, new DownLoadObserver() {
////                    @Override
////                    public void onNext(DownloadInfo value) {
////                        super.onNext(value);
////                        progress1.setMax((int) value.getTotal());
////                        progress1.setProgress((int) value.getProgress());
////                    }
////
////                    @Override
////                    public void onComplete() {
////                        if (downloadInfo != null) {
////                            Toast.makeText(DownLoadActivity.this,
////                                    downloadInfo.getFileName() + "-DownloadComplete",
////                                    Toast.LENGTH_SHORT).show();
////                        }
////                    }
////                });
////                break;
//            case R.id.main_btn_cancel1:
//                DownloadManager.getInstance().cancel(url1);
//                break;
//            case R.id.main_btn_down2:
//                DownloadManager.getInstance().download(url2, new DownLoadObserver() {
//                    @Override
//                    public void onNext(DownloadInfo value) {
//                        super.onNext(value);
////                        progress2.setMax((int) value.getTotal());
////                        progress2.setProgress((int) value.getProgress());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        if (downloadInfo != null) {
//                            Toast.makeText(DownLoadActivity.this,
//                                    downloadInfo.getFileName() + Uri.encode("下载完成"),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                break;
//            case R.id.main_btn_cancel2:
//                DownloadManager.getInstance().cancel(url2);
//                break;
//            case R.id.main_btn_down3:
//                DownloadManager.getInstance().download(url3, new DownLoadObserver() {
//                    @Override
//                    public void onNext(DownloadInfo value) {
//                        super.onNext(value);
////                        progress3.setMax((int) value.getTotal());
////                        progress3.setProgress((int) value.getProgress());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        if (downloadInfo != null) {
//                            Toast.makeText(DownLoadActivity.this,
//                                    downloadInfo.getFileName() + "下载完成",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                break;
//            case R.id.main_btn_cancel3:
//                DownloadManager.getInstance().cancel(url3);
//                break;
//        }
//    }

    @Override
    public void onBackPressed() {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        super.onBackPressed();
    }
}
