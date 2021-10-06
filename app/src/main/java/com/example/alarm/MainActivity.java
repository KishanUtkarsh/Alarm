package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button button;
    private RecyclerView recyclerView;
    private static ArrayList<Alarm> allAlarms;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new RecyclerViewAdapter();


        if(allAlarms == null){
            allAlarms = new ArrayList<>();
        }

        recyclerView = (RecyclerView) findViewById(R.id.AlarmRecView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAlarms(allAlarms);

        button = (Button) findViewById(R.id.AddNewAlarmbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AlarmActivity.class);
                startActivity(intent);
            }
        });

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");
            if(bundle != null){
                int hour = bundle.getInt("hour",-1);
                int min = bundle.getInt("min",-1);
                if(hour != -1){
                    if(min!= -1){
                        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,min);
                        alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE,"Hello from the Ohter Side");
                        Alarm alarm = new Alarm(hour,min,"hello from the other side");
                        allAlarms.add(alarm);
                        adapter.setAlarms(allAlarms);
                        startActivity(alarmIntent);
                    }
                }
            }else{
                Log.d(TAG, "onCreate: Bundle is Null");
            }

        }catch (Exception e){
            Log.d(TAG, "onCreate: Exception Happening");
        }
    }
}