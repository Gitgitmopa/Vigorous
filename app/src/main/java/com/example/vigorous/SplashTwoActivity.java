package com.example.vigorous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class SplashTwoActivity extends AppCompatActivity {

    Button btn_next2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_two);

        btn_next2 = findViewById(R.id.btn_next2);

        btn_next2.setOnClickListener(v -> {
            Intent i = new Intent(SplashTwoActivity.this,SignAndRegActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_right_animation,R.anim.slide_out_left);
            finish();
        });
    }
}