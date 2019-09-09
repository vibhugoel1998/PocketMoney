package com.example.vibhu.pocketmoney;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CashActivity extends AppCompatActivity {
    private DatabaseReference mdatabase;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String number;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        number=prefs.getString("number","no number");
        button=findViewById(R.id.applyButton);
        mdatabase.child("Entries").child(number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.child("ApprovalRequired").getValue(String.class);
                if(val!=null && val.equals("yes")){
                    button.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void doFinish1(View view){
        finish();
    }

    public void ApplyForLoan(View view){
        Toast.makeText(this, "Documents sent for approval!", Toast.LENGTH_SHORT).show();
        mdatabase.child("Entries").child(number).child("ApprovalRequired").setValue("yes");
        Toast.makeText(this, "Your documents are sent for review!", Toast.LENGTH_SHORT).show();
        button.setVisibility(View.INVISIBLE);
    }
}
