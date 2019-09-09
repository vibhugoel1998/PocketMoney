package com.example.vibhu.pocketmoney;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class LoanCalculator extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    SeekBar dateSeekbar;
    TextView dateTextView;
    TextView amountreturntextview;
    TextView datereturntextview;
    int amount=8000;
    int days=7;
    int processing=500;
    int Stamp_Duty=100;
    TextView interestRate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calculator);
        interestRate=findViewById(R.id.interestRate);
        seekBar=findViewById(R.id.cashseekbar);
        textView=findViewById(R.id.cashtext);
        amountreturntextview=findViewById(R.id.returntext);
        datereturntextview=findViewById(R.id.duedatetext);
        DateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 7);
        Date currentDatePlusOne = c.getTime();
        datereturntextview.setText("Repayment on "+dateFormat.format(currentDatePlusOne));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i=i/1000;
                i=i*1000;
                Log.d("heyVibhu",i+"");
                amount=i+8000;
                int sum=0;
                if(days<=10){
                    sum=(int)(((6.0/100)*amount));
                }
                else if(days>10 && days<=20){
                    sum=(int)(((12.0/100)*amount));
                }
                else{
                    sum=(int)(((18.0/100)*amount));
                }
                sum=sum+100*days;
                int ans=(int)(sum+amount+processing+Stamp_Duty);
                amountreturntextview.setText("₹"+String.valueOf(ans));
                textView.setText("₹"+String.valueOf(i+8000));
                interestRate.setText("Interest Amount ₹"+String.valueOf(sum));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dateSeekbar=findViewById(R.id.dateseekbar);
        dateTextView=findViewById(R.id.datetext);
        dateSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                dateTextView.setText(String.valueOf(i+7)+" Days");
                days=i+7;
                int sum=0;
                if(days<=10){
                    sum=(int)(((6.0/100)*amount));
                }
                else if(days>10 && days<=20){
                    sum=(int)(((12.0/100)*amount));
                }
                else{
                    sum=(int)(((18.0/100)*amount));
                }
                sum=sum+100*days;
                int ans=(int)(sum+amount+processing+Stamp_Duty);
                amountreturntextview.setText("₹"+String.valueOf(ans));
                DateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy");
                Date currentDate = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(currentDate);
                c.add(Calendar.DATE, days);
                Date currentDatePlusOne = c.getTime();
                datereturntextview.setText("Repayment on "+dateFormat.format(currentDatePlusOne));
                interestRate.setText("Interest Amount ₹"+String.valueOf(sum));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    public void doFinish(View view){
        finish();
    }
}
