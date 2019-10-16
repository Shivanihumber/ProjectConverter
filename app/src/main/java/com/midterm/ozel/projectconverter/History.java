package com.midterm.ozel.projectconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class History extends AppCompatActivity {
    TextView txtHist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        txtHist = (TextView) findViewById(R.id.txtHistory);
        fillTable();
    }
    private void fillTable(){
        DatabaseOpenHelper dbHandler = new DatabaseOpenHelper(this,null,null,2);
        List<Conversions> tmpList = dbHandler.listActivities();
        String tmpTxt="";
        for(Conversions i : tmpList)
            tmpTxt += i.toString() + "\n";
        txtHist.setText(tmpTxt);
    }

    public void onBack(View view) {
        finish();
    }
}
