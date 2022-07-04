package com.example.vigorous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

public class LoadingActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        lottieAnimationView = findViewById(R.id.lottie);
        lottieAnimationView.animate().translationX(-1600).setDuration(1000).setStartDelay(10000);


        Thread t = new Thread(() ->{
            try {
                Thread.sleep(11000);

                Intent intent = new Intent(getBaseContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
    }
}