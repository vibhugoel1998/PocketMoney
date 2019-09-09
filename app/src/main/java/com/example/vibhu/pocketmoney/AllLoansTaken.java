package com.example.vibhu.pocketmoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AllLoansTaken extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_loans_taken);
        Toast.makeText(this, "Currently no loans are taken!", Toast.LENGTH_SHORT).show();
    }

    public void doFinish4(View view){
        finish();
    }
}
