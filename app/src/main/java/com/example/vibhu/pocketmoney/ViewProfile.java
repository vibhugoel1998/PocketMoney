package com.example.vibhu.pocketmoney;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {
    TextView textFirstName;
    TextView textSecondName;
    TextView textCustomerID;
    TextView textMobileNumber;
    TextView textEmailID;
    TextView textCurrentAddress;
    TextView textCompanyDetails;
    TextView textBankDetails;
    private DatabaseReference mdatabase;
    String number;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        textFirstName=findViewById(R.id.viewFirstName);
        textSecondName=findViewById(R.id.viewSecondName);
        textCustomerID=findViewById(R.id.viewCustomerID);
        textMobileNumber=findViewById(R.id.viewMobileNo);
        textEmailID=findViewById(R.id.viewEmailID);
        textBankDetails=findViewById(R.id.ViewBankDetails);
        textCurrentAddress=findViewById(R.id.viewCurrentAddress);
        textCompanyDetails=findViewById(R.id.viewCompanyDetails);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        number=prefs.getString("number","no number");
        String hidden="XXXXXX"+number.substring(6,10);
        userid=prefs.getString("userid","0000000000");
        textCustomerID.setText(userid);
        textMobileNumber.setText(hidden);
        fetchSavedData();




    }

    private void fetchSavedData() {
        if(!number.equals("no number")){
            if(mdatabase.child("Entries")!=null) {
                if (mdatabase.child("Entries").child(number) != null) {
                    mdatabase.child("Entries").child(number).child("PersonalProfile").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String firstNamef=dataSnapshot.child("firstName").getValue(String.class);
                            String secondNamef=dataSnapshot.child("secondName").getValue(String.class);
                            String emailf=dataSnapshot.child("email").getValue(String.class);
                            if(firstNamef!=null){
                                textFirstName.setText(firstNamef);
                            }
                            if(secondNamef!=null){
                                textSecondName.setText(secondNamef);
                            }
                            if(emailf!=null){
                                textEmailID.setText(emailf);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                   mdatabase.child("Entries").child(number).child("ProfessionalProfile").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           String companyf=dataSnapshot.child("company").getValue(String.class);
                           if(companyf!=null){
                               textCompanyDetails.setText(companyf);
                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
                   mdatabase.child("Entries").child(number).child("AddressDetails").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           String doorf=dataSnapshot.child("door").getValue(String.class);
                           String streetf=dataSnapshot.child("street").getValue(String.class);
                           String cityf=dataSnapshot.child("city").getValue(String.class);
                           String statef=dataSnapshot.child("state").getValue(String.class);
                           textCurrentAddress.setText(doorf+","+streetf+","+cityf+","+statef);
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
                   mdatabase.child("Entries").child(number).child("DocBankType").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           String docbank=dataSnapshot.getValue(String.class);
                           textBankDetails.setText(docbank);
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
                }
            }
        }
    }

    public void OpenEditEmail(View view){
        Intent intent=new Intent(ViewProfile.this,PersonalProfileDetails.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }

    public void CopyUID(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", userid);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "ID copied!", Toast.LENGTH_SHORT).show();
    }

    public void ChangeMobile(View view){

    }

    public void doFinish(View view){
        finish();
    }
}
