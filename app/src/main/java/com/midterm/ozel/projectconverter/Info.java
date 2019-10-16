package com.midterm.ozel.projectconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }
    public void onBack(View view) {
        finish();
    }
}
