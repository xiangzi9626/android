package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String[] data = {"AAA", "BBB", "CCC"};
    private Spinner spinner;
    //班级
    private List<String> gradeList = new ArrayList<String>();
    //班级中的学生
    private Map<String, List<String>> studentMap = new HashMap<String, List<String>>();
    private Spinner spinnerGrade, spinnerStudent;
    //选中的学生
    private List<String> selectedStudent = new ArrayList<>();
    //学生列表中适配器
    private ArrayAdapter stuAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        spinner = findViewById(R.id.spinner);
        spinnerGrade = findViewById(R.id.spinnerGrade);
        spinnerStudent = findViewById(R.id.spinnerStudent);
        spinnerGrade.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, gradeList));
        stuAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, selectedStudent);
        spinnerStudent.setAdapter(stuAdapter);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, android.R.id.text1, data));
        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedStudent.clear();
                String selectGrade = gradeList.get(i);
                selectedStudent.addAll(studentMap.get(selectGrade));
                stuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fillData() {
        gradeList.add("Android班");
        gradeList.add("嵌入式班");
        List<String> stuAndroid = new ArrayList<>();
        stuAndroid.add("张三");
        stuAndroid.add("李四");
        List<String> stuArm = new ArrayList<>();
        stuArm.add("王五");
        stuArm.add("赵六");
        studentMap.put(gradeList.get(0), stuAndroid);
        studentMap.put(gradeList.get(1), stuArm);
    }
}