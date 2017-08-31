package com.mytestdemo.lazy_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

public class LazyFragmentActivity extends AppCompatActivity {
    private static final String TAG = "LazyFragmentActivity";
    private ViewPager viewpager;
    private FragmentAdapter adapter;

    private LazyTestFragment1 lazyFragment1;
    private LazyTestFragment2 lazyFragment2;
    private LazyTestFragment3 lazyFragment3;
    private LazyTestFragment4 lazyFragment4;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lazy_layout);  //注意，使用activityLiftRecycleCallbacks（）需要放在super之前
        super.onCreate(savedInstanceState);
        initView();

        Log.e("测试onActivityResult执行顺序",TAG +"       onCreate: ");
    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        lazyFragment1 = new LazyTestFragment1();
        lazyFragment2 = new LazyTestFragment2();
        lazyFragment3 = new LazyTestFragment3();
        lazyFragment4 = new LazyTestFragment4();

        fragmentList = new ArrayList<>();
        fragmentList.add(lazyFragment1);
        fragmentList.add(lazyFragment2);
        fragmentList.add(lazyFragment3);
        fragmentList.add(lazyFragment4);

        adapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("测试onActivityResult执行顺序",TAG +"       onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("测试onActivityResult执行顺序",TAG +"       onResume: ");

    }

    @Override
    protected void onPause() {
        super.onPause();
//        Intent intent = getIntent();
//        intent.putExtra("status", "测试");
//        setResult(0, intent);
        Log.e("测试onActivityResult执行顺序",TAG +"           onPause: ");
        setResult(10);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("测试onActivityResult执行顺序",TAG +"           onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("测试onActivityResult执行顺序",TAG +"           onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "LazeActivity Finish", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy:   LazeActivity Finish ");
    }
}
