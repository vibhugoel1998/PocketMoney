package com.example.vibhu.pocketmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayActivity extends AppCompatActivity {
    CardView personalProf;
    CardView professionalprof;
    CardView CurrentAddress;
    CardView profilepicture;
    boolean firstTime;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    LinearLayout linearPersonal;
    LinearLayout linearProfessional;
    LinearLayout linearofresidential;
    LinearLayout linearofpicture;
    LinearLayout linearofDocument;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        linearPersonal=findViewById(R.id.Linearofpersonal);
        linearProfessional=findViewById(R.id.linearofprofessional);
        linearofresidential=findViewById(R.id.linearofresidential);
        linearofpicture=findViewById(R.id.linearofpicture);
        linearofDocument=findViewById(R.id.linearofdocuments);
        Intent intent=getIntent();
        firstTime=intent.getBooleanExtra("first",true);
        personalProf=findViewById(R.id.personalprof);
        personalProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DisplayActivity.this,PersonalProfileDetails.class);
                intent.putExtra("first",firstTime);
                startActivity(intent);
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
            }
        });
        professionalprof=findViewById(R.id.professionalprof);
        professionalprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DisplayActivity.this,ProfessionalProfileDetails.class);
                intent.putExtra("first",firstTime);
                startActivity(intent);
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
            }
        });
        CurrentAddress=findViewById(R.id.CurrentAddress);
        CurrentAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DisplayActivity.this,CurrentResidentialAddress.class);
                intent.putExtra("first",firstTime);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            }
        });
        profilepicture=findViewById(R.id.ProfPicture);
        profilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DisplayActivity.this,ProfilePicture.class);
                intent.putExtra("first",firstTime);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            }
        });
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean checkPersonal=prefs.getBoolean("checkPersonal",false);
        boolean checkProfessional=prefs.getBoolean("checkProfessional",false);
        boolean checkAddress=prefs.getBoolean("checkAddress",false);
        boolean checkPicture=prefs.getBoolean("checkPicture",false);
        boolean checkDocument=prefs.getBoolean("checkDocuments",false);
        if(checkPersonal){
            linearPersonal.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearPersonal.setBackgroundResource(R.color.white);
        }
        if(checkProfessional){
            linearProfessional.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearProfessional.setBackgroundResource(R.color.white);
        }
        if(checkAddress){
            linearofresidential.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearofresidential.setBackgroundResource(R.color.white);
        }
        if(checkPicture){
            linearofpicture.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearofpicture.setBackgroundResource(R.color.white);
        }
        if(checkDocument){
            linearofDocument.setBackgroundResource(R.color.backSuccess);
        }else{
            linearofDocument.setBackgroundResource(R.color.white);
        }

        if(checkAddress && checkPersonal && checkPicture && checkProfessional && checkDocument){
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("checkAllDone",true);
            editor.apply();
        }
        else{
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("checkAllDone",false);
            editor.apply();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean checkPersonal=prefs.getBoolean("checkPersonal",false);
        boolean checkProfessional=prefs.getBoolean("checkProfessional",false);
        boolean checkAddress=prefs.getBoolean("checkAddress",false);
        boolean checkPicture=prefs.getBoolean("checkPicture",false);
        boolean checkDocument=prefs.getBoolean("checkDocuments",false);
        if(checkPersonal){
            linearPersonal.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearPersonal.setBackgroundResource(R.color.white);
        }
        if(checkProfessional){
            linearProfessional.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearProfessional.setBackgroundResource(R.color.white);
        }
        if(checkAddress){
            linearofresidential.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearofresidential.setBackgroundResource(R.color.white);
        }
        if(checkPicture){
            linearofpicture.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearofpicture.setBackgroundResource(R.color.white);
        }
        if(checkDocument){
            linearofDocument.setBackgroundResource(R.color.backSuccess);
        }
        else{
            linearofDocument.setBackgroundResource(R.color.white);
        }

        if(checkAddress && checkPersonal && checkPicture && checkProfessional && checkDocument){
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("checkAllDone",true);
            editor.apply();
        }
        else{
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("checkAllDone",false);
            editor.apply();
        }

    }

    public void PaymentDocuments(View view){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean checkPersonal=prefs.getBoolean("checkPersonal",false);
        boolean checkProfessional=prefs.getBoolean("checkProfessional",false);
        boolean checkAddress=prefs.getBoolean("checkAddress",false);
        boolean checkPicture=prefs.getBoolean("checkPicture",false);
        if(checkPersonal && checkAddress && checkPicture && checkProfessional){
            Intent intent=new Intent(DisplayActivity.this,ChooseDocumentMethod.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
        }
        else{
            Toast.makeText(this, "Complete the remaining profile first to upload the documents", Toast.LENGTH_SHORT).show();
        }

    }



}
