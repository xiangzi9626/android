package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frag.FragmentGroup;
import com.example.frag.FragmentHome;
import com.example.frag.FragmentMsg;
import com.example.frag.FragmentMy;
import com.example.frag.FragmentNewsletter;

public class MainActivity extends FragmentActivity {
    private LinearLayout nav1, nav2, nav3, nav4, nav5;
    private TextView nav1_icon, nav1_text, nav2_icon, nav2_text, nav3_icon, nav3_text, nav4_icon, nav4_text;
    private TextView nav5_icon, nav5_text;
    private long exitTime = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置activity全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        nav1 = findViewById(R.id.nav1);
        nav1_icon = findViewById(R.id.nav1_icon);
        nav1_text = findViewById(R.id.nav1_text);
        nav2 = findViewById(R.id.nav2);
        nav2_icon = findViewById(R.id.nav2_icon);
        nav2_text = findViewById(R.id.nav2_text);
        nav3 = findViewById(R.id.nav3);
        nav3_icon = findViewById(R.id.nav3_icon);
        nav3_text = findViewById(R.id.nav3_text);
        nav4 = findViewById(R.id.nav4);
        nav4_icon = findViewById(R.id.nav4_icon);
        nav4_text = findViewById(R.id.nav4_text);
        nav5 = findViewById(R.id.nav5);
        nav5_icon = findViewById(R.id.nav5_icon);
        nav5_text = findViewById(R.id.nav5_text);
        //设置font字体图标
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        nav1_icon.setTypeface(font);
        nav2_icon.setTypeface(font);
        nav3_icon.setTypeface(font);
        nav4_icon.setTypeface(font);
        nav5_icon.setTypeface(font);
        Intent in = this.getIntent();
        String action = in.getStringExtra("action");
        if (action.equals("FragmentHome")) {
            setNavColor(nav1_icon,nav1_text);
             changeFragment(new FragmentHome());
        }else if (action.equals("FragmentGroup")) {
            setNavColor(nav2_icon,nav2_text);
            changeFragment(new FragmentGroup());
        } else if (action.equals("FragmentNewsletter")) {
            setNavColor(nav3_icon,nav3_text);
            changeFragment(new FragmentNewsletter());
        }else if (action.equals("FragmentMsg")) {
            setNavColor(nav4_icon,nav4_text);
            changeFragment(new FragmentMsg());
        } else if (action.equals("FragmentMy")) {
            setNavColor(nav5_icon,nav5_text);
            changeFragment(new FragmentMy());
        }
        nav1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               setNavColor(nav1_icon,nav1_text);
                changeFragment(new FragmentHome());
            }
        });
        nav2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setNavColor(nav2_icon,nav2_text);
                changeFragment(new FragmentGroup());
            }
        });
        nav3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setNavColor(nav3_icon,nav3_text);
                changeFragment(new FragmentNewsletter());
            }
        });
        nav4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNavColor(nav4_icon,nav4_text);
                changeFragment(new FragmentMsg());
            }
        });
        nav5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNavColor(nav5_icon,nav5_text);
                changeFragment(new FragmentMy());
            }
        });
    }
    public void changeFragment(Object fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_content, (Fragment) fragment);
        transaction.commit();
    }
    //设置导航颜色
    private void setNavColor(TextView tv_icon,TextView tv_text){
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList red = (ColorStateList) resource.getColorStateList(R.color.red, null);
        ColorStateList gray = (ColorStateList) resource.getColorStateList(R.color.gray, null);
        nav1_icon.setTextColor(gray);
        nav1_text.setTextColor(gray);
        nav2_icon.setTextColor(gray);
        nav2_text.setTextColor(gray);
        nav3_icon.setTextColor(gray);
        nav3_text.setTextColor(gray);
        nav4_icon.setTextColor(gray);
        nav4_text.setTextColor(gray);
        nav5_icon.setTextColor(gray);
        nav5_text.setTextColor(gray);
        tv_icon.setTextColor(red);
        tv_text.setTextColor(red);
    }
    private void login() {
        Intent in = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(in);
    }

    public boolean isLogin() {
        /*if (SharedUtils.getUserId(MainActivity.this).equals("")
                || SharedUtils.getUserName(MainActivity.this).equals("")
                || SharedUtils.getPassword(MainActivity.this).equals("")) {
            return false;
        } else {
            return true;
        }*/
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
