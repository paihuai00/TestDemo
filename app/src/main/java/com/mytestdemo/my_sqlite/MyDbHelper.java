package com.mytestdemo.my_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cuishuxiang on 2017/7/12.
 */

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDbHelper";
    /**
     * 构造函数
     * @param context 上下文对象
     * @param name 表示创建数据库的名称
     * @param factory 游标工厂
     * @param version 表示创建数据库的版本 >=1
     */

    public MyDbHelper(Context context, String name,
                      SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /**
     * 当数据库创建时回调的函数
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: ");

        String sql = "create table " + SqlConstant.TABLE_NAME + "(" +
                "_id Integer primary key autoincrement," +
                "name varchar(10)," +
                "age Integer not null)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /**
     * 当数据库打开时回调的函数
     * @param db 数据库对象
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
