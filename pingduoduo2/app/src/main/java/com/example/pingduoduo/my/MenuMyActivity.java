package com.example.pingduoduo.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.pingduoduo.CollectActivity;
import com.example.pingduoduo.R;

public class MenuMyActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_my);
        textView = findViewById(R.id.my_collect);

        textView1 = findViewById(R.id.user_tv_nickname);
        SharedPreferences preferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        String userName = preferences.getString("userName", "000");
        textView1.setText(userName);
        textView.setOnClickListener(view -> {
            Log.i(">>>>",">>>");
            Intent intent = new Intent(MenuMyActivity.this, CollectActivity.class);
            startActivity(intent);
        });

    }
}