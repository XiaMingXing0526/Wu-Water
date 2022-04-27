package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Object> ObjectList=new ArrayList<>();
    private  String[] data={ "shuiwenyuan","hohai_university","flowvelo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObject();
        ObjectAdapter adapter = new ObjectAdapter(MainActivity.this, R.layout.object_layout, ObjectList);
        ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object=ObjectList.get(position);
                Intent intent1 = new Intent(MainActivity.this, CameraActivity.class);
                Intent intent2 = new Intent(MainActivity.this, shuiwenActivity.class);
                Intent intent3 = new Intent(MainActivity.this, hohaiActivity.class);
                switch(object.getName()){
                    case "摄影测流":
                        startActivity(intent1);
                        break;
                    case "水文院":
                        startActivity(intent2);
                        break;
                    case "河海大学":
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
    public void initObject(){

            Object flowvelo=new Object("摄影测流",R.drawable.water_1);
            ObjectList.add(flowvelo);
            Object shuiwenyuan=new Object("水文院",R.drawable.shuiwenyuan);
            ObjectList.add(shuiwenyuan);
            Object hohai_university=new Object("河海大学",R.drawable.hohai_university);
            ObjectList.add(hohai_university);
    }
}