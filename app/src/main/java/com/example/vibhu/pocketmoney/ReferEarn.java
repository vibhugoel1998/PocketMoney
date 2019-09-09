package com.example.vibhu.pocketmoney;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReferEarn extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);
        textView=findViewById(R.id.referralcode);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String code=prefs.getString("code","uv5pxy29rd");
        textView.setText(code);
    }

    public void doFinish5(View view){
        finish();
    }
}
