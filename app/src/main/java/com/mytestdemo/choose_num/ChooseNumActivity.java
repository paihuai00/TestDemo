package com.mytestdemo.choose_num;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author cuishuxiang
 * @date 2017/12/14.
 * <p>
 * 打分 的自定义View
 */

public class ChooseNumActivity extends BaseActivity {
    private static final String TAG = "ChooseNumActivity";
    @BindView(R.id.choose_num_view)
    ChooseNumView chooseNumView;
    @BindView(R.id.choose_btn)
    Button chooseBtn;
    @BindView(R.id.show_img)
    ImageView showImg;

    private ChooseNumAdapter adapter;
    private DialogPlus dialogPlus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_num);
        ButterKnife.bind(this);

        final List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }

        adapter = new ChooseNumAdapter(this, integerList);


        dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(new ListHolder())
                .setAdapter(adapter)
                .setExpanded(true, 500)
                .setGravity(Gravity.CENTER)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        Toast.makeText(getBaseContext(), "点击了 : " + integerList.get(position), Toast.LENGTH_SHORT).show();
                        chooseNumView.setNum(integerList.get(position));

                        dialog.dismiss();

                        chooseNumView.getNumsBitmap();
                    }
                })
                .setCancelable(true)
                .create();

        Bitmap bitmap = ImageUtils.getGradeBitmap(getBaseContext(), R.drawable.icon_bg_img, 100);

        showImg.setImageBitmap(bitmap);
        Log.d(TAG, "onCreate: ");
    }

    @OnClick(R.id.choose_btn)
    public void onViewClicked() {
        dialogPlus.show();
    }
}
