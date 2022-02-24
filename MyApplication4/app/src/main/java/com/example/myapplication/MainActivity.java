package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    //数据源
    private List<Map<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        fillData();
        //适配器
        SimpleAdapter adapter = new SimpleAdapter(
                this,//上下文
                data,//数据源类型要求是List<Map<String,String>>
                R.layout.list_item,//item布局
                new String[]{"name", "age"},//显示数据源中的key
                new int[]{R.id.tvName, R.id.tvAge}//显示数据控件的id
        );

        //设置适配器
        listView.setAdapter(adapter);
    }

    private void fillData() {
        data = new ArrayList<Map<String, String>>();
        Map<String, String> stu1 = new HashMap<>();
        stu1.put("name", "张三");
        stu1.put("age", "20");
        data.add(stu1);
        Map<String, String> stu2 = new HashMap<>();
        stu2.put("name", "李四");
        stu2.put("age", "21");
        data.add(stu2);
    }
}