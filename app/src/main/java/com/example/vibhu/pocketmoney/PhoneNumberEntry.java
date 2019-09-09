package com.example.vibhu.pocketmoney;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eyalbira.loadingdots.LoadingDots;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PhoneNumberEntry extends AppCompatActivity {
    EditText editText;
    private FirebaseAuth mAuth;
    TextView otptext;
    LoadingDots loadingDots;
    private DatabaseReference mdatabase;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_entry);
        editText=findViewById(R.id.mobileEditText);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        otptext=findViewById(R.id.OtpText);
        loadingDots=findViewById(R.id.otploadingdots);
    }

    public void GetOtpOnMobile(View view){
        if(editText!=null){
            final String number=editText.getText().toString();
            if(number.length()!=10){
                Toast.makeText(this, "Enter valid Number", Toast.LENGTH_SHORT).show();
            }else{
                loadingDots.setVisibility(View.VISIBLE);
                PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential,number);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Log.w("check2", "onVerificationFailed", e);

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(PhoneNumberEntry.this, "Invalid", Toast.LENGTH_SHORT).show();
                            // ...
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // The SMS quota for the project has been exceeded
                            // ...
                        }
                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(String s) {
                        loadingDots.setVisibility(View.INVISIBLE);
                        otptext.setText("Resend OTP");
                    }
                };

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        number,        // Phone number to verify
                        30,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        this,               // Activity (for callback binding)
                        mCallBacks);
                mAuth.setLanguageCode("en");
            }
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, final String number) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Random rand = new Random();
                            int UserID1=rand.nextInt(100000);
                            int UserID2=rand.nextInt(100000);
                            String su1=UserID1+"";
                            String su2=UserID2+"";
                            su1=su1.concat(su2);
                            String code=createRandomCode(10);
                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putBoolean("verified",true);
                            editor.putString("number",number);
                            editor.putString("code",code);
                            editor.putString("userid",su1);
                            editor.apply();
                            mdatabase.child("Entries").child(number).child("ApprovalRequired").setValue("no");
                            Intent intent=new Intent(PhoneNumberEntry.this,MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
                            Log.d("check", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("check1", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    public String createRandomCode(int codeLength){
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output ;
    }

}
