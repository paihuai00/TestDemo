package com.mytestdemo.my_tabhost;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.mytestdemo.R;

public class TabHostActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec page1 = tabHost.newTabSpec("tab1")
                .setIndicator("叫兽")
                .setContent(R.id.isanimal);
        tabHost.addTab(page1);

        TabHost.TabSpec page2 = tabHost.newTabSpec("tab2")
                .setIndicator("老湿")
                .setContent(R.id.alwayswet);
        tabHost.addTab(page2);

        TabHost.TabSpec page3 = tabHost.newTabSpec("tab3")
                .setIndicator("哪吒")
                .setContent(R.id.nezha);
        tabHost.addTab(page3);
    }
}
