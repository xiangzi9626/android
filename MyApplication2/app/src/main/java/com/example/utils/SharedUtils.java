package com.example.utils;
import android.content.Context;
import android.content.SharedPreferences;
public class SharedUtils {
    private static final String FILE_NAME="xiaofz";
    private static final String MODE_NAME="welcome";

    public static boolean getWelcomeBoolean(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getBoolean(MODE_NAME,false);
    }
    public static void putWelcomeBoolean(Context context, Boolean isFirst){
        SharedPreferences.Editor editor=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putBoolean(MODE_NAME,isFirst);
        editor.commit();
    }
    public static void putUserId(Context context, String userId){
        SharedPreferences.Editor editor=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("userId",userId);
        editor.commit();
    }
    public static void putUserName(Context context, String userName){
        SharedPreferences.Editor editor=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("userName",userName);
        editor.commit();
    }
    public static void putNickName(Context context, String nickname){
        SharedPreferences.Editor editor=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("nickname",nickname);
        editor.commit();
    }
    public static void putPortrait(Context context, String portrait){
        SharedPreferences.Editor editor=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("portrait",portrait);
        editor.commit();
    }
    public static void putPassword(Context context, String password){
        SharedPreferences.Editor editor=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("password",password);
        editor.commit();
    }
    public static String getUserId(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getString("userId","");
    }
    public static String getUserName(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getString("userName","");
    }
    public static String getNickName(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getString("nickname","");
    }
    public static String getPortrait(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getString("portrait","");
    }
    public static String getPassword(Context context){
        return context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getString("password","");
    }
}
