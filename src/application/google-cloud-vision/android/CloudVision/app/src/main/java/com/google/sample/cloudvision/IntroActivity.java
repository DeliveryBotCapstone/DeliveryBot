package com.google.sample.cloudvision;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

public class IntroActivity extends AppCompatActivity {
    ImageView logo;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        logo = findViewById(R.id.logo);
        //title = findViewById(R.id.title);
        // 로고 애니메이션 재생
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        logo.startAnimation(animation);

        // 스레드를 이용하여 MainActivity로 넘어감
        IntroThread introThread = new IntroThread(handler);
        introThread.start();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };
}