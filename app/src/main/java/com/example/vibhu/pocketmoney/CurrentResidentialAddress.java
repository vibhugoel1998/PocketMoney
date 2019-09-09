package com.example.vibhu.pocketmoney;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurrentResidentialAddress extends AppCompatActivity {
    private DatabaseReference mdatabase;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String number;
    EditText editDoor;
    EditText editStreet;
    EditText editLandmark;
    EditText editCity;
    EditText editState;
    EditText editPincode;
    EditText editResidence;
    EditText editHouseType;
    EditText editRef1Name;
    EditText editRef1House;
    EditText editRef1No;
    EditText editRef2Name;
    EditText editRef2House;
    EditText editRef2No;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_residential_address);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        number=prefs.getString("number","no number");
        editDoor=findViewById(R.id.DoorNo);
        editStreet=findViewById(R.id.Streetname);
        editLandmark=findViewById(R.id.Landmark);
        editCity=findViewById(R.id.City);
        editState=findViewById(R.id.State);
        editPincode=findViewById(R.id.PinCode);
        editResidence=findViewById(R.id.ResidentialType);
        editHouseType=findViewById(R.id.HouseType);
        editRef1Name=findViewById(R.id.Reference1Name);
        editRef1House=findViewById(R.id.Reference1House);
        editRef1No=findViewById(R.id.Reference1Mobile);
        editRef2Name=findViewById(R.id.Reference2Name);
        editRef2House=findViewById(R.id.Reference2House);
        editRef2No=findViewById(R.id.Reference2Mobile);

        fetchSavedData();
    }

    private void fetchSavedData() {
        if(!number.equals("no number")){
            if(mdatabase.child("Entries")!=null) {
                if (mdatabase.child("Entries").child(number) != null) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String doorno=dataSnapshot.child("door").getValue(String.class);
                            String street=dataSnapshot.child("street").getValue(String.class);
                            String landmark=dataSnapshot.child("landmark").getValue(String.class);
                            String city=dataSnapshot.child("city").getValue(String.class);
                            String state=dataSnapshot.child("state").getValue(String.class);
                            String pin=dataSnapshot.child("pin").getValue(String.class);
                            String resisdence=dataSnapshot.child("type").getValue(String.class);
                            String housetype=dataSnapshot.child("housetype").getValue(String.class);
                            String ref1name=dataSnapshot.child("ref1name").getValue(String.class);
                            String ref1house=dataSnapshot.child("ref1house").getValue(String.class);
                            String ref1no=dataSnapshot.child("ref1no").getValue(String.class);
                            String ref2name=dataSnapshot.child("ref2name").getValue(String.class);
                            String ref2house=dataSnapshot.child("ref2house").getValue(String.class);
                            String ref2no=dataSnapshot.child("ref2no").getValue(String.class);
                            if(doorno!=null){
                                editDoor.setText(doorno);
                            }
                            if(street!=null){
                                editStreet.setText(street);
                            }
                            if(landmark!=null){
                                editLandmark.setText(landmark);
                            }
                            if(city!=null){
                                editCity.setText(city);
                            }
                            if(state!=null){
                                editState.setText(state);
                            }
                            if(pin!=null){
                                editPincode.setText(pin);
                            }
                            if(resisdence!=null){
                                editResidence.setText(resisdence);
                            }
                            if(housetype!=null){
                                editHouseType.setText(housetype);
                            }
                            if(ref1name!=null){
                                editRef1Name.setText(ref1name);
                            }
                            if(ref1house!=null){
                                editRef1House.setText(ref1house);
                            }
                            if(ref1no!=null){
                                editRef1No.setText(ref1no);
                            }
                            if(ref2name!=null){
                                editRef2Name.setText(ref2name);
                            }
                            if(ref2house!=null){
                                editRef2House.setText(ref2house);
                            }
                            if(ref2no!=null){
                                editRef2No.setText(ref2no);
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

    public void SaveAddress(View view){
        if(!number.equals("no number")){
            String doorn=editDoor.getText().toString();
            String streetn=editStreet.getText().toString();
            String landmarkn=editLandmark.getText().toString();
            String cityn=editCity.getText().toString();
            String staten=editState.getText().toString();
            String pinn=editPincode.getText().toString();
            String residencen=editResidence.getText().toString();
            String housetypen=editHouseType.getText().toString();
            String ref1namen=editRef1Name.getText().toString();
            String ref1housen=editRef1House.getText().toString();
            String ref1non=editRef1No.getText().toString();
            String ref2namen=editRef2Name.getText().toString();
            String ref2housen=editRef2House.getText().toString();
            String ref2non=editRef2No.getText().toString();
            if(housetypen!=null){
                if(!isEmpty(editHouseType)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("housetype").setValue(housetypen);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("housetype").setValue(null);
                }
            }
            if(ref1namen!=null){
                if(!isEmpty(editRef1Name)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref1name").setValue(ref1namen);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref1name").setValue(null);
                }
            }
            if(ref1housen!=null){
                if(!isEmpty(editRef1House)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref1house").setValue(ref1housen);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref1house").setValue(null);
                }
            }
            if(ref1non!=null){
                if(!isEmpty(editRef1No)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref1no").setValue(ref1non);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref1no").setValue(null);
                }
            }
            if(ref2namen!=null){
                if(!isEmpty(editRef2Name)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref2name").setValue(ref2namen);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref2name").setValue(null);
                }
            }
            if(ref2housen!=null){
                if(!isEmpty(editRef2House)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref2house").setValue(ref2housen);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref2house").setValue(null);
                }
            }
            if(ref2non!=null){
                if(!isEmpty(editRef2No)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref2no").setValue(ref2non);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("ref2no").setValue(null);
                }
            }
            if(doorn!=null){
                if(!isEmpty(editDoor)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("door").setValue(doorn);
                }else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("door").setValue(null);
                }
            }
            if(streetn!=null){
                if(!isEmpty(editStreet)) {
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("street").setValue(streetn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("street").setValue(null);
                }
            }
            if(landmarkn!=null){
                if(!isEmpty(editLandmark)){
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("landmark").setValue(landmarkn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("landmark").setValue(null);
                }

            }
            if(cityn!=null){
                if(!isEmpty(editCity)){
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("city").setValue(cityn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("city").setValue(null);
                }

            }
            if(staten!=null){
                if(!isEmpty(editState)){
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("state").setValue(staten);
                }
                else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("state").setValue(null);
                }

            }
            if(pinn!=null){
                if(!isEmpty(editPincode)){
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("pin").setValue(pinn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("pin").setValue(null);
                }

            }
            if(residencen!=null){
                if(!isEmpty(editResidence)){
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("type").setValue(residencen);
                }
                else{
                    mdatabase.child("Entries").child(number).child("AddressDetails").child("type").setValue(null);
                }

            }
            boolean checkFullFilled=FullFilled();
            if(checkFullFilled){
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkAddress",true);
                editor.apply();
            }
            else{
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkAddress",false);
                editor.apply();
            }
            Toast.makeText(this, "Details saved", Toast.LENGTH_SHORT).show();
            finish();

        }

    }

    private boolean FullFilled() {
        boolean doorc=isEmpty(editDoor);
        boolean streetc=isEmpty(editStreet);
        boolean landmarkc=isEmpty(editLandmark);
        boolean cityc=isEmpty(editCity);
        boolean statec=isEmpty(editState);
        boolean pinc=isEmpty(editPincode);
        boolean residencec=isEmpty(editResidence);
        boolean housetypec=isEmpty(editHouseType);
        boolean ref1namec=isEmpty(editRef1Name);
        boolean ref1housec=isEmpty(editRef1House);
        boolean ref1noc=isEmpty(editRef1No);
        boolean ref2namec=isEmpty(editRef2Name);
        boolean ref2house=isEmpty(editRef2House);
        boolean ref2no=isEmpty(editRef2No);
        if(doorc || streetc || landmarkc || cityc || statec || pinc || residencec || housetypec || ref1namec || ref1housec || ref1noc || ref2namec || ref2house || ref2no){
            return false;
        }
        else{
            return true;
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        boolean hey= TextUtils.isEmpty(str);
        if(!hey){
            if(str.equals("")){
                return true;
            }
        }
        return hey;
    }
}
