package com.knowmemo.usermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by tseng on 2016/10/24.
 */
public class SettingTimeActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_time);

        Button setting_remind = (Button) findViewById(R.id.button_toremind);
        Button setting_alarm = (Button)findViewById(R.id.button_toalarm);

        setting_remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToRemind();
            }
        });
        setting_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToAlarm();
            }
        });
    }
    public void jumpToRemind(){
        setContentView(R.layout.activity_remind);
        Intent intent = new Intent(SettingTimeActivity.this, RemindActivity.class);
        startActivity(intent);

    }
    public void jumpToAlarm(){
        setContentView(R.layout.activity_alarm);
        Intent intent = new Intent(SettingTimeActivity.this,AlarmActivity.class);
        startActivity(intent);

    }
}
