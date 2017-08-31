package com.mytestdemo.my_spinner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mytestdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpinnerActivity extends AppCompatActivity {
    private static final String TAG = "SpinnerActivity";
    @BindView(R.id.mSpinner)
    Spinner mSpinner;

    //定义一个String类型的List数组作为数据源
    private List<String> dataList;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.bind(this);
        //为dataList赋值，将下面这些数据添加到数据源中
        dataList = new ArrayList<String>();
        dataList.add("北京");
        dataList.add("上海");
        dataList.add("广州");
        dataList.add("深圳");
        dataList.add("咸宁");

        // 创建ArrayAdapter对象
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dataList);

        //为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);

        //为spinner绑定监听器，这里我们使用匿名内部类的方式实现监听器
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpinnerActivity.this, "您当前选择的是：" + adapter.getItem(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Log.d(TAG, "onNothingSelected:请选择您的城市 ");

            }
        });
    }

}
