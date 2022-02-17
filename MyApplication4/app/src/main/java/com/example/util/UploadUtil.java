package com.example.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class UploadUtil {
    public static String uploadFile(String requestUrl, File file) {
        String BOUNDARY = UUID.randomUUID().toString();//设置标识
        String PREFIX = "--";
        String LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";
        Boolean b = true;//上传成功的状态
        String result = "";//服务端响应结果
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //准备好协议头
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            //写入文件格式
            if (file != null) {
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                sb.append("Content-Disposition: form-data; name=\"fileName\"; filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type:application/octet-stream; charset=" + "UTF-8" + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                if (conn.getResponseCode() == 200) {
                    //Log.i("TEST", "file success");
                    //得到返回结果
                    InputStream res = conn.getInputStream();
                    byte[] buffer = new byte[1024];
                    int lens = res.read(buffer, 0, 1024);
                    if (lens != -1) {
                        result = new String(buffer, 0, lens);
                        //Log.i("TEST", result);
                    }
                } else {
                    b = false;
                    //Log.i("TEST", "file failure");
                }
            }
        } catch (MalformedURLException e) {
            b = false;
        } catch (IOException e) {
            b = false;
        }
        if (b) {
            return result;
        } else {
            return "上传失败请重试";
        }
    }
}
