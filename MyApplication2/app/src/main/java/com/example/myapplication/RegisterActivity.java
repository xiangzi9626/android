package com.example.myapplication;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.conf.Config;
import com.example.utils.HttpUtil;
import com.example.utils.Reg;

import org.json.JSONObject;
public class RegisterActivity extends AppCompatActivity {
    private String country = "86";
    private String username;
    private String password;
    private String phone;
    private String baseUrl, jsonData;
    private EditText et1, et2, et3;
    private Button btn;
    private ImageView close;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String info = (String) msg.obj;
            if (info.equals("")) {
                return false;
            }
            if (info.equals("next")) {
                username = et1.getText().toString().trim();
                password = et2.getText().toString();
                phone = et3.getText().toString();
                /*Intent intent = new Intent(RegisterActivity.this, ChackPhoneActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                intent.putExtra("phone", phone);
                startActivity(intent);*/
            } else {
                Toast.makeText(RegisterActivity.this, "" + info, Toast.LENGTH_LONG).show();
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.password);
        et3 = findViewById(R.id.phone);
        close = findViewById(R.id.close);
        baseUrl = "http://www.xiaofz.cn/app/controller/RegisterActivity.php";
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et1.getText().toString();
                password = et2.getText().toString();
                phone = et3.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_LONG).show();
                } else if (!Reg.checkphone(phone)) {
                    Toast.makeText(RegisterActivity.this, "手机号格式不正确", Toast.LENGTH_LONG).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("username", username);
                                jsonObject.put("password", password);
                                jsonObject.put("phone", phone);
                                jsonObject.put("action", "chack1");
                                String json = jsonObject.toString();
                                String url= Config.baseUrl+"/user/register";
                                String param="username="+username+"&password="+password+"&phone="+phone;
                                String res= HttpUtil.post(url,param);
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
            }
        });
    }
}
