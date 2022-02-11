package com.example.myapplication;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showPopup(btn);
           }
       });
    }
    public void showPopup(View v){
        PopupMenu popup=new PopupMenu(this,v);
        getMenuInflater().inflate(R.menu.main,popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                 switch(item.getItemId()){
                     case R.id.menu1:
                         Toast.makeText(MainActivity.this,"11111",Toast.LENGTH_LONG).show();
                        break;
                     case R.id.menu2:
                         Toast.makeText(MainActivity.this,"222222",Toast.LENGTH_LONG).show();
                         break;
                     case R.id.menu3:
                         Toast.makeText(MainActivity.this,"3333333",Toast.LENGTH_LONG).show();
                         break;
                 }
                return false;
            }
        });
    }
}
