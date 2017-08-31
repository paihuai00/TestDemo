package com.mytestdemo.my_navigation;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishuxiang on 2017/4/26.
 */

public class MyNavigationActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private RelativeLayout leftLayout;
    private RelativeLayout rightLayout;
    private List<ContentModel> list;
    private MyNaviAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        leftLayout=(RelativeLayout) findViewById(R.id.left);
        rightLayout=(RelativeLayout) findViewById(R.id.right);
        ListView listView=(ListView) leftLayout.findViewById(R.id.left_listview);
        initData();
        adapter=new MyNaviAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "点击了：" + i, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initData() {
        list=new ArrayList<ContentModel>();
        list.add(new ContentModel(R.mipmap.ic_launcher, "新闻"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "订阅"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "图片"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "视频"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "跟帖"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "投票"));
    }
}
