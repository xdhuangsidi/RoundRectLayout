package com.example.root.popup;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private TextView tv;
    private PopupWindow window;
    private String [] data ={"苹果","橘子","芒果"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        window  = new PopupWindow(this);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        final View content_view = LayoutInflater.from(this).inflate(R.layout.popup_menu, null);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildPopupWindow(view);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_layout, data);//适配器

        window.setBackgroundDrawable(null);
        window.setContentView(content_view);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        RoundRectLayout rcRelativeLayout = content_view.findViewById(R.id.rc_layout);
        rcRelativeLayout.setRadius(12.5f);
        ListView listView = content_view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        TextView tv1 = content_view.findViewById(R.id.webview_more_fresh_text);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });
        TextView tv2 = content_view.findViewById(R.id.webview_more_open_browser_text);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "222", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildPopupWindow(View anchor_view) {
        window.showAsDropDown(anchor_view);
    }
}
