package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private String imgPath="https://t7.baidu.com/it/u=1951548898,3927145&fm=193&f=GIF";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.iv);
    }
    public void download(View v){
         new MyAsyncTask().execute(imgPath);
    }

    /**
     * 1 AsyncTask 三个泛型参数
     *  Params doInBackground方法中使用 httpURL
     * 2 Progress 任务完成的进度
     * 3 Result onPostExecute方法中使用
     */
    class MyAsyncTask extends AsyncTask<String,Void,byte[]> {
        @Override
        protected void onPreExecute() {
            Log.i("tag",Thread.currentThread().getName()+"----onPreExecute----");
        }

        @Override
        protected byte[] doInBackground(String... strings) {
            Log.i("tag", Thread.currentThread().getName() + "----doinBackground----");
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            byte[] images=null;
            try{
            URL url=new URL(strings[0]);
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCode=conn.getResponseCode();
                if (responseCode==200){
                    InputStream input=conn.getInputStream();
                    byte[] data=new byte[1024];
                    int temp=0;
                    while ((temp=input.read(data))!=-1){
                        outputStream.write(data,0,temp);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            images=outputStream.toByteArray();
            return images;
        }
        @Override
        protected void onPostExecute(byte[] bytes) {
            Log.i("tag",Thread.currentThread().getName()+"----onPostExecute----");
            if (bytes!=null && bytes.length!=0){
                Bitmap bm=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                iv.setImageBitmap(bm);
            }else{
                Toast.makeText(getApplicationContext(),"下载图片失败",Toast.LENGTH_LONG).show();
            }
        }
    }
}
