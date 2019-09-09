package com.example.vibhu.pocketmoney;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class PermissionsActivity extends AppCompatActivity {
    CheckBox checkBox;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        checkBox=findViewById(R.id.permissioncheck);
    }

    public void OnDisagree(View view){
        finish();
    }

    public void OnAgree(View view){
        if(checkBox.isChecked()){
            isStoragePermissionGranted(1);

        }else{
            Toast.makeText(this, "Please click checkbox then press i agree!", Toast.LENGTH_SHORT).show();
        }
    }

    public  void isStoragePermissionGranted(int a) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.v("permiV", "Permission is granted");
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("PermissionGranted",false);
                    editor.apply();
                    Log.v("permiV", "Permission is revoked");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_CONTACTS}, 1);
                }
            } else { //permission is automatically granted on sdk<23 upon installation
                Log.v("permiV", "Permission is granted");
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[0] == PackageManager.PERMISSION_GRANTED){
      //      Log.v("permiV","Permission: "+permissions[0]+ "was "+grantResults[0]);
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putBoolean("PermissionGranted",true);
            editor.apply();
            Intent intent=new Intent(this,InfoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            finish();
            }
            else{
            Toast.makeText(this, "First provide all permissions to go forward!", Toast.LENGTH_SHORT).show();
        }
    }
}
