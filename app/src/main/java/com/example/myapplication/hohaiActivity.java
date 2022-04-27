package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hohaiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hohai);
        Button startfirstActivity = (Button) findViewById(R.id.button);
        Button startsecondActivity = (Button) findViewById(R.id.button2);
        Button startthirdActivity = (Button) findViewById(R.id.button3);
        startfirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.hhu.edu.cn/"));
                startActivity(intent);

            }
        });
        startsecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://my.hhu.edu.cn/portal-web/guest/hhdx/index.html?t=1633774738815"));
                startActivity(intent);
            }
        });
        startthirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://202.119.114.197/login"));
                startActivity(intent);
            }
        });

    }
}