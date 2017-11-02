package com.example.john.myapplication.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.john.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by John on 2017/8/25.
 */

public class RecycleViewActivity extends Activity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.rv_srl)
    SwipeRefreshLayout refreshLayout;
    private MultipleItemQuickAdapter adapter;

    //    private List<AppleyedEntity> list = new ArrayList<AppleyedEntity>();
    private int maxCount;
    private int curCount;
    private boolean mLoadMoreEndGone = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        ButterKnife.bind(this);
        maxCount = 100;
        curCount = 0;
        adapter = new MultipleItemQuickAdapter(this, getNetData());
        curCount = adapter.getData().size();
        initData();
        rvContent.setAdapter(adapter);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout.setOnRefreshListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rvContent.smoothScrollToPosition(50);
            }
        }).start();
    }

    private void initData() {
        adapter.setOnLoadMoreListener(this, rvContent);
    }

    public List<AppleyedEntity> getNetData() {
        List<AppleyedEntity> list = new ArrayList<AppleyedEntity>();
        AppleyedEntity tempEntity;
        for (int i = 0; i < 100; i++) {
            tempEntity = new AppleyedEntity();
            if (i == 0) {
                tempEntity.setType(AppleyedEntity.Type.TOP);
            } else {
                tempEntity.setType(AppleyedEntity.Type.CONTENT);
            }
            int m = i + curCount;
            tempEntity.setPlayersRank("Rank:" + m);
            tempEntity.setPlayersId("Id:" + m);
            tempEntity.setPlayersStatus("Status:" + m);
            tempEntity.setCurrPoint("chip:" + m);
            tempEntity.setPlayersName("name:" + m);
            tempEntity.setSeatInfo("info:" + m);
            list.add(tempEntity);
        }
        return list;
    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                curCount = 0;
                adapter.setNewData(getNetData());
                curCount = 10;
                refreshLayout.setRefreshing(false);
                adapter.setEnableLoadMore(true);
            }
        }, 1000);
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setEnabled(false);
        if (adapter.getData().size() < 10) {
            adapter.loadMoreEnd(true);
        } else {
            if (curCount >= maxCount) {
//                    adapter.loadMoreEnd();//default visible
                adapter.loadMoreEnd(false);//true is gone,false is visible
            } else {
                adapter.addData(getNetData());
                curCount = adapter.getData().size();
                adapter.loadMoreComplete();
            }
            refreshLayout.setEnabled(true);
        }
    }
}
