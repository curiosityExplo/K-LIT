package com.example.iidea8.k_lit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Abhigyan on 7/25/2015.
 */
public class SplashActivity1 extends Activity {


    private final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity1.this, SplashActivity2.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
