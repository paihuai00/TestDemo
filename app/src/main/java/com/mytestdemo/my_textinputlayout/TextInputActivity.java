package com.mytestdemo.my_textinputlayout;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import com.mytestdemo.R;
import com.mytestdemo.view.XEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextInputActivity extends AppCompatActivity {

    XEditText myEdit;
    @BindView(R.id.name_textInput)
    TextInputLayout nameTextInput;
    XEditText pwdEdit;
    @BindView(R.id.pwd_textInput)
    TextInputLayout pwdTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        ButterKnife.bind(this);


        myEdit = (XEditText) nameTextInput.getEditText();
        myEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 3) {
                    nameTextInput.setError("用户名长度不能超过3");
                } else if (charSequence.length() > 0) {
                    nameTextInput.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pwdEdit = (XEditText) pwdTextInput.getEditText();
    }
}
