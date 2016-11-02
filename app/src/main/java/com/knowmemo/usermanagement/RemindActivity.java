package com.knowmemo.usermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Button;

/**
 * Created by tseng on 2016/10/24.
 */
public class RemindActivity extends AppCompatActivity {
    private CheckBox checkBox_repeat;
    private CheckBox checkBox_Mon;
    private CheckBox checkBox_Tue;
    private CheckBox checkBox_Wed;
    private CheckBox checkBox_Thu;
    private CheckBox checkBox_Fri;
    private CheckBox checkBox_Sat;
    private CheckBox checkBox_Sun;
    private TimePicker timePicker_Remind;
    private Button buttonSet;
    private  Button back_toSetting;
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_remind);
        checkBox_repeat = (CheckBox) findViewById(R.id.checkBox);
        checkBox_Mon = (CheckBox) findViewById(R.id.checkBox2);
        checkBox_Tue = (CheckBox) findViewById(R.id.checkBox3);
        checkBox_Wed = (CheckBox) findViewById(R.id.checkBox4);
        checkBox_Thu = (CheckBox) findViewById(R.id.checkBox5);
        checkBox_Fri = (CheckBox) findViewById(R.id.checkBox6);
        checkBox_Sat = (CheckBox) findViewById(R.id.checkBox7);
        checkBox_Sun = (CheckBox) findViewById(R.id.checkBox8);
        timePicker_Remind = (TimePicker) findViewById(R.id.timePicker_remind);
        buttonSet = (Button) findViewById(R.id.button13);
        back_toSetting = (Button) findViewById(R.id.button12);

        back_toSetting.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                BackToSettingTime();
            }
        });
    }
        public void BackToSettingTime(){
            setContentView(R.layout.activity_setting_time);
            Intent intent = new Intent(RemindActivity.this,SettingTimeActivity.class);
            startActivity(intent);
        }

}
