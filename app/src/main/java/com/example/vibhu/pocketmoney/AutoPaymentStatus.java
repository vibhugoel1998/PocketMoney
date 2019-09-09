package com.example.vibhu.pocketmoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AutoPaymentStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_payment_status);
    }

    public void doFinishAuto(View view){
        finish();
    }
}
