package com.example.john.myapplication.database;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.john.myapplication.R;
import com.example.john.myapplication.manager.DBManager;
import com.example.john.myapplication.test.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DBActivity extends Activity {

    @BindView(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add, R.id.delete, R.id.update, R.id.search, R.id.activity_db})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                DBManager.getInstance().getDaoSession().getUserDao().insert(new User());
                break;
            case R.id.delete:
                DBManager.getInstance().getDaoSession().getUserDao().delete(new User());
                break;
            case R.id.update:
                DBManager.getInstance().getDaoSession().getUserDao().update(new User());
                break;
            case R.id.search:
//                DBManager.getInstance().getDaoSession().getUserDao().readKey()
                break;
            case R.id.activity_db:
                break;
        }
    }
}
