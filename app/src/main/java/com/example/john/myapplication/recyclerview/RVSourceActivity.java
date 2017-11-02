package com.example.john.myapplication.recyclerview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.john.myapplication.R;
import com.example.john.myapplication.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by John on 2017/3/14.
 */

public class RVSourceActivity extends Activity {
    @BindView(R.id.tv_midetail_applyed_nomessage)
    TextView noMessage;
    @BindView(R.id.lv_midetail_applyed)
    ListView listView;
    @BindView(R.id.sl_midetail_applyed)
    ScrollView scrollView;
    @BindView(R.id.ll_midetail_applyed_content)
    LinearLayout content;
    @BindView(R.id.ll_midetail_applyed_contenttitle)
    LinearLayout contentTitle;

    private ApplyedAdapter adapter;
    private List<AppleyedEntity> list = new ArrayList<AppleyedEntity>();
    private int dispatchCout = 0;
    private List<AppleyedEntity> dispatchList = new ArrayList<AppleyedEntity>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    toast(msg.obj.toString());
                    break;
                case 1:
                    if (list.size() > 10) {
                        richAdapter();
                        mHandler.sendEmptyMessageDelayed(4, 500);
                    } else {
                        if (adapter != null) adapter.setData(list);
                    }
                    if (listView != null) listView.scrollTo(0, 0);
                    break;
                case 4:
                    dismissProgressDialog();
                    if (list.size() <= dispatchCout) {
                        return;
                    } else if (content != null || scrollView != null) {//页面
                        // 销毁时，因为handler机制依然会走到该位置，但是content已经被销毁，故加次判断
                        if (content.getHeight() - (scrollView.getHeight() + scrollView.getScrollY
                                () - DisplayUtil.dp2px(RVSourceActivity.this, 14)) < DisplayUtil.dp2px(RVSourceActivity.this, 18)) {
                            showProgressDialog("");
                            richAdapter();
                        }
                        mHandler.sendEmptyMessageDelayed(4, 300);
                    }
                    break;
            }
        }
    };

    private void richAdapter() {
        if (dispatchCout > (list.size() - 1)) return;
        for (int i = 0; i < 10; i++) {
            if (dispatchCout > (list.size() - 1)) break;
            dispatchList.add(list.get(dispatchCout++));
        }
        if (adapter != null) adapter.setData(dispatchList);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_midetail_applyed);
        ButterKnife.bind(this);
        adapter = new ApplyedAdapter(this, noMessage);
        adapter.setData(list);
        listView.setAdapter(adapter);
        listView.getLastVisiblePosition();
        LinearLayout footerView = (LinearLayout) getLayoutInflater().inflate(R.layout.item_midetail_applyed, null);
        footerView.setBackgroundResource(R.drawable.bg_midetail_blindlevel);
        listView.addFooterView(footerView);
        getNetData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getNetData() {
        list.clear();
        AppleyedEntity tempEntity;
        for (int i = 0; i < 100; i++) {
            tempEntity = new AppleyedEntity();
            tempEntity.setPlayersRank("playersRank:" + i);
            tempEntity.setPlayersId("playersId:" + i);
            tempEntity.setPlayersStatus("playersStatus:" + i);
            tempEntity.setCurrPoint("currPoint:" + i);
            tempEntity.setPlayersName("playersName:" + i);
            tempEntity.setSeatInfo("seatInfo:" + i);
            list.add(tempEntity);
        }
        mHandler.obtainMessage(1).sendToTarget();
//        MatchInfoNetRequest.getInstance(this).getMatchPlayers(tourID, status, new
//                BaseNetRequest.onNetResponseListener() {
//                    @Override
//                    public void onFailure(String errorMsg) {
//                        Log.i("xxx", "MIDMatchinfo: " + errorMsg);
//                        Message msg = new Message();
//                        msg.what = 0;
//                        msg.obj = errorMsg;
//                        mHandler.sendMessage(msg);
//                    }
//
//                    @Override
//                    public void onResponse(JSONObject obj) {
//                        if ("0".equals(obj.getString("code"))) {
//                            list = JsonInflatorMI.getPlayser(obj.getJSONArray("resultData"));
//                            mHandler.obtainMessage(1).sendToTarget();
//                        } else {
//                            Message msg = new Message();
//                            msg.what = 0;
//                            msg.obj = obj.getString("message");
//                            mHandler.sendMessage(msg);
//                        }
//                    }
//                });
    }

    @OnClick({R.id.tv_midetail_applyed_nomessage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_midetail_applyed_nomessage:
                getNetData();
                break;
        }
    }

    protected void toast(String msg) {
        if (this != null) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showProgressDialog(String title) {
        if (mProgressDialog == null && this != null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage("加载中...");
        mProgressDialog.show();
    }

    protected ProgressDialog mProgressDialog;
}
