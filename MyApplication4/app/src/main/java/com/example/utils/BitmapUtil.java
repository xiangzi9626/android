package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitmapUtil {
    /**
     * 获取网落图片资源
     *
     * @param httpUrl
     * @return
     */
    public static Bitmap getHttpBitmap(String httpUrl) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes;
        InputStream input;
        Bitmap bitmap = null;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.connect();
            input = conn.getInputStream();
            byte[] data = new byte[1024];
            int temp = 0;
            while ((temp = input.read(data)) != -1) {
                outputStream.write(data, 0, temp);
            }
            bytes = outputStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
