package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.util.UploadUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2;
    private File file;
    private ImageView iv;
    private String fileName;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            createDialog(message.obj.toString());
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //动态获取权限
        try {
            String[] permArr = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
            };
            boolean needReq = false;
            for (int i = 0; i < permArr.length; i++) {
                if (ContextCompat.checkSelfPermission(this, permArr[i]) != PackageManager.PERMISSION_GRANTED) {
                    needReq = true;
                    break;
                }
            }
            if (needReq) {
                ActivityCompat.requestPermissions(this, permArr, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        iv = findViewById(R.id.iv);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        //调用系统camera
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //指定存储照片的位置和名字
                //File dir= Environment.getExternalStorageDirectory();//存到SD卡
                String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
                fileName = System.currentTimeMillis() + ".jpg";
                file = new File(dir + "/camera", fileName);
                Uri fileUri = Uri.fromFile(file);
                if (!file.exists()) {
                    file.getParentFile().mkdir();
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * 拍照成功返回
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        btn2.setVisibility(View.VISIBLE);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = "http://18021859626.qicp.vip/upload.php";
                        String res = UploadUtil.uploadFile(url, file);
                        Message msg = new Message();
                        msg.obj = res;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(dir + "/camera", fileName);
            Uri fileUri = Uri.fromFile(file);
            iv.setImageURI(fileUri);
        }
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), null);//图片插入到系统图库
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));//通知图库刷新
    }

    public void createDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage(msg);
        builder.setNegativeButton("确定", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}