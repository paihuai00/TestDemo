package com.mytestdemo.mvp_demo2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mytestdemo.BaseActivity;
import com.mytestdemo.R;
import com.mytestdemo.mvp_demo2.persenter.LoginPersenter;
import com.mytestdemo.my_mvp.model.User;
import com.mytestdemo.my_mvp.presenter.UserLoginPresenter;
import com.mytestdemo.my_mvp.view.IUserLoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登陆View的接口，实现类也就是登陆的activity
 *
 * Activity一般也就做加载UI视图、设置监听再交由Presenter处理的一些工作，
 * 所以也就需要持有相应Presenter的引用
 *
 * 本层所需要做的操作就是在每一次有相应交互的时候，调用presenter的相关方法就行
 */
public class Mvp2LoginActivity extends BaseActivity implements IloginView {
    private static final String TAG = "Mvp2LoginActivity";
    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.pwd_edit)
    EditText pwdEdit;
    @BindView(R.id.login_btn)
    Button loginBtn;

    private LoginPersenter loginPersenter = new LoginPersenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ButterKnife.bind(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPersenter.confirmLogin(nameEdit.getText().toString().trim(), pwdEdit.getText().toString().trim());
            }
        });
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading: ");
    }

    @Override
    public void hindLoading() {
        Log.d(TAG, "hindLoading: ");
    }

    @Override
    public void showError() {
        Log.d(TAG, "showError: ");
    }

    @Override
    public void loginSucceedToView() {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }
}
