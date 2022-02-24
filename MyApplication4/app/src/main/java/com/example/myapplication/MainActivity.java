package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //数据源
    private String[] data = {"上海", "江苏", "北京", "湖北", "湖南"};
    //控件
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        //适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,//上下文
                R.layout.list_item,//item布局
                R.id.tv,//item布局控件的ID
                data //数据源
        );
        //使用系统布局
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this,//上下文
                android.R.layout.simple_list_item_1,//系统item布局
                android.R.id.text1,//系统item布局控件的ID
                data //数据源
        );
        //设置适配器
        // listView.setAdapter(adapter);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *
             * @param adapterView 父容器
             * @param view 每一项的item
             * @param i 当前索引
             * @param l ID
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, data[i], Toast.LENGTH_LONG).show();
            }
        });
    }
}