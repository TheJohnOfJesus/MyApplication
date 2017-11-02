package com.example.john.myapplication.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.icu.math.BigDecimal;

import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DaoSession;

/**
 * Created by John on 2017/8/21.
 */

public class DBManager {
//    作者：啊发是也
//    链接：http://www.jianshu.com/p/853401a7d02b
//    來源：简书
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    static private DBManager instance;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    static public DBManager getInstance() {
        if (instance == null) instance = new DBManager();
        return instance;
    }

    /**
     * 推荐在Application做初始化操作
     * @param context
     */
    public void init(Context context) {
        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(context, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    public SQLiteDatabase getDb() {
        return db;
    }
    public int getZeroCount(int num){
        int length=String.valueOf(num).length();
        if(length==1)return 0;
        if(length==2)return num/10;
        return getZeroCount1(length);
    }
    private int getZeroCount1(int length){
        if (length<2)return -1;
        //(n+1)+(n-2)*9+C(n-1)*9=10n-17+C(n-1)*9;
        return 10*length-17+getZeroCount1(length-1);
    }

}
