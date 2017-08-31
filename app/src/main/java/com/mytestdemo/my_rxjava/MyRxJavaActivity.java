package com.mytestdemo.my_rxjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;

public class MyRxJavaActivity extends AppCompatActivity {

    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rx_java);
        ButterKnife.bind(this);

    }

    private void initRxJava() {

        /**
         * 基础代码
         */
//        //创建一个被观察者
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("你好RxJava");
//                subscriber.onCompleted();
//            }
//        });
//        //创建一个观察者
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                //会将 被订阅者中的  onNext方法的String 写到这里面
//                textView2.setText(s);
//            }
//        };
//        //被观察者，订阅观察者
//        observable.subscribe(subscriber);

        /**
         * 利用just  优化代码
         */
        Observable.just("hello just RxJava").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView2.setText(s);
            }
        });

    }

    @OnClick(R.id.button)
    public void onClick() {
        initRxJava();
    }
}
