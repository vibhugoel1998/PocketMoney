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

public class ProfessionalProfileDetails extends AppCompatActivity {
    private DatabaseReference mdatabase;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String number;
    EditText editCompany;
    EditText editDesignation;
    EditText editOfficeArea;
    EditText editOfficeCity;
    EditText editOfficePinCode;
    EditText editExperience;
    EditText editPanCard;
    EditText editSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_profile_details);
        mdatabase= FirebaseDatabase.getInstance().getReference();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        number=prefs.getString("number","no number");
        editCompany=findViewById(R.id.CompanyName);
        editDesignation=findViewById(R.id.Designation);
        editOfficeArea=findViewById(R.id.OfficeArea);
        editOfficeCity=findViewById(R.id.OfficeCity);
        editOfficePinCode=findViewById(R.id.OfficePincode);
        editExperience=findViewById(R.id.TotalExperience);
        editPanCard=findViewById(R.id.PanNumber);
        editSalary=findViewById(R.id.MonthlySalary);

        findSavedData();

    }

    private void findSavedData() {
        if(!number.equals("no number")){
            if(mdatabase.child("Entries")!=null) {
                if (mdatabase.child("Entries").child(number) != null) {
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String company=dataSnapshot.child("company").getValue(String.class);
                            String designation=dataSnapshot.child("designation").getValue(String.class);
                            String officeArea=dataSnapshot.child("officeArea").getValue(String.class);
                            String officeCity=dataSnapshot.child("officeCity").getValue(String.class);
                            String officePinCode=dataSnapshot.child("officePin").getValue(String.class);
                            String experience=dataSnapshot.child("experience").getValue(String.class);
                            String pan=dataSnapshot.child("PAN").getValue(String.class);
                            String salary=dataSnapshot.child("salary").getValue(String.class);
                            if(company!=null){
                                editCompany.setText(company);
                            }
                            if(designation!=null){
                                editDesignation.setText(designation);
                            }
                            if(officeArea!=null){
                                editOfficeArea.setText(officeArea);
                            }
                            if(officeCity!=null){
                                editOfficeCity.setText(officeCity);
                            }
                            if(officePinCode!=null){
                                editOfficePinCode.setText(officePinCode);
                            }
                            if(experience!=null){
                                editExperience.setText(experience);
                            }
                            if(pan!=null){
                                editPanCard.setText(pan);
                            }
                            if(salary!=null){
                                editSalary.setText(salary);
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


    public void SubmitProfessional(View view){
        if(!number.equals("no number")){
            String companyn=editCompany.getText().toString();
            String designationn=editDesignation.getText().toString();
            String officeArean=editOfficeArea.getText().toString();
            String officeCityn=editOfficeCity.getText().toString();
            String officePinn=editOfficePinCode.getText().toString();
            String experiencen=editExperience.getText().toString();
            String pann=editPanCard.getText().toString();
            String salaryp=editSalary.getText().toString();
            if(salaryp!=null && !salaryp.equals("")){
                long salaryn=Long.parseLong(editSalary.getText().toString());
                if(salaryn<=20000) {
                    Toast.makeText(this, "Salary must be greater than 20000", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("salary").setValue(salaryp);
                }
            }
            else if(salaryp!=null){
                if(!isEmpty(editSalary)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("salary").setValue(salaryp);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("salary").setValue(null);
                }

            }
            if(companyn!=null){
                if(!isEmpty(editCompany)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("company").setValue(companyn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("company").setValue(null);
                }

            }
            if(designationn!=null){
                if(!isEmpty(editDesignation)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("designation").setValue(designationn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("designation").setValue(null);
                }

            }
            if(officeArean!=null){
                if(!isEmpty(editOfficeArea)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("officeArea").setValue(officeArean);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("officeArea").setValue(null);
                }

            }
            if(officeCityn!=null){
                if(!isEmpty(editOfficeCity)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("officeCity").setValue(officeCityn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("officeCity").setValue(null);
                }

            }
            if(officePinn!=null){
                if(!isEmpty(editOfficePinCode)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("officePin").setValue(officePinn);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("officePin").setValue(null);
                }

            }
            if(experiencen!=null){
                if(!isEmpty(editExperience)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("experience").setValue(experiencen);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("experience").setValue(null);
                }

            }
            if(pann!=null){
                if(!isEmpty(editPanCard)){
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("PAN").setValue(pann);
                }
                else{
                    mdatabase.child("Entries").child(number).child("ProfessionalProfile").child("PAN").setValue(null);
                }

            }
            Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();
            boolean checkFullFilled=FullFilled();
            if(checkFullFilled){
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkProfessional",true);
                editor.apply();
            }
            else{
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkProfessional",false);
                editor.apply();
            }
            Toast.makeText(this, "Details saved", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private boolean FullFilled() {
        boolean companyc=isEmpty(editCompany);
        boolean designationc=isEmpty(editDesignation);
        boolean officeAreac=isEmpty(editOfficeArea);
        boolean officeCityc=isEmpty(editOfficeCity);
        boolean officePinc=isEmpty(editOfficePinCode);
        boolean experiencec=isEmpty(editExperience);
        boolean panc=isEmpty(editPanCard);
        boolean salaryc=isEmpty(editSalary);
        if(companyc || designationc || officeAreac || officeCityc || officePinc || experiencec || panc || salaryc){
            return false;
        }
        else{
            return true;
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        boolean hey=TextUtils.isEmpty(str);
        if(hey==false){
            if(str.equals("")){
                return true;
            }
        }
        return hey;
    }
}
