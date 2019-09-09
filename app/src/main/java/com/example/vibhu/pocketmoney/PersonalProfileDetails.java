package com.example.vibhu.pocketmoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PersonalProfileDetails extends AppCompatActivity {
    boolean firstTime;
    private DatabaseReference mdatabase;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String number;
    EditText editfirstName;
    EditText editsecondName;
    EditText editfathersName;
    EditText editmothersname;
    EditText editDOB;
    EditText editGender;
    EditText editMaritial;
    EditText editEmail;
    EditText editHighest;
    EditText editInstitute;
    EditText editWorkingOrNot;
    EditText editSpouseCompany;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile_details);
        editfirstName=findViewById(R.id.FirstName);
        editsecondName=findViewById(R.id.SecondName);
        editfathersName=findViewById(R.id.FathersName);
        editmothersname=findViewById(R.id.MothersName);
        editDOB=findViewById(R.id.DOB);
        editGender=findViewById(R.id.Gender);
        editMaritial=findViewById(R.id.MaritalStatus);
        editEmail=findViewById(R.id.Email);
        editHighest=findViewById(R.id.HighestEducation);
        editInstitute=findViewById(R.id.Institute);
        editWorkingOrNot=findViewById(R.id.SpouseWorking);
        editSpouseCompany=findViewById(R.id.SpouseCompany);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        number=prefs.getString("number","no number");
        Intent intent=getIntent();
        firstTime=intent.getBooleanExtra("first",true);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                editDOB.setText(sdf.format(myCalendar.getTime()));
            }
        };
        editDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(PersonalProfileDetails.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        fetchSavedData();
    }

    private void fetchSavedData() {
        if(!number.equals("no number")){
            if(mdatabase.child("Entries")!=null) {
                if (mdatabase.child("Entries").child(number) != null) {
                    mdatabase.child("Entries").child(number).child("PersonalProfile").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String firstName=dataSnapshot.child("firstName").getValue(String.class);
                            String secondName=dataSnapshot.child("secondName").getValue(String.class);
                            String father=dataSnapshot.child("father").getValue(String.class);
                            String mother=dataSnapshot.child("mother").getValue(String.class);
                            String dob=dataSnapshot.child("dob").getValue(String.class);
                            String gender=dataSnapshot.child("gender").getValue(String.class);
                            String maritial=dataSnapshot.child("maritial").getValue(String.class);
                            String email=dataSnapshot.child("email").getValue(String.class);
                            String highest=dataSnapshot.child("highedu").getValue(String.class);
                            String institute=dataSnapshot.child("institute").getValue(String.class);
                            String spouseornot=dataSnapshot.child("spouseornot").getValue(String.class);
                            String spouseCompany=dataSnapshot.child("spousecompany").getValue(String.class);
                            if(firstName!=null){
                                editfirstName.setText(firstName);
                            }
                            if(secondName!=null){
                                editsecondName.setText(secondName);
                            }
                            if(father!=null){
                                editfathersName.setText(father);
                            }
                            if(mother!=null){
                                editmothersname.setText(mother);
                            }
                            if(dob!=null){
                                editDOB.setText(dob);
                            }
                            if(gender!=null){
                                editGender.setText(gender);
                            }
                            if(maritial!=null){
                                editMaritial.setText(maritial);
                            }
                            if(email!=null){
                                editEmail.setText(email);
                            }
                            if(highest!=null){
                                editHighest.setText(highest);
                            }
                            if(institute!=null){
                                editInstitute.setText(institute);
                            }
                            if(spouseornot!=null){
                                editWorkingOrNot.setText(spouseornot);
                            }
                            if(spouseCompany!=null){
                                editSpouseCompany.setText(spouseCompany);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        }
    }

    public void SubmitPersonal(View view){
        if(!number.equals("no number")){
            String firstN=editfirstName.getText().toString();
            String secondN=editsecondName.getText().toString();
            String fatherN=editfathersName.getText().toString();
            String motherN=editmothersname.getText().toString();
            String dobN=editDOB.getText().toString();
            String gendern=editGender.getText().toString();
            String maritialn=editMaritial.getText().toString();
            String emailn=editEmail.getText().toString();
            String highestn=editHighest.getText().toString();
            String instituten=editInstitute.getText().toString();
            String spouseornotn=editWorkingOrNot.getText().toString();
            String spouseCompanyn=editSpouseCompany.getText().toString();
            if(firstN!=null){
                if(!isEmpty(editfirstName)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("firstName").setValue(firstN);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("firstName").setValue(null);
                }

            }
            if(secondN!=null){
                if(!isEmpty(editsecondName)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("secondName").setValue(secondN);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("secondName").setValue(null);
                }

            }
            if(fatherN!=null){
                if(!isEmpty(editfathersName)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("father").setValue(fatherN);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("father").setValue(null);
                }

            }
            if(motherN!=null){
                if(!isEmpty(editmothersname)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("mother").setValue(motherN);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("mother").setValue(null);
                }
            }
            if(dobN!=null){
                if(!isEmpty(editDOB)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("dob").setValue(dobN);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("dob").setValue(null);
                }

            }
            if(gendern!=null){
                if(!isEmpty(editGender)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("gender").setValue(gendern);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("gender").setValue(null);
                }

            }
            if(maritialn!=null){
                if(!isEmpty(editMaritial)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("maritial").setValue(maritialn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("maritial").setValue(null);
                }

            }
            if(emailn!=null){
                if(!isEmpty(editEmail)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("email").setValue(emailn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("email").setValue(null);
                }

            }
            if(highestn!=null){
                if(!isEmpty(editHighest)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("highedu").setValue(highestn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("highedu").setValue(null);
                }

            }
            if(instituten!=null){
                if(!isEmpty(editInstitute)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("institute").setValue(instituten);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("institute").setValue(null);
                }

            }
            if(spouseornotn!=null){
                if(!isEmpty(editWorkingOrNot)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("spouseornot").setValue(spouseornotn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("spouseornot").setValue(null);
                }
            }
            if(spouseCompanyn!=null){
                if(!isEmpty(editSpouseCompany)){
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("spousecompany").setValue(spouseCompanyn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("PersonalProfile").child("spousecompany").setValue(null);
                }
            }

        }
        Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();
        boolean checkFullFilled=FullFilled();
        if(checkFullFilled){
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("checkPersonal",true);
            editor.apply();
        }
        else{
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("checkPersonal",false);
            editor.apply();
        }
        finish();
    }

    private boolean FullFilled() {
        boolean FirstC=isEmpty(editfirstName);
        boolean Secondc=isEmpty(editsecondName);
        boolean Fatherc=isEmpty(editfathersName);
        boolean Motherc=isEmpty(editmothersname);
        boolean dobc=isEmpty(editDOB);
        boolean genderc=isEmpty(editGender);
        boolean maritialc=isEmpty(editMaritial);
        boolean emailc=isEmpty(editEmail);
        boolean highestc=isEmpty(editHighest);
        boolean institutec=isEmpty(editInstitute);
        boolean isWorkingc=isEmpty(editWorkingOrNot);
        boolean SpouseCompanyc=isEmpty(editSpouseCompany);
        if(FirstC || Secondc || Fatherc || Motherc || dobc || genderc || maritialc || emailc || highestc || institutec || isWorkingc || SpouseCompanyc){
            return false;
        }
        else{
            return true;
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        boolean hey=TextUtils.isEmpty(str);
        if(!hey){
            if(str.equals("")){
                return true;
            }
        }
        return hey;
    }
}
