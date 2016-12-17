package com.knowmemo.usermanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import sqlite.SqlHelper;
import sqlite.Words;
import sqlite.tableDao;
import sqlite.GameQuestion;

import org.w3c.dom.Text;

public class SingleTestActivity extends AppCompatActivity{
    TextView question;
    TextView timer;
    TextView record;
    Button btn_ans1;
    Button btn_ans2;
    Button btn_ans3;
    Button btn_ans4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_test);

        this.layoutInit();
        timeCountDown();
    }

    private void layoutInit() {
        question = (TextView) findViewById(R.id.single_question);
        timer = (TextView) findViewById(R.id.single_test_timer);
        record = (TextView) findViewById(R.id.right_answer);
        btn_ans1 = (Button) findViewById(R.id.btn_ans1);
        btn_ans2 = (Button) findViewById(R.id.btn_ans2);
        btn_ans3 = (Button) findViewById(R.id.btn_ans3);
        btn_ans4 = (Button) findViewById(R.id.btn_ans4);
    }

    private void databaseInit() {

    }

    private void timeCountDown() {
        new CountDownTimer(30000,1000){

            @Override
            public void onFinish() {

            }

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished/1000);
            }
        }.start();
    }
}