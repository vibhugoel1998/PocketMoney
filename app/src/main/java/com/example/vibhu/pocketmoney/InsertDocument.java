package com.example.vibhu.pocketmoney;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class InsertDocument extends AppCompatActivity {
    private StorageReference mStorageRef;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    final static int PICK_PDF_CODE = 2342;
    private static int RESULT_LOAD_IMAGE = 1;
    private DatabaseReference mdatabase;
    int count=0;
    EditText editText;
    String number;
    EditText pass;
    Uri datav;
    ImageView doc1;
    ImageView doc2;
    ImageView doc3;
    ImageView doc4;
    ImageView doc5;
    ImageView doc6;
    String Selecttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_document);
        Intent intent=getIntent();
        Selecttype=intent.getStringExtra("type");
        doc1=findViewById(R.id.doc1);
        doc2=findViewById(R.id.doc2);
        doc3=findViewById(R.id.doc3);
        doc4=findViewById(R.id.doc4);
        doc5=findViewById(R.id.doc5);
        doc6=findViewById(R.id.doc6);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mdatabase= FirebaseDatabase.getInstance().getReference();
        editText=findViewById(R.id.pdfbank);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        number=prefs.getString("number","no number");
        pass=findViewById(R.id.pdfpass);
        boolean checker=prefs.getBoolean("checkDocuments",false);
        if(checker){
            mdatabase.child("Entries").child(number).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String bank=dataSnapshot.child("DocBankType").getValue(String.class);
                    editText.setText(bank);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void SelectAndUpload1(View view){
        count=1;
        selectPDF();
    }
    public void SelectAndUpload2(View view){
        count=2;
        selectPDF();
    }
    public void SelectAndUpload3(View view){
        count=3;
        selectPDF();
    }
    public void SelectAndUpload4(View view){
        count=4;
        selectPDF();
    }
    public void SelectAndUpload5(View view){
        count=5;
        selectPDF();
    }
    public void SelectAndUpload6(View view){
        count=6;
        selectPDF();
    }


    public void selectPDF(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        if(Selecttype.equals("PDF")){
            //creating an intent for file chooser
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
        }
        else{
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                datav=data.getData();
                if(count==1){
                    doc1.setImageResource(R.drawable.checked);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("checkDocuments1",true);
                    editor.apply();
                }
                else if(count==2){
                    doc2.setImageResource(R.drawable.checked);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("checkDocuments2",true);
                    editor.apply();
                }
                else if(count==3){
                    doc3.setImageResource(R.drawable.checked);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("checkDocuments3",true);
                    editor.apply();
                }
                else if(count==4){
                    doc4.setImageResource(R.drawable.checked);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("checkDocuments4",true);
                    editor.apply();
                }
                else if(count==5){
                    doc5.setImageResource(R.drawable.checked);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("checkDocuments5",true);
                    editor.apply();
                }
                else if(count==6){
                    doc6.setImageResource(R.drawable.checked);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("checkDocuments6",true);
                    editor.apply();
                }
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("fileselected",true);
                editor.apply();

            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            datav = Uri.fromFile(new File(picturePath));
            if(count==1){
                doc1.setImageResource(R.drawable.checked);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkDocuments1",true);
                editor.apply();
            }
            else if(count==2){
                doc2.setImageResource(R.drawable.checked);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkDocuments2",true);
                editor.apply();
            }
            else if(count==3){
                doc3.setImageResource(R.drawable.checked);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkDocuments3",true);
                editor.apply();
            }
            else if(count==4){
                doc4.setImageResource(R.drawable.checked);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkDocuments4",true);
                editor.apply();
            }
            else if(count==5){
                doc5.setImageResource(R.drawable.checked);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkDocuments5",true);
                editor.apply();
            }
            else if(count==6){
                doc6.setImageResource(R.drawable.checked);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("checkDocuments6",true);
                editor.apply();
            }
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("fileselected",true);
            editor.apply();
        }
    }

    private void uploadFile(Uri data) {
        StorageReference sRef = mStorageRef.child("Documents/" +number+"/"+ System.currentTimeMillis() + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String downUrl=taskSnapshot.getDownloadUrl().toString();
                        if(count==1){
                            mdatabase.child("Entries").child(number).child("BankStatement").setValue(downUrl);

                        }
                        else if(count==2){
                            mdatabase.child("Entries").child(number).child("PANCard").setValue(downUrl);

                        }
                        else if(count==3){
                            mdatabase.child("Entries").child(number).child("AadharCard").setValue(downUrl);

                        }
                        else if(count==4){
                            mdatabase.child("Entries").child(number).child("AddressProof").setValue(downUrl);

                        }
                        else if(count==5){
                            mdatabase.child("Entries").child(number).child("HighestQual").setValue(downUrl);

                        }
                        else if(count==6){
                            mdatabase.child("Entries").child(number).child("CompanyIDCard").setValue(downUrl);

                        }
                        Toast.makeText(InsertDocument.this, "Document Successfully uploaded!", Toast.LENGTH_SHORT).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });
    }

    public void SubmitDocument(View view){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean helper=prefs.getBoolean("fileselected",false);
        if(helper){
            uploadFile(datav);
            CharSequence str = editText.getText().toString();
            boolean hey= TextUtils.isEmpty(str);
            if(!hey){
                if(!str.equals("")){
                    final boolean check1=prefs.getBoolean("checkDocuments1",false);
                    final boolean check2=prefs.getBoolean("checkDocuments2",false);
                    final boolean check3=prefs.getBoolean("checkDocuments3",false);
                    final boolean check4=prefs.getBoolean("checkDocuments4",false);
                    final boolean check5=prefs.getBoolean("checkDocuments5",false);
                    final boolean check6=prefs.getBoolean("checkDocuments6",false);
                    Log.d("heyVibhu1",check1+"");
                    Log.d("heyVibhu2",check2+"");
                    Log.d("heyVibhu3",check3+"");
                    Log.d("heyVibhu4",check4+"");
                    Log.d("heyVibhu5",check5+"");
                    Log.d("heyVibhu6",check6+"");
                    if(check1 && check2 && check3 && check4 && check5 && check6){
                        CharSequence str2=pass.getText().toString();
                        boolean help=TextUtils.isEmpty(str2);
                        if(!help){
                            if(!str2.equals("")){
                                mdatabase.child("Entries").child(number).child("DocPassword").setValue(pass.getText().toString());
                            }
                        }
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putBoolean("checkDocuments",true);
                        editor.apply();
                        mdatabase.child("Entries").child(number).child("DocBankType").setValue(editText.getText().toString());
                        finish();
                        Toast.makeText(this, "Documents submitted successfully for approval!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(this, "First selected all the Documents!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Please fill in the details to continue!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Please fill in the details to continue!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "First Select a suitable file to continue!", Toast.LENGTH_SHORT).show();
        }

    }

   public void doFinishAuto6(View view){
        finish();
   }


}
