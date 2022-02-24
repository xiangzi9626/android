package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private int images[] = {
            R.drawable.apple_pic,
            R.drawable.banana_pic,
            R.drawable.cherry_pic,
            R.drawable.grape_pic,
            R.drawable.mango_pic,
            R.drawable.orange_pic,
            R.drawable.pear_pic,
            R.drawable.pineapple_pic,
            R.drawable.strawberry_pic,
            R.drawable.watermelon_pic
    };
    private String[] names = {"苹果", "香蕉", "樱桃", "葡萄", "芒果", "橙子", "梨", "菠萝", "草莓", "西瓜"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv);
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv=view.findViewById(R.id.fruitname);
                Toast.makeText(MainActivity.this,tv.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }

    class MyAdapter extends BaseAdapter {
        /**
         * 数据源项的个数
         *
         * @return
         */
        @Override
        public int getCount() {
            return names.length;
        }

        /**
         * 项
         *
         * @param i
         * @return
         */
        @Override
        public Object getItem(int i) {
            return null;
        }

        /**
         * 项ID
         *
         * @param i
         * @return
         */
        @Override
        public long getItemId(int i) {
            return 0;
        }

        /**
         * 项显示的view
         *
         * @param i
         * @param view
         * @param viewGroup
         * @return
         */
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1;
            ViewHolder holder;
            if (view == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                view1 = layoutInflater.inflate(R.layout.fruit_item, viewGroup, false);
                holder = new ViewHolder(view1);
                view1.setTag(holder);
            } else {
                view1 = view;
                holder = (ViewHolder) view1.getTag();
            }

            ImageView iv = holder.getIv();
            TextView tv = holder.getTv();
            iv.setImageResource(images[i]);
            tv.setText(names[i]);
            return view1;
        }
    }

    class ViewHolder {
        private View view;
        private ImageView iv;
        private TextView tv;

        public ViewHolder(View view) {
            this.view = view;
        }

        public ImageView getIv() {
            if (iv == null) {
                iv = view.findViewById(R.id.fruit_image);
            }
            return iv;
        }

        public TextView getTv() {
            if (tv == null) {
                tv = view.findViewById(R.id.fruitname);
            }
            return tv;
        }
    }
}