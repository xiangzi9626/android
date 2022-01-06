package com.example.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class HttpUtil {
    /**
     * 参数可以在url地址中
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        try {
            URL urlObject = new URL(url);
            URLConnection connection = urlObject.openConnection();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            is.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String post(String url, String param) {
        try {
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Accept", "text/plain,text/html");
            connection.setRequestProperty("Connection", "keep-alive");
            OutputStream os = connection.getOutputStream();
            if (!param.equals("")) {
                os.write(param.getBytes(StandardCharsets.UTF_8));
            }
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                byte[] b = new byte[1024];
                StringBuilder sb = new StringBuilder();
                int len;
                while ((len = is.read(b)) != -1) {
                    sb.append(new String(b, 0, len));
                }
                is.close();
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
