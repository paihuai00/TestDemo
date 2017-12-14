package com.mytestdemo.dialogplus_edit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.MainActivity;
import com.mytestdemo.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author cuishuxiang
 * @date 2017/11/14.
 */

public class DialogEditActivity extends BaseActivity {
    @BindView(R.id.show_listview)
    ListView showListview;
//    @BindView(R.id.enter_edit)
//    EditText enterEdit;
    @BindView(R.id.send_btn)
    Button sendBtn;

    private List<String> stringList = new ArrayList<>();

    private DialogPlus dialogPlus;

    private InputMethodManager imm;

    private EditText dialogEdit;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_edit);
        ButterKnife.bind(this);

        for (int i = 0; i < 50; i++) {
            stringList.add("这是：" + i);
        }

        showListview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList));

        imm = (InputMethodManager)DialogEditActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);

        dialogPlus=DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.dialog_plus_edit))
                .setCancelable(true)
                .setGravity(Gravity.BOTTOM)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {

                    }
                })
                .setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogPlus dialog) {
                        //关闭软键盘
//                        imm.hideSoftInputFromWindow(dialogEdit.getWindowToken(), 0);
                        imm.hideSoftInputFromWindow(DialogEditActivity.this.getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                    }
                })
                .create();
        dialogEdit = (EditText) dialogPlus.getHolderView().findViewById(R.id.enter_edit);
    }

    @OnClick(R.id.send_btn)
    public void onViewClicked() {
        dialogPlus.show();
        //打开软键盘
        showSoftInputFromWindow(this,dialogEdit);

        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
