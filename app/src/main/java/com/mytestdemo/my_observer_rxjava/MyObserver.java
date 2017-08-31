package com.mytestdemo.my_observer_rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mytestdemo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

import static rx.Observable.just;


/**
 * Created by cuishuxiang on 2017/6/1.
 * <p>
 * 观察者模式  demo    + Rxjava
 */

public class MyObserver extends AppCompatActivity {
    private static final String TAG = "MyObserver";
    @BindView(R.id.observer_btn)
    Button observerBtn;
    @BindView(R.id.map_btn)
    Button mapBtn;
    @BindView(R.id.flat_btn)
    Button flatBtn;
    @BindView(R.id.operator_btn)
    Button operatorBtn;
    @BindView(R.id.merge_bt)
    Button mergeBtn;
    @BindView(R.id.rxbing_time_btn)
    Button rxbingTimeBtn;
    @BindView(R.id.show_time)
    TextView showTime;
    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.pwd_edit)
    EditText pwdEdit;
    @BindView(R.id.login_btn)
    Button loginBtn;

    //添加假数据
    private List<UserBean> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_observer_layout);
        ButterKnife.bind(this);

        for (int i = 0; i < 20; i++) {
            list.add(new UserBean(i + "", i + "msg", new UserBean.DataBean("" + i, "name:" + i)));
        }

        initLoginBtn();

    }

    //创建，观察者
    private void initObservable() {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                //这里的subscriber，其实就是下面的  观察者
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onNext(4);
                subscriber.onCompleted();
            }
        }).toList().subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {

                for (int i = 0; i < integers.size(); i++) {
                    Log.d(TAG, "call: " + integers.get(i));
                }
            }
        });
//                .map(new Func1<List<Integer>, String>() {
//                    @Override
//                    public String call(List<Integer> integers) {
//                        return integers.size() + "";
//                    }
//                })
               
//                .subscribe(new Observer<Integer>() {//被观察者 订阅 观察者
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "onCompleted: ");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: " + e);
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.d(TAG, "onNext: " + integer);
//                        observerBtn.setText("" + integer);
//                    }
//                });

        /**
         * 使用Action 一个方法的回调call
         */
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("string:1");
                subscriber.onNext("string:2");
                subscriber.onNext("string:3");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "Action----call: " + s);
//                observerBtn.setText(s);
            }
        });

    }


    private void initMap() {
        /**
         * 将int型--->转换String
         */
        just(1, 2, 3, 4, 5)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        Log.d(TAG, "map：" + integer.getClass().toString());
                        return "This is " + integer;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: " + s);
                Log.d(TAG, "map------->call: " + s.getClass().toString());

            }
        });
    }

    private void initFlatMap() {
        /**
         * 使用flatMap  转换符
         *
         */

//        Observable.just(list.get(1))
//                .map(new Func1<UserBean, UserBean.DataBean>() {
//                    @Override
//                    public UserBean.DataBean call(UserBean userBean) {
//                        return userBean.data;
//                    }
//                }).subscribe(new Action1<UserBean.DataBean>() {
//            @Override
//            public void call(UserBean.DataBean dataBean) {
//                Log.d(TAG, "UserBean.DataBean: " + dataBean.getId());
//            }
//        });


        Observable.from(list)
                .map(new Func1<UserBean, UserBean.DataBean>() {
                    @Override
                    public UserBean.DataBean call(UserBean userBean) {
                        return userBean.data;
                    }
                }).subscribe(new Action1<UserBean.DataBean>() {
            @Override
            public void call(UserBean.DataBean dataBean) {
                Log.d(TAG, "Map---->UserBean.DataBean: " + dataBean.getId());
            }
        });

//        Observable.from(list)
//                .flatMap(new Func1<UserBean, Observable<UserBean.DataBean>>() {
//                    @Override
//                    public Observable<UserBean.DataBean> call(UserBean userBean) {
//                        return Observable.from(new ArrayList<UserBean.DataBean>());
//                    }
//                }).subscribe(new Action1<UserBean.DataBean>() {
//            @Override
//            public void call(UserBean.DataBean dataBean) {
//                Log.d(TAG, "flatMap---->UserBean.DataBean: " + dataBean.getId());
//            }
//        });

        Observable.just("hello", "flatMap")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just("转换过的：" + s);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "flatMap--->call: " + s);
            }
        });

    }

    //操作符
    private void initOperator() {
        /**
         *  filter  筛选出大于5的数据
         */
        Observable.from(list)
                .filter(new Func1<UserBean, Boolean>() {
                    @Override
                    public Boolean call(UserBean userBean) {
                        return 5 < Integer.valueOf(userBean.data.getId());
                    }
                }).subscribe(new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                Log.d(TAG, "filter:筛选出大于5--: " + userBean.data.getId());
            }
        });

        /**
         * take 用一个整数n作为一个参数，从原始的序列中发射前n个元素.
         */
        Observable.from(list)
                .take(5)//取得前五个元素
                .subscribe(new Action1<UserBean>() {
                    @Override
                    public void call(UserBean userBean) {
                        Log.d(TAG, "take:筛选出前5个数据--: " + userBean.data.getId());
                    }
                });

        /**
         * takeLast 同样用一个整数n作为参数，只不过它发射的是观测序列中后n个元素。
         */
        Observable.from(list)
                .takeLast(5)//筛选后5个数据
                .subscribe(new Action1<UserBean>() {
                    @Override
                    public void call(UserBean userBean) {
                        Log.d(TAG, "take:筛选出后5个数据--: " + userBean.data.getId());
                    }
                });

        /**
         * takeUntils   将第二个作为限制，条件满足就停止第一个
         */
        Observable.from(list)
                .takeUntil(new Func1<UserBean, Boolean>() {
                    @Override
                    public Boolean call(UserBean userBean) {
                        return Integer.valueOf(userBean.data.getId()) >= 10;
                    }
                }).subscribe(new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                Log.d(TAG, "takeUntils:打印小于10的--: " + userBean.data.getId());
            }
        });

        /**
         * skip 忽略前面的 5 几项数据
         */
        Observable.from(list)
                .skip(5)
                .subscribe(new Action1<UserBean>() {
                    @Override
                    public void call(UserBean userBean) {
                        Log.d(TAG, "skip:忽略前面的5项数据--: " + userBean.data.getId());
                    }
                });

        /**
         * distinct  过滤掉重复数据
         */
        just(1, 2, 1, 1, 3, 4)
                .distinct()//输出 1 2 3 4
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "distinct  过滤掉重复数据: " + integer);
                    }
                });

        /**
         * First(func1) 只发送符合条件的一项
         */
        Observable.from(list)
                .first(new Func1<UserBean, Boolean>() {
                    @Override
                    public Boolean call(UserBean userBean) {
                        return "1".equals(userBean.data.getId());
                    }
                }).subscribe(new Action1<UserBean>() {
            @Override
            public void call(UserBean userBean) {
                Log.d(TAG, "First(func1) 只发送符合条件的一项: " + userBean.data.getId());
            }
        });
    }

    //组合操作符
    private void initMerge() {
        /**
         *  merge  将2个Observable 合并，一个发 string  一个 发int
         */
        final String[] strings = new String[]{"s1", "s2", "s3", "s4", "s5", "s6", "s7"};
        //这里使用interval创建被观察者
        Observable<String> observable1 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return strings[aLong.intValue()];
                    }
                }).take(5);//只取前五个
        Observable<Long> observable2 = Observable.interval(500, TimeUnit.MILLISECONDS).take(5);

        Observable.merge(observable1, observable2)//将俩个被观察者合并，并订阅
                .subscribe(new Action1<Serializable>() {
                    @Override
                    public void call(Serializable serializable) {
                        Log.d(TAG, "merge: " + serializable.toString());
                    }
                });

        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<String> o2 = Observable.just("a", "b", "c");
        Observable.merge(o1, o2)
                .subscribe(new Action1<Serializable>() {
                    @Override
                    public void call(Serializable serializable) {
                        Log.d(TAG, "merge2: " + serializable.toString());
                    }
                });


        /**
         * zip 组合操作符
         */
        Observable<String> zipO1 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return aLong + "";
                    }

                }).take(5);
        Observable<String> zipO2 = Observable.from(strings).take(5);
        Observable.zip(zipO1, zipO2, new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + s2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "zip 组合操作符: " + s);
            }
        });

        /**
         * join 操作符，ObserableB 每次发射数据时，都会跟ObserableA已经发射的数据进行配对
         * 例：ObserableB-->“B”时，ObserableA已经发射了 1 2 3。则1B,2B,3B
         */
        Observable<String> join1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return strings[Integer.valueOf(String.valueOf(aLong))];
                    }
                }).take(5);

        Observable<String> join2 = Observable.interval(1, TimeUnit.SECONDS)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return "" + aLong;
                    }
                }).take(5);

        join1.join(join2, new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.interval(2, TimeUnit.SECONDS).map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return aLong + "";
                    }
                }).take(5);
            }
        }, new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.interval(0, TimeUnit.SECONDS).map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return aLong + "";
                    }
                }).take(5);
            }
        }, new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return s + "---->" + s2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "join: " + s);
            }
        });

    }


    @OnClick({R.id.observer_btn, R.id.map_btn, R.id.flat_btn,
            R.id.operator_btn, R.id.merge_bt, R.id.rxbing_time_btn
            , R.id.login_btn})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.observer_btn:
                initObservable();
                break;

            case R.id.map_btn:
                initMap();
                break;

            case R.id.flat_btn:
                initFlatMap();
                break;

            case R.id.operator_btn://操作符
                initOperator();
                break;

            case R.id.merge_bt:
                initMerge();
                break;

            case R.id.rxbing_time_btn://Rxbing 显示一个倒计时
                initTimeRxbindingg();

                break;

            case R.id.login_btn://模拟登陆，只有当账号密码，都输入才可以点击

//                initLoginBtn();

                break;
        }
    }
    //模拟登陆，只有当账号密码，都输入才可以点击
    private void initLoginBtn() {

        Observable<CharSequence> name = RxTextView.textChanges(nameEdit);
        Observable<CharSequence> pwd = RxTextView.textChanges(pwdEdit);

        Observable.combineLatest(name, pwd, new Func2<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence, CharSequence charSequence2) {
                return charSequence.length() > 0 && charSequence2.length() > 0;
            }
        }).subscribe(new Action1<Boolean>() {

            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(loginBtn).call(aBoolean);

                RxView.clicks(loginBtn).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(MyObserver.this,"登录中...",Toast.LENGTH_SHORT).show();
                        Toast.makeText(MyObserver.this,"模拟登录成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    //点击按钮，显示一个倒计时
    private void initTimeRxbindingg() {
        Observable<Void> voidObservable = RxView.clicks(rxbingTimeBtn)
                .throttleFirst(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Void>() {//在输出之前，做的事情
                    @Override
                    public void call(Void aVoid) {
                        RxView.enabled(rxbingTimeBtn).call(false);
                    }
                });

        voidObservable.subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Observable.interval(1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .take(6)
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onCompleted() {
                                RxTextView.text(showTime).call("获取验证码");
                                RxView.enabled(rxbingTimeBtn).call(true);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Long aLong) {
                                Log.d(TAG, "onNext: " + aLong);
                                RxTextView.text(showTime).call("剩余" + (8 - aLong) + "秒");
                            }
                        });
            }
        });

    }

//    @OnClick{(R.id.observer_btn),(R.id.map_btn)}
//    public void onViewClicked() {
//    }


//    private static final String TAG = "MyObserver";
//
//    @BindView(R.id.observer_btn)
//    Button observerBtn;
//
//    Observable<String> observable;
//    Observer<String> observer;
//

//
//    //被观察者
//    private Observable<String> getObservable() {
//        return Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//
//                e.onNext("111");
//                e.onNext("222");
//
//                e.onComplete();
//
//            }
//        });
//    }
//
//    private Observer<String> getObserver(){
////        return new Subscriber() {
////            @Override
////            public void onCompleted() {
////
////            }
////
////            @Override
////            public void onError(Throwable e) {
////
////            }
////
////            @Override
////            public void onNext(Object o) {
////
////            }
////        };
//        return new Observer<String>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String value) {
//                Log.d(TAG, "onNext: " + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//
//    }
//
//    @OnClick(R.id.observer_btn)
//    public void onClick() {
//        observable.subscribe(observer);
//    }
}
