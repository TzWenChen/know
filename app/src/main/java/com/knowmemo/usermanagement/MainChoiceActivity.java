package com.knowmemo.usermanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainChoiceActivity extends AppCompatActivity {

    private long lastBackTime = 0;
    //当前按下返回键的系统时间
    private long currentBackTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choice);

        Button learn_word = (Button) findViewById(R.id.button_learnWord);
        Button search_word = (Button) findViewById(R.id.button_searchWord);
        Button game_test = (Button) findViewById(R.id.button_gameTest);
        Button setting_time = (Button) findViewById(R.id.button_settingTime);
        Button favor= (Button) findViewById(R.id.button_favor);

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
        favor.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                jumpToFavor();
            }

        });
    }

    public void jumpToLearnWord(){

        setContentView(R.layout.activity_level_choice);
        Intent intent = new Intent(MainChoiceActivity.this, LevelChoiceActivity.class);
        startActivity(intent);

    }
    public void jumpToFavor(){

        setContentView(R.layout.activity_myfavorite);
        Intent intent = new Intent(MainChoiceActivity.this, FavoriteActivity.class);
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



