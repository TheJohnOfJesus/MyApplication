package com.example.john.myapplication.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.john.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends Activity {
    private ListView listView;
    private TextView enable, disenable;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.listview);
        enable = (TextView) findViewById(R.id.enable);
        disenable = (TextView) findViewById(R.id.disenable);
        for (int i = 0; i < 100; i++) {
            list.add("this is position"+i);
        }
        listView.setAdapter(new MAdapter());
        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setEnabled(true);
            }
        });
        disenable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setEnabled(false);
            }
        });
    }

    class MAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(ListViewActivity.this);
            }
            ((TextView) convertView).setText(list.get(position));
            return convertView;
        }
    }
}
