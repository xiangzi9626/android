package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "xiaofz.db";//数据库名称 要用static final
    private static final int DB_VERSION = 1;//数据库版本 大于等于1 要用static final

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //创建数据库中的表
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE friend_chat(send_user_id BIGING,receive_user_id BIGING,message_type varchar(300),content TEXT,time datetime)";
        sqLiteDatabase.execSQL(sql);
        String sql1 = "CREATE TABLE lately_friend(my_id BIGINT,friend_id BIGINT,nickname varchar(300),portrait varchar(300),unread_num BIGINT,time datetime)";
        sqLiteDatabase.execSQL(sql1);
        String sql2 = "CREATE TABLE news(img varchar(300),title varchar(300),url varchar(300))";
        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1 == i) {
            String sql = "drop table if exists friend_chat";
            sqLiteDatabase.execSQL(sql);
            String sql1 = "drop table if exists lately_friend";
            sqLiteDatabase.execSQL(sql1);
            String sql2 = "drop table if exists news";
            sqLiteDatabase.execSQL(sql2);
            onCreate(sqLiteDatabase);
        }
    }
}
