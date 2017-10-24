package com.mytestdemo.my_mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mytestdemo.R;
import com.mytestdemo.my_mvp.model.User;
import com.mytestdemo.my_mvp.presenter.UserLoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 不常用
 */
public class MyLoginActivity2 extends AppCompatActivity implements IUserLoginView {

    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.pwd_edit)
    EditText pwdEdit;
    @BindView(R.id.login_btn)
    Button loginBtn;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ButterKnife.bind(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserLoginPresenter.login();
            }
        });
    }

    @Override
    public String getUserName() {
        return nameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return pwdEdit.getText().toString();
    }

    @Override
    public void clearUserName() {
        nameEdit.setText("");
    }

    @Override
    public void clearPassword() {
        pwdEdit.setText("");
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "正在登录.....", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "登录完成", Toast.LENGTH_LONG).show();
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUser_name() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }
}
