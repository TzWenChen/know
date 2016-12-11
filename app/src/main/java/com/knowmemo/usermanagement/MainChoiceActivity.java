package com.knowmemo.usermanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import knowmemoAPI.knowmemoCallBackListener;

public class MainChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choice);

        Button learn_word = (Button) findViewById(R.id.button_learnWord);
        Button search_word = (Button) findViewById(R.id.button_searchWord);
        Button game_test = (Button) findViewById(R.id.button_gameTest);
        Button setting_time = (Button) findViewById(R.id.button_settingTime);

        search_word.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                jumpToSearchword();
            }

        });

        learn_word.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                // TODO Auto-generated method stub

                jumpToLearnWord();

            }

        });
        setting_time.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                jumpToSettingTime();
            }

        });
        game_test.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                jumpToGameTest();
            }

        });
    }

    public void jumpToLearnWord(){

        setContentView(R.layout.activity_show_word);
        //Button l= (Button)findViewById(R.id.Button02);
        Intent intent = new Intent(MainChoiceActivity.this, wordsCard.ShowWordActivity.class);
        startActivity(intent);

    }

    public void jumpToSearchword(){

        setContentView(R.layout.activity_searchbyhand);
        Intent intent = new Intent(MainChoiceActivity.this, SearchByhandActivity.class);
        startActivity(intent);

    }

    public void jumpToGameTest(){

        setContentView(R.layout.activity_game_home);
        Intent intent = new Intent(MainChoiceActivity.this, GameHomeActivity.class);
        startActivity(intent);

    }

    public void jumpToSettingTime(){

        setContentView(R.layout.activity_setting_time);
        Intent intent = new Intent(MainChoiceActivity.this, SettingTimeActivity.class);
        startActivity(intent);

    }
}



