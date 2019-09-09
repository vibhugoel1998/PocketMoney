package com.example.vibhu.pocketmoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseDocumentMethod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_document_method);
    }

    public void doBack(View view){
        finish();
    }

    public void OpenWithPDF(View view){
        Intent intent=new Intent(ChooseDocumentMethod.this,InsertDocument.class);
        intent.putExtra("type","PDF");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
        finish();
    }

    public void OpenWithPhotos(View view){
        Intent intent=new Intent(ChooseDocumentMethod.this,InsertDocument.class);
        intent.putExtra("type","Photos");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
        finish();
    }
}
