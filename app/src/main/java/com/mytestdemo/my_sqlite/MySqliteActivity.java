package com.mytestdemo.my_sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cuishuxiang on 2017/7/12.
 */

public class MySqliteActivity extends AppCompatActivity {
    private static final String TAG = "MySqliteActivity";
    @BindView(R.id.create_btn)
    Button createBtn;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.change_btn)
    Button changeBtn;
    @BindView(R.id.query_btn)
    Button queryBtn;
    @BindView(R.id.webView)
    WebView webView;

    private MyDbHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.bind(this);
        dbHelper = new MyDbHelper(this, "info.db", null, 1);

    }

    @OnClick({R.id.create_btn, R.id.delete_btn, R.id.change_btn, R.id.query_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_btn:
                database = dbHelper.getWritableDatabase();
                database.close();
                break;
            case R.id.delete_btn:
                database = dbHelper.getWritableDatabase();
                /**
                 * delete(String table, String whereClause, String[] whereArgs)
                 * String table 表示删除数据表的名字
                 * String whereClause 表示删除条件
                 * String[] whereArgs 表示删除条件的占位符
                 */
                database.delete(SqlConstant.TABLE_NAME, "_id=1", null);
                database.close();
                break;
            case R.id.change_btn:
                database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "zhangsan");
                contentValues.put("age", 10);
                contentValues.put("_id", 1);
                database.insert(SqlConstant.TABLE_NAME, null, contentValues);
                database.close();
                break;
            case R.id.query_btn:
                Log.d(TAG, "第7位数是: " + getIndexNum(7) + "\n第3位数是:" + getIndexNum(3));
                maopao();
                break;
        }

    }

    //一列数的规则如下: 1、1、2、3、5、8、13、21、34 ，求第30位数是多少？使用递归实现
    public int getIndexNum(int i) {
        if (i <= 2) {
            return 1;
        }
        return getIndexNum(i - 1) + getIndexNum(i - 2);
    }

    //冒泡排序
    public void maopao() {
        int[] ints = new int[]{3, 25, 22, 6, 8, 10};

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    int num = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = num;
                }
            }
        }

        for (int i = 0; i < ints.length; i++) {
            Log.d(TAG, "maopao : " + ints[i] + "\n");
        }

    }
}
