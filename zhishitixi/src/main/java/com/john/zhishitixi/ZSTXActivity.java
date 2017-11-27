package com.john.zhishitixi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ZSTXActivity extends Activity {

    private ListView listView;
    private TextView gyt;//概要图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zstx);
        listView = (ListView) findViewById(R.id.lv_zstx_list);
        gyt = (TextView) findViewById(R.id.tv_zstx_gy);
    }
}
