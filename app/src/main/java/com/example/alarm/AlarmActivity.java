package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    private static final String TAG = "AlarmActivity";

    private Button button;
    private EditText HourEdittext , MinEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        HourEdittext = (EditText) findViewById(R.id.hourEditText);
        MinEditText = (EditText) findViewById(R.id.MinEditText);
        button = (Button) findViewById(R.id.Alarmbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAlarm();
            }
        });

    }
    private void addNewAlarm(){
        Log.d(TAG, "addNewAlarm: Started");
        int hour = Integer.valueOf(HourEdittext.getText().toString());
        int min = Integer.valueOf(MinEditText.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putInt("hour" , hour);
        bundle.putInt("min" , min);

        Intent intent = new Intent(AlarmActivity.this ,MainActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("bundle",bundle);
        startActivity(intent);

    }
}