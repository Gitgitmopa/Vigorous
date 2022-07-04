package com.example.vigorous;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //Variable
    private static final int SPLASH = 3500;
    Animation topAnim;
    ImageView imageView;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Assign
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        imageView = findViewById(R.id.logo);

        imageView.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                preferences = getSharedPreferences("pref",MODE_PRIVATE);
                boolean firstIme = preferences.getBoolean("firstTime",true); //To get stored data.

                if (firstIme){
                    //To Stored data.
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("firstTime",false);
                    editor.apply();
                    Intent intent =  new Intent(MainActivity.this,SplashActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Intent intent =  new Intent(MainActivity.this,SignAndRegActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH);

    }

}