package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2;
    private Dialog customDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        createCustomDialog();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View customView = layoutInflater.inflate(R.layout.custom_view, null);
                final EditText etUsername = customView.findViewById(R.id.username);
                final EditText etPassword = customView.findViewById(R.id.password);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("自定义")
                        .setView(customView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String username = etUsername.getText().toString();
                                String password = etPassword.getText().toString();
                                Toast.makeText(MainActivity.this, username + "/" + password, Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.show();
            }
        });
    }

    private void createCustomDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View customView2 = layoutInflater.inflate(R.layout.custom_view2, null);
        Button determine = customView2.findViewById(R.id.determine);
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        customDialog = new Dialog(this);
        customDialog.setTitle("自定义");
        customDialog.setContentView(customView2);
    }
}