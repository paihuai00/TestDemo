package com.mytestdemo.my_toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.mytestdemo.R;

/**
 * Created by cuishuxiang on 2017/4/17.
 */

public class MyToolBarActivity extends AppCompatActivity {
    private ActionMenuView actionMenuView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_layout);
        actionMenuView = (ActionMenuView) findViewById(R.id.action_menu_view);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.my_toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu1:
                        Toast.makeText(getBaseContext(), "点击了" + item.getItemId(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu2:
                        Toast.makeText(getBaseContext(), "点击了" + item.getItemId(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu3:
                        Toast.makeText(getBaseContext(), "点击了" + item.getItemId(), Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }

        });

        MenuItem menuItem = actionMenuView.getMenu().findItem(R.id.menu1);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.my_toolbar_menu, menu);
//
//        return true;
//    }
}
