package com.midterm.ozel.projectconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

public class CurrencyList extends AppCompatActivity {
    TextView txtList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);
        txtList = (TextView) findViewById(R.id.txtList);
        InputStream inpFile = getResources().openRawResource(R.raw.currencies);
        Scanner scan = new Scanner(inpFile);
        String tmpTxt="";
        while (scan.hasNext()){
            String line = scan.nextLine();
            tmpTxt += line + "\n\n";
        }
        txtList.setText(tmpTxt);
    }

    public void onBack(View view) {
        finish();
    }
}
