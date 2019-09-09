package com.example.vibhu.pocketmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    private Handler handler = new Handler();
    private static int SPLASH_TIME_OUT = 10000;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        imageView=findViewById(R.id.logoimg);
        imageView.startAnimation(animZoomIn);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                boolean check=prefs.getBoolean("verified",false);
                Log.d("spf",check+"");
                    if(check){
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putBoolean("first",false);
                        editor.apply();
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(i);
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                         finish();
                    }
                    else{
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putBoolean("first",true);
                        editor.apply();
                        boolean helper=prefs.getBoolean("PermissionGranted",false);
                        if(helper){
                            Intent i = new Intent(SplashScreen.this, InfoActivity.class);
                            startActivity(i);
                            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        }else{
                            Intent i = new Intent(SplashScreen.this, PermissionsActivity.class);
                            startActivity(i);
                            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        }

                        finish();
                    }
                }
        }, SPLASH_TIME_OUT);
    }
}
