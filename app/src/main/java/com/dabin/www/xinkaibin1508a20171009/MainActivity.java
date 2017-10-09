package com.dabin.www.xinkaibin1508a20171009;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.util.Random;
/*
* TIme: 2017/4/9
* Author: Dabin
*/
public class MainActivity extends AppCompatActivity {
    private MyView myView;
    private Button mButton;
    private Button mReset;
    private Button mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.my_view);
        mButton = (Button) findViewById(R.id.start);
        mReset = (Button) findViewById(R.id.reset);
        mColor = (Button) findViewById(R.id.button2);
        //开始
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = (int) (Math.random() * 100);
                myView.setPercent(n);
            }
        });
        //重置
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setReset(78);
            }
        });
        //改变颜色
        mColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 随机颜色
                Random random = new Random();
                int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
                myView.setmSmallColor(ranColor);
            }
        });


    }
}
