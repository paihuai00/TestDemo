package com.mytestdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mytestdemo.behavior_md.BehaviorActivity;
import com.mytestdemo.bottom_navigation.BottomNavigationActivity;
import com.mytestdemo.choose_num.ChooseNumActivity;
import com.mytestdemo.city_loadview.CityLoadActivity;
import com.mytestdemo.clip_demo.ClipActivity;
import com.mytestdemo.constraint_test.ConstraintActivity;
import com.mytestdemo.custom_dialog.CustomDialogTestActivity;
import com.mytestdemo.dialogplus_edit.DialogEditActivity;
import com.mytestdemo.drag_message_bomb.MessageBombActivity;
import com.mytestdemo.drawable_test.MyDrawableActivity;
import com.mytestdemo.fish_bezier_view.FishActivity;
import com.mytestdemo.gpu_fliter.GpuFliterActivity;
import com.mytestdemo.grid_recyclerview.MyAddaRecycleActivity;
import com.mytestdemo.image_select.ImageSelectActivity;
import com.mytestdemo.joint_images.JointImageActivity;
import com.mytestdemo.layoutinflater.LayoutInflaterActivity;
import com.mytestdemo.lazy_fragment.LazyFragmentActivity;
import com.mytestdemo.load_state.LoadStateActivity;
import com.mytestdemo.loading_view.LoadingActivity;
import com.mytestdemo.mvp_demo2.view.Mvp2LoginActivity;
import com.mytestdemo.my_animator.MyAnimatorActivity;
import com.mytestdemo.my_animator.ValueAnimatorActivity;
import com.mytestdemo.my_banner.BannerActivity;
import com.mytestdemo.my_bitmap.BitmapActivity;
import com.mytestdemo.my_collapsingtoolbar.CollapsingToolbarActivity;
import com.mytestdemo.my_fab_button.MyFloatActionButtonActivity;
import com.mytestdemo.my_flowerview.FlowerActivity;
import com.mytestdemo.my_galleryfinal.MyGalleryFinalActivity;
import com.mytestdemo.my_gridview.MyGridViewActivity;
import com.mytestdemo.my_htextview.TextViewActivity;
import com.mytestdemo.my_httpurlconnection.MyHttpUrlConnetcionActivity;
import com.mytestdemo.my_infinite_img.InfiniteActivity;
import com.mytestdemo.my_keyboard.LoginActivity;
import com.mytestdemo.my_lockview.LockActivity;
import com.mytestdemo.my_matrix.MatrixActivity;
import com.mytestdemo.my_message_bubble.MsgBubbleActivity;
import com.mytestdemo.my_mvp.view.MyLoginActivity2;
import com.mytestdemo.my_navigation.MyNavigationActivity;
import com.mytestdemo.my_navigation.MyNavigationActivity2;
import com.mytestdemo.my_observer_rxjava.MyObserver;
import com.mytestdemo.my_okhttp.MyOktttpActivity;
import com.mytestdemo.my_path_paint_canvas.MyPathRadarActivity;
import com.mytestdemo.my_permission.MyPermissionActivity;
import com.mytestdemo.my_polygondraw.PolygonActivity;
import com.mytestdemo.my_record_play.RecordActivity;
import com.mytestdemo.my_rx_galleryfinall.MyRxGalleryFinalActivity;
import com.mytestdemo.my_rxjava.MyRxJavaActivity;
import com.mytestdemo.my_seekbar_bezier.SeekBarActivity;
import com.mytestdemo.my_service.MyServiceActivity;
import com.mytestdemo.my_shadow_img.ShadowActivity;
import com.mytestdemo.my_side_bar.MySideBarActivity;
import com.mytestdemo.my_side_bar.high_sideBar.HighSideBarActivity;
import com.mytestdemo.my_spinner.SpinnerActivity;
import com.mytestdemo.my_sqlite.MySqliteActivity;
import com.mytestdemo.my_state_bar.MyStateActivity;
import com.mytestdemo.my_state_bar2.MyStateActivity2;
import com.mytestdemo.my_switch_button.MySwitchButtonActivity;
import com.mytestdemo.my_tabhost.TabHostActivity;
import com.mytestdemo.my_test_duiqi.MyTestActivity;
import com.mytestdemo.my_textinputlayout.TextInputActivity;
import com.mytestdemo.my_toolbar.MyToolBarActivity;
import com.mytestdemo.my_view.MyViewActivity;
import com.mytestdemo.my_web_h5.WebToHtmlActivity;
import com.mytestdemo.my_zh_photo_choose.ZHPhotoActivity;
import com.mytestdemo.progress_manager.ProgressManagerActivity;
import com.mytestdemo.pull_xml.PullXmlActivity;
import com.mytestdemo.qq_step_count_view.QQStepCountActivity;
import com.mytestdemo.recyclerview_brvah.BravhActivity;
import com.mytestdemo.recyclerview_snaphelper.MySnapHelperActivity;
import com.mytestdemo.recycleview_diffutil.MyRecycleViewActivity;
import com.mytestdemo.recycleview_normal.NormalRecycleViewActivity;
import com.mytestdemo.smart_recyclerview.SmartRecyclerViewActivity;
import com.mytestdemo.table_viewpager.MyTabViewPagerActivity;
import com.mytestdemo.tree_list_view.TreeListActivity;
import com.mytestdemo.view_check.CheckActivity;
import com.mytestdemo.view_digital_loading.DigitalLoadingActivity;
import com.mytestdemo.wifi_demo.WifiActivity;
import com.mytestdemo.xiaomi_loading_view.XiaoMiLoadActivity;
import com.mytestdemo.yahoo_ball_loadingview.YahooLoadActivity;
import com.zzhoujay.richtext.RichText;

import java.util.logging.Logger;

/**
 * Created by cuishuxiang on 2017/3/30.
 */

public class MainActivity extends BaseActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private Button lazy_btn;//懒加载



    private Button recycler_btn,normal_recycler_btn,view_btn,drawable_btn,grid_btn;
    private Button snaphelper_btn,switch_btn,grid_viewpager,state_btn,state_btn2,align_txt,pickerview_txt;
    private Button galleryfinal_txt,toolbar_txt,rx_txt,radar_txt,animator_txt,fab_txt,navigation_txt,navigation_txt2,gesturelock_txt;

    private Button TextInputLayout_txt,keyboard_txt,call_txt,rxjava_txt,collapsing_txt,tab_txt;

    private Button okhttp__txt,mvp_txt,observer_btn,tabhost_btn,algin_textivew_btn,irecycler_btn;

    private Button lock_btn,polygon_btn,shadow_btn,spruce_btn,infinite_btn,banner_btn,service_btn,http_btn, bitmap_btn;

    private Button web_btn, sql_btn, mSeekBar_btn, matrix_btn, zh_photo_btn, record_btn;

    private Button msg_bubble_btn, flower_btn, msg_bomb_btn, fish_btn, loading_btn, yahoo_btn;

    private Button fliter_btn;
    private Button city_btn, qq_count_btn, sidebar_btn, xiaomi_btn, sidebar_high_btn, base_recycler_btn;

    private Button constraint_btn, clip_btn, bravh_btn, load_state_btn, bottom_navigation_btn,choose_num_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        initLazy();
        //常规 recyclerView
        initNormalRecycler();
        //使用 diffUtil
        initRecycler();

        //测试view
        initMyView();

        initDrawable();

        initGridRecycler();

        initSnapHelper();

        initSwitchBtn();

        initGridFragment();

        initStateBar();

        initTestDuiQi();

//        initPickerView();

        initGalleryGinal();

        initToolBar();

        initRxGalleryFinal();

        initRadar();

        initAnimator();

        initFab();

        initNavigation();

        initNavigation2();

        initTextInputLayout();

        initKeyBorad();

        initCall();

        initRxJava();

        initCollapsing();

        initTab();

        initOkHttp();

        Log.d(TAG, "----------->onCreate");

        initRichText();

        initMvpTest();

        initObserver();

        initTabHost();

        initAlginTestView();

        initIrecycler();

        initLock();

        initPolygon();

        initShadowImg();

        initSpruce();

        initInfinite();

        initBanner();

        initService();

        initHttpURLConnection();

        initBitmap();

        initWebH5();

        initSQLite();

        initSeekBar();

        initSeekBarHigh();

        initMatrix();

        initZHPhoto();

        initRecord();

        initMsgBubble();

        initFlower();

        initWindowManager();

        initMsgBomb();

        initFishView();

        initLoadingView();

        initYahooBall();

        initCityView();

        initQqCount();

        initSideBar();

        initXiaoMi();

        initFliter();

        initConstraint();

        initClip();

        initBravth();

        initLoadState();

        initBottomNavigation();


        initLayoutInflater();

        initCustomDialog();

        initValueAnimator();

        initMvpDemo2();


        initTreeList();

        initCheck();

        initDigitalView();

        initProgressManager();

        initJointImages();

        initPullXml();

        initDialogEdit();

        initBehavior();

        initImgSelect();

        initChooseNum();

        initWifi();
    }

    private void initWifi() {
        Button wifi_btn = (Button) findViewById(R.id.wifi_btn);
        wifi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WifiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initChooseNum() {

        Button choose_num_btn = (Button) findViewById(R.id.choose_num_btn);
        choose_num_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseNumActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initImgSelect() {

        Button img_selecter_btn = (Button) findViewById(R.id.img_selecter_btn);
        img_selecter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageSelectActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initBehavior() {

        Button behavior_btn = (Button) findViewById(R.id.behavior_btn);
        behavior_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BehaviorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDialogEdit() {

        Button dialog_edit_btn = (Button) findViewById(R.id.dialog_edit_btn);
        dialog_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initPullXml() {

        Button pull_btn = (Button) findViewById(R.id.pull_btn);
        pull_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PullXmlActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initJointImages() {

        Button joint_btn = (Button) findViewById(R.id.joint_btn);
        joint_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JointImageActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initProgressManager() {

        Button progress_manager_btn = (Button) findViewById(R.id.progress_manager_btn);
        progress_manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProgressManagerActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initDigitalView() {

        Button digital_btn = (Button) findViewById(R.id.digital_btn);
        digital_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DigitalLoadingActivity.class);

                startActivity(intent);
            }
        });
    }

    private void initCheck() {

        Button check_btn = (Button) findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTreeList() {

        Button tree_btn = (Button) findViewById(R.id.tree_btn);
        tree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TreeListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMvpDemo2() {

        Button mvp2_btn = (Button) findViewById(R.id.mvp2_btn);
        mvp2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Mvp2LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initValueAnimator() {

        Button value_animator_btn = (Button) findViewById(R.id.value_animator_btn);
        value_animator_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ValueAnimatorActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initCustomDialog() {

        Button dialog_btn = (Button) findViewById(R.id.dialog_btn);
        dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomDialogTestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLayoutInflater() {
        Button layoutinflater_btn = (Button) findViewById(R.id.layoutinflater_btn);
        layoutinflater_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LayoutInflaterActivity.class);
                startActivity(intent);
            }
        });
    }



    private void initBottomNavigation() {

        bottom_navigation_btn = (Button) findViewById(R.id.bottom_navigation_btn);
        bottom_navigation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initLoadState() {

        load_state_btn = (Button) findViewById(R.id.load_state_btn);
        load_state_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoadStateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initBravth() {
        bravh_btn = (Button) findViewById(R.id.bravh_btn);
        bravh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BravhActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initClip() {

        clip_btn = (Button) findViewById(R.id.clip_btn);
        clip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClipActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initConstraint() {

        constraint_btn = (Button) findViewById(R.id.constraint_btn);
        constraint_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConstraintActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFliter() {

        fliter_btn = (Button) findViewById(R.id.fliter_btn);
        fliter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GpuFliterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSeekBarHigh() {

        sidebar_high_btn = (Button) findViewById(R.id.sidebar_high_btn);
        sidebar_high_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HighSideBarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initXiaoMi() {

        xiaomi_btn = (Button) findViewById(R.id.xiaomi_btn);
        xiaomi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, XiaoMiLoadActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSideBar() {

        sidebar_btn = (Button) findViewById(R.id.sidebar_btn);
        sidebar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MySideBarActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("测试onActivityResult执行顺序",TAG +"        onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "----------->onStart");
        Log.e("测试onActivityResult执行顺序",TAG +"        onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "----------->onResume");
        Log.e("测试onActivityResult执行顺序",TAG +"        onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "----------->onPause");
        Log.e("测试onActivityResult执行顺序",TAG +"        onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "----------->onStop");
        Log.e("测试onActivityResult执行顺序",TAG +"        onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "----------->onDestroy");
        Log.e("测试onActivityResult执行顺序",TAG +"        onDestroy: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void initQqCount() {
        qq_count_btn = (Button) findViewById(R.id.qq_count_btn);
        qq_count_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QQStepCountActivity.class);
                startActivity(intent);
            }
        });
        
    }

    private void initCityView() {
        city_btn = (Button) findViewById(R.id.city_btn);
        city_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CityLoadActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initYahooBall() {

        yahoo_btn = (Button) findViewById(R.id.yahoo_btn);
        yahoo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, YahooLoadActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLoadingView() {
        loading_btn = (Button) findViewById(R.id.loading_btn);
        loading_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFishView() {
        Log.d(TAG, "initFishView: Math.sin(30) = " + Math.sin(30));
        Log.d(TAG, "initFishView: Math.toRadians(30) = " + Math.toRadians(30));
        Log.d(TAG, "initFishView: Math.sin(Math.toRadians(30)) = " + Math.sin(Math.toRadians(30)));

        fish_btn = (Button) findViewById(R.id.fish_btn);
        fish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FishActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMsgBomb() {

        msg_bomb_btn = (Button) findViewById(R.id.msg_bomb_btn);
        msg_bomb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessageBombActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWindowManager() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();

        Log.d(TAG, "initWindowManager: width " + width + " height : " + height);
    }

    private void initFlower() {

        flower_btn = (Button) findViewById(R.id.flower_btn);
        flower_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FlowerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMsgBubble() {


        msg_bubble_btn = (Button) findViewById(R.id.msg_bubble_btn);
        msg_bubble_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MsgBubbleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecord() {

        record_btn = (Button) findViewById(R.id.record_btn);
        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initZHPhoto() {

        zh_photo_btn = (Button) findViewById(R.id.zh_photo_btn);
        zh_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ZHPhotoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMatrix() {

        matrix_btn = (Button) findViewById(R.id.matrix_btn);
        matrix_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MatrixActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSeekBar() {
        mSeekBar_btn = (Button) findViewById(R.id.mSeekBar_btn);
        mSeekBar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SeekBarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSQLite() {

        sql_btn = (Button) findViewById(R.id.sql_btn);
        sql_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MySqliteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initWebH5() {

        web_btn = (Button) findViewById(R.id.web_btn);
        web_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebToHtmlActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initBitmap() {

        bitmap_btn = (Button) findViewById(R.id.bitmap_btn);
        bitmap_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BitmapActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initHttpURLConnection() {

        http_btn = (Button) findViewById(R.id.http_btn);
        http_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyHttpUrlConnetcionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initService() {


        service_btn = (Button) findViewById(R.id.service_btn);
        service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyServiceActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initBanner() {

        banner_btn = (Button) findViewById(R.id.banner_btn);
        banner_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initInfinite() {

        infinite_btn = (Button) findViewById(R.id.infinite_btn);
        infinite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfiniteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initSpruce() {
        spruce_btn = (Button) findViewById(R.id.spruce_btn);
        spruce_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initShadowImg() {
        shadow_btn = (Button) findViewById(R.id.shadow_btn);
        shadow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShadowActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initPolygon() {
        polygon_btn = (Button) findViewById(R.id.polygon_btn);
        polygon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PolygonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLock() {
        lock_btn = (Button) findViewById(R.id.lock_btn);
        lock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LockActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initIrecycler() {
        irecycler_btn = (Button) findViewById(R.id.irecycler_btn);
        irecycler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SmartRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initAlginTestView() {

        algin_textivew_btn = (Button) findViewById(R.id.algin_textivew_btn);
        algin_textivew_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTabHost() {

        tabhost_btn = (Button) findViewById(R.id.tabhost_btn);
        tabhost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TabHostActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initObserver() {
        observer_btn = (Button) findViewById(R.id.observer_btn);
        observer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyObserver.class);
                startActivity(intent);
            }
        });
    }

    private void initMvpTest() {
        mvp_txt = (Button) findViewById(R.id.mvp_txt);
        mvp_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyLoginActivity2.class);
                startActivity(intent);
            }
        });

    }

    private void initRichText() {
        TextView rich_txt = (TextView) findViewById(R.id.rich_txt);
//        String htmlText = "<p style=\"text-indent: 24px; line-height: 115%; text-align: center;\"><span style=\"line-height: 115%; font-size: 14px; font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;\"><img title=\"\" alt=\"810X240－03.png\" src=\"http://101.200.173.136:38081/file/2016-06-16/ea186002-cd3c-42f6-b28c-66dcd9409e26.png\"/></span></p><p style=\"text-indent: 24px; line-height: 115%;\"><span style=\"line-height: 115%; font-size: 14px; font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;\"><br/></span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\">根据国家《教师教育课程标准(试行)》的基本要求，中学数学教学的现实诉求和数学教育学科发展的基本要求，《中学数学教学设计》是在普通高等师范院校数学与应用数学本科生掌握了一定的数学专业基础知识（如数学分析、高等代数和解析几何等）和经历教育学、心理学等通识教育的基础上开设的。</span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\"><br/></span></p><p style=\"text-indent: 24px; line-height: 115%; text-align: center;\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\"><img title=\"\" alt=\"810X240－04.png\" src=\"http://101.200.173.136:38081/file/2016-06-16/f591f7b6-332f-46d5-ab21-bdbc016ccd18.png\"/></span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\"><br/></span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"font-size: 16px; line-height: 115%; font-family: arial, helvetica, sans-serif;\">本课程与《中学数学课程标准与教材研究》、《中学数学教学实践》课程是师范生必修的专业基础课，是数学教师教育的核心课程。《中学数学课程标准与教材研究》是本课程的基础，本课程是《中学数学课程标准与教材研究》课程的发展，是《中学数学教学实践》课程的基础，是高师教育与基础教育无缝对接、教育理论与教育实践密切联系的实践性课程。</span></p><p style=\"text-indent:32px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\">根据课程目标的要求、中学数学教师的诉求和职前数学教师的需求，本课程主要目的：通过本课程的学习，使学生能够系统地掌握中学数学教学设计的基础知识与基本技能、基本原理与基本方法，并实现如下目的：</span></p><p style=\"text-align: center;\"><span style=\"font-size: 16px; font-family: arial, helvetica, sans-serif;\">&nbsp;&nbsp; <span style=\"font-size: 16px; font-family: 宋体;\">&nbsp;</span><img title=\"\" alt=\"实现如下目的.png\" src=\"http://101.200.173.136:38081/file/2016-05-18/140ab785-309a-4f64-81b2-a618b53762d6.png\"/></span></p>";
//
        String html1 = "<div class=\"main-wrap content-wrap\"> <div class=\"headline\"> <div class=\"img-place-holder\"></div> </div> <div class=\"content-inner\"> <div class=\"question\"> <h2 class=\"question-title\">为什么有的建筑可以做到在很长一段时间内都不过时？</h2> <div class=\"answer\"> <div class=\"meta\"> <img class=\"avatar\" src=\"http://pic4.zhimg.com/v2-8921498c242100cc8724c20a9f369d5b_is.jpg\"> <span class=\"author\">袁牧，</span><span class=\"bio\">职业建筑师</span> </div> <div class=\"content\"> <p>没错，建筑就是对抗时间的艺术。</p> <p>这个问题的另一个版本是……一个很庸俗的版本……甲方经常提出的要求：</p> <p>五十年不过时！</p> <p>只不过可惜的是，提这种要求的甲方，不但很快设计就过时，而且自己也往往提早过时……</p> <p>只是他们说的不过时，并不是我们说的不过时。</p> <p>- 什么是不过时 -</p> <p>回答这个问题之前，我们得谈谈什么是不过时。</p> <p>其实就是超越短期潮流的美。</p> <p>前述那种甲方的不过时，其实是指试图超前于潮流，以为就可以不过时，然而大错特错。</p> <p>我们知道，实际上潮流经常不断的循环，甚至整个艺术史都在不断的循环，无非左，右，左，右，最多来个左中右（这里左右只是代指方向差异，例如繁与简，写实与抽象，弯与直等等），因此，妄图超前于潮流实现不过时，根本是痴人说梦。大凡追逐潮流的，一般都会被潮流抛弃。</p> <p>所以我们所说的不过时，是指超越潮流。</p> <p>在潮流忽左忽右的标准之下，有普世的美学基石。恪守这种近乎永恒（也就是上下五千年的长度）的美学规则，方可不过时。</p> <p>-古典不过时 -</p> <p><img class=\"content-image\" src=\"http://pic4.zhimg.com/70/v2-355db344a091f42d84fbe19e60461eab_b.jpg\" alt=\"\" /></p> <p>例如上面这位，西方建筑的元老，帕提隆神庙。既可以说作为古典中的古典（古典，也就是经典，英语里 classic 就一个词），过时透顶；也可以说至今魅力十足，不断传承，从未过时。</p> <p>其实就是超越时尚而已。</p> <p>它有很多后代，两千年后还不过时，文艺复兴抄了一轮，新古典又抄了一轮，直到当代中国还在抄欧陆风抄的已经老祖宗都不认得了。</p> <p>这确实是不过时，最主要的一种不过时。</p> <p>当然，这种中国也有这种不过时。</p> <p><img class=\"content-image\" src=\"http://pic1.zhimg.com/70/v2-0763a2ab8078e3f4db082608707544ac_b.jpg\" alt=\"\" /></p> <p>跟西方的反反复复不同，中国传统木构宫殿的体系自大唐朝基本确立之后，直到明清，基本上一千多年没有大变，这种根本不变的时尚，算不算另一种不过时呢？</p> <p>古典不过时。</p> <p>- 现代不过时 -</p> <p>那么现代怎么办？</p> <p>其实现代的也不过时。</p> <p><img class=\"content-image\" src=\"http://pic1.zhimg.com/70/v2-41bfbe28a90dce85e6daa586f24d40fc_b.jpg\" alt=\"\" /></p> <p>老贝的卢浮宫金字塔过去快三十年了，历久弥新，至今时尚到闪闪发光没有朋友。</p> <p>当然还有它的祖宗。</p> <p><img class=\"content-image\" src=\"http://pic3.zhimg.com/70/v2-3d421d11373868834122708474fa7382_b.jpg\" alt=\"\" /></p> <p>简洁，几何，功能，钢与玻璃，混凝土，砖，都不过时。</p> <p>柯布，康都是这样。</p> <p><img class=\"content-image\" src=\"http://pic2.zhimg.com/70/v2-cb6ea5d1398240a882c2c12a6a77b2f1_b.jpg\" alt=\"\" /></p> <p>其实我认为，这些现代是古典的嫡传，所以不过时。</p> <p>有趣的是，现代，每个时代都自称是现代，再过几十年，我们如何称呼当下的现代？</p> <p>嘿嘿嘿。</p> <p>- 形式美的原则 -</p> <p>其实每个现代都一样。现代和古典也都一样。</p> <p>凡是传下来不过时的原理，都可以概括为所谓《形式美的原则》</p> <p>秩序，均衡，对称，韵律，比例，点，线，面，虚实，疏密，轻重，黑白，质感，光线，自相似，分形，自组织，混沌。</p> <p>其实本质都是数学。</p> <p>不要看那些老古董，古典建筑西方的满身雕刻，好像很不几何，很不数学。</p> <p>其实他们都严谨到发疯，把数学的简洁和秩序当做生命。</p> <p>西方从毕达哥拉斯学派开始，把数学崇拜的跟神一样，所以建筑里特别喜爱黄金分割。现在科学还是一样，把数学当命根子。</p> <p>古典建筑天天算比例。</p> <p><img class=\"content-image\" src=\"http://pic4.zhimg.com/70/v2-0b4debda1f163c6d739fb958da3c72ab_b.jpg\" alt=\"\" /></p> <p>艺术家天天不务正业算数学。</p> <p><img class=\"content-image\" src=\"http://pic4.zhimg.com/70/v2-3e49b57f53f3dcebbc5ccdbeab4c6927_b.jpg\" alt=\"\" /></p> <p>柯布西耶老把自己当芬奇。</p> <p><img class=\"content-image\" src=\"http://pic4.zhimg.com/70/v2-4b44779ced6fe2d4f2e36ab1a586668f_b.jpg\" alt=\"\" /></p> <p>中国呢，弧线大屋顶雕梁画栋，木工泥瓦匠的东西，也数学？没错，而且非常数学，非常几何。</p> <p>随便盗个图。</p> <p><img class=\"content-image\" src=\"http://pic4.zhimg.com/70/v2-df6d8f8c58b606bed06779d594ba3567_b.jpg\" alt=\"\" /></p> <p>中国仅有的三栋唐代建筑中最大的，佛光寺，遵循极为 严格的简洁比例，而且层层嵌套，互相咬合。这是我宿舍哥们这几年的研究成果，集学院四代人对中国古典建筑比例研究的大成，结论是几千年来现存的数百经典案例证明，极为简洁的几何比例贯穿了整个中国古典城市、建筑乃至器物的设计建造，不可思议的整体性，系统性和传承力。有兴趣的可以去读原文，或者等年底专著出版。丧心病狂的院士级成果，专著目前在出版社，年内出版，憋了四年终于可以披露了，因为衍生论文已经发表。</p> <p>《规矩方圆 佛之居所—— 五台山佛光寺东大殿构图比例探析》 [ 王南 ]，《建筑学报》2017 / 06</p> <p>古典凭什么不过时？跟现代建筑一样，凭的是数学之美，永不过时。</p> <p>- 数学之美 -</p> <p>那么为什么数学之美就可以不过时？</p> <p>我个人的看法是，因为这种源自数学的秩序，贯穿几千年，资格太老了，它本身就是人类审美的标准，所以它超越潮流，超越时尚，无法颠覆。</p> <p>审美本来是相当主观的事情，最重要的是如何培养审美的标准。而审美的标准有很多种，互相当然也冲突和竞争。数学之美，当然是因为最本质，最广泛；类似的，古典建筑及其传承者之美，应用了数学秩序，最古老，最庞大。两者都是最大最久最广泛的存在，也就成了最大的标准。</p> <p>因此它们都不过时。因为它们就是根本标准。</p> <p>当然，建筑和几何之外的美学，也有很多超越了形式美，那就不在我研究范围之内了。</p> </div> </div> <div class=\"view-more\"><a href=\"http://www.zhihu.com/question/62245725\">查看知乎讨论<span class=\"js-question-holder\"></span></a></div> </div> </div> </div>";
//        String html1 = "<p style=\"text-indent: 24px; line-height: 115%; text-align: center;\"><span style=\"line-height: 115%; font-size: 14px; font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;\"><img title=\"\" alt=\"810X240－03.png\" src=\"http://101.200.173.136:38081/file/2016-06-16/ea186002-cd3c-42f6-b28c-66dcd9409e26.png\"/></span></p><p style=\"text-indent: 24px; line-height: 115%;\"><span style=\"line-height: 115%; font-size: 14px; font-family: 微软雅黑, &#39;Microsoft YaHei&#39;;\"><br/></span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\">根据国家《教师教育课程标准(试行)》的基本要求，中学数学教学的现实诉求和数学教育学科发展的基本要求，《中学数学教学设计》是在普通高等师范院校数学与应用数学本科生掌握了一定的数学专业基础知识（如数学分析、高等代数和解析几何等）和经历教育学、心理学等通识教育的基础上开设的。</span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\"><br/></span></p><p style=\"text-indent: 24px; line-height: 115%; text-align: center;\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\"><img title=\"\" alt=\"810X240－04.png\" src=\"http://101.200.173.136:38081/file/2016-06-16/f591f7b6-332f-46d5-ab21-bdbc016ccd18.png\"/></span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\"><br/></span></p><p style=\"text-indent:24px;line-height:115%\"><span style=\"font-size: 16px; line-height: 115%; font-family: arial, helvetica, sans-serif;\">本课程与《中学数学课程标准与教材研究》、《中学数学教学实践》课程是师范生必修的专业基础课，是数学教师教育的核心课程。《中学数学课程标准与教材研究》是本课程的基础，本课程是《中学数学课程标准与教材研究》课程的发展，是《中学数学教学实践》课程的基础，是高师教育与基础教育无缝对接、教育理论与教育实践密切联系的实践性课程。</span></p><p style=\"text-indent:32px;line-height:115%\"><span style=\"line-height: 115%; font-size: 16px; font-family: arial, helvetica, sans-serif;\">根据课程目标的要求、中学数学教师的诉求和职前数学教师的需求，本课程主要目的：通过本课程的学习，使学生能够系统地掌握中学数学教学设计的基础知识与基本技能、基本原理与基本方法，并实现如下目的：</span></p><p style=\"text-align: center;\"><span style=\"font-size: 16px; font-family: arial, helvetica, sans-serif;\">&nbsp;&nbsp; <span style=\"font-size: 16px; font-family: 宋体;\">&nbsp;</span><img title=\"\" alt=\"实现如下目的.png\" src=\"http://101.200.173.136:38081/file/2016-05-18/140ab785-309a-4f64-81b2-a618b53762d6.png\"/></span></p>";
        RichText.from(html1).into(rich_txt);


        Log.d(TAG, "主线程: " + Thread.currentThread().getId());

        //测试postdelayed 是否开启子线程,  并没有开启子线程
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "postDelayed的线程: " + Thread.currentThread().getId());
            }
        }, 100);
        //开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread的线程: " + Thread.currentThread().getId());
            }
        }).start();
    }

    private void initOkHttp() {

        okhttp__txt= (Button) findViewById(R.id.okhttp__txt);
        okhttp__txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyOktttpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTab() {
        tab_txt= (Button) findViewById(R.id.tab_txt);
        tab_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyTabViewPagerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initCollapsing() {

        collapsing_txt= (Button) findViewById(R.id.collapsing_txt);
        collapsing_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CollapsingToolbarActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initRxJava() {

        rxjava_txt= (Button) findViewById(R.id.rxjava_txt);
        rxjava_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyRxJavaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initCall() {
        call_txt= (Button) findViewById(R.id.call_txt);
        call_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPermissionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initKeyBorad() {

        keyboard_txt= (Button) findViewById(R.id.keyboard_txt);
        keyboard_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTextInputLayout() {

        TextInputLayout_txt= (Button) findViewById(R.id.TextInputLayout_txt);
        TextInputLayout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextInputActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initNavigation2() {

        navigation_txt2= (Button) findViewById(R.id.navigation_txt2);
        navigation_txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyNavigationActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void initNavigation() {

        navigation_txt= (Button) findViewById(R.id.navigation_txt);
        navigation_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyNavigationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFab() {

        fab_txt= (Button) findViewById(R.id.fab_txt);
        fab_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyFloatActionButtonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAnimator() {
        animator_txt= (Button) findViewById(R.id.animator_txt);
        animator_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyAnimatorActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initRadar() {

        radar_txt= (Button) findViewById(R.id.radar_txt);
        radar_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyPathRadarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRxGalleryFinal() {

        rx_txt= (Button) findViewById(R.id.rx_txt);
        rx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyRxGalleryFinalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initToolBar() {

        toolbar_txt= (Button) findViewById(R.id.toolbar_txt);
        toolbar_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyToolBarActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initGalleryGinal() {
        galleryfinal_txt= (Button) findViewById(R.id.galleryfinal_txt);
        galleryfinal_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyGalleryFinalActivity.class);
                startActivity(intent);
            }
        });


    }

//    private void initPickerView() {
//        pickerview_txt= (Button) findViewById(R.id.pickerview_txt);
//        pickerview_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MyPickerViewActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }

    private void initTestDuiQi() {
        align_txt= (Button) findViewById(R.id.align_txt);
        align_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyTestActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initStateBar() {
        state_btn= (Button) findViewById(R.id.state_btn);
        state_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyStateActivity.class);
                startActivity(intent);
            }
        });

        //浸入式
        state_btn2= (Button) findViewById(R.id.state_btn2);
        state_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyStateActivity2.class);
                startActivity(intent);
            }
        });

    }

    private void initGridFragment() {
        grid_viewpager= (Button) findViewById(R.id.grid_viewpager);
        grid_viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyGridViewActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initSwitchBtn() {
        switch_btn= (Button) findViewById(R.id.switch_btn);
        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MySwitchButtonActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initSnapHelper() {
        snaphelper_btn = (Button) findViewById(R.id.snaphelper_btn);
        snaphelper_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MySnapHelperActivity.class);
                startActivity(intent);
            }
        });

    }


    private void initGridRecycler() {
        grid_btn = (Button) findViewById(R.id.grid_btn);
        grid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyAddaRecycleActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initDrawable() {
        drawable_btn = (Button) findViewById(R.id.drawable_btn);
        drawable_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyDrawableActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMyView() {
        view_btn = (Button) findViewById(R.id.view_btn);
        view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //真正于用户交互的界面onWindowFocusChanged 指的是这个Activity得到或者失去焦点的时候
        Log.d(TAG, "----------->onWindowFocusChanged");
    }


    private void initRecycler() {
        recycler_btn = (Button) findViewById(R.id.recycler_btn);
        recycler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyRecycleViewActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initNormalRecycler(){
        normal_recycler_btn = (Button) findViewById(R.id.normal_recycler_btn);
        normal_recycler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NormalRecycleViewActivity.class);
                startActivity(intent);
            }
        });
    }

    //懒加载 demo
    private void initLazy() {
        lazy_btn = (Button) findViewById(R.id.lazy_btn);
        lazy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LazyFragmentActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 0); //测试
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 0) {
//            switch (resultCode) {
//                case 0:
//                    String status = data.getStringExtra("status");
//                    Toast.makeText(this, "懒加载Activity返回" + status, Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
        Log.e("测试onActivityResult执行顺序",TAG +"        onActivityResult: ");

        if (resultCode == 10) {
            Toast.makeText(this, "LazeActivity Finish", Toast.LENGTH_SHORT).show();
        }

    }


    //    @Override
//    public int initView() {
//        return R.layout.activity_main;
//    }
//
//    @Override
//    public void initData() {
//
//    }
}
