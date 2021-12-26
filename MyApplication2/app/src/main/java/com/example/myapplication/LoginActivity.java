package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.HttpUtil;
import com.example.utils.SharedUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.conf.Config;
import org.json.JSONObject;
import java.util.Map;
public class LoginActivity extends AppCompatActivity {
    private Button btn;
    private EditText et1, et2;
    private ImageView back;
    private String username,password;
    private TextView register;
    private Map<String, Object> uList;
    private CheckBox ch;
    private int num = 0;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String str = (String) msg.obj;
            try {
                Gson gson = new Gson();
                uList = gson.fromJson(str, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (uList.get("info").equals("success")) {
                    SharedUtils.putUserId(LoginActivity.this, uList.get("userId").toString());
                    SharedUtils.putUserName(LoginActivity.this, uList.get("username").toString());
                    SharedUtils.putNickName(LoginActivity.this, uList.get("nickname").toString());
                    SharedUtils.putPortrait(LoginActivity.this, uList.get("portrait").toString());
                    SharedUtils.putPassword(LoginActivity.this, uList.get("password").toString());
                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    in.putExtra("action", "FragmentMy");
                    startActivity(in);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏头部标题
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.btn);
        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.password);
        register = findViewById(R.id.register);
        ch = findViewById(R.id.ch);
        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    et2.setSelection(et2.getText().toString().length());
                } else {
                    et2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    et2.setSelection(et2.getText().toString().length());
                }
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, MainActivity.class);
                in.putExtra("action", "FragmentHome");
                startActivity(in);
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        String baseUrl = "http://www.xiaofz.cn/app/controller/LoginActivity.php";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et1.getText().toString();
                password = et2.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String url=Config.baseUrl+"/user/login";
                            String res=HttpUtil.post(url,"username="+username+"&password="+password);
                             if (res != null && res.length() > 0) {
                                Message msg = new Message();
                                msg.obj = res;
                                mHandler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        //*************
//监听软键盘的删除键
        et1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    num++;
                    //在这里加判断的原因是点击一次软键盘的删除键,会触发两次回调事件
                    if (num % 2 != 0) {
                        String s = et1.getText().toString();
                        if (!TextUtils.isEmpty(s)) {
                            et1.setText("" + s.substring(0, s.length() - 1));
                            //将光标移到最后
                            et1.setSelection(et1.getText().length());
                        }
                    }
                    return true;
                }
                return false;
            }
        });
        //******************
        et2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    num++;
                    //在这里加判断的原因是点击一次软键盘的删除键,会触发两次回调事件
                    if (num % 2 != 0) {
                        String s = et2.getText().toString();
                        if (!TextUtils.isEmpty(s)) {
                            et2.setText("" + s.substring(0, s.length() - 1));
                            //将光标移到最后
                            et2.setSelection(et2.getText().length());
                        }
                    }
                    return true;
                }
                return false;
            }
        });
        //*****************
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent in = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(in);
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
