package com.midterm.ozel.projectconverter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Convert extends AppCompatActivity {
    private int unitType;
    Spinner spinnerFrom, spinnerTo;
    EditText fromValue;
    TextView toValue;
    Button btnCurrencyList;
    Unit myUnit;
    int fromUnit = 0, toUnit = 0;
    String fromUnitText, toUnitText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        Intent myIntent = getIntent();
        unitType = myIntent.getIntExtra("UnitType", -1);

        fromValue = (EditText) findViewById(R.id.txtFromValue);
        spinnerFrom = (Spinner) findViewById(R.id.lstFrom);
        spinnerTo = (Spinner) findViewById(R.id.lstTo);
        toValue = (TextView) findViewById(R.id.txtToValue);
        btnCurrencyList = (Button) findViewById(R.id.btnList);
        initializeActivity();
        if (unitType != 4)
            btnCurrencyList.setVisibility(btnCurrencyList.INVISIBLE);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromUnit = position;
                fromUnitText = spinnerFrom.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toUnit = position;
                toUnitText = spinnerTo.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initializeActivity(){
         switch (unitType){
            case 1:
                myUnit = new UnitLength();
                break;
            case 2:
                myUnit = new UnitWeight();
                break;
            case 3:
                myUnit = new UnitTemperature();
                break;
            case 4:
                InputStream inpFile = getResources().openRawResource(R.raw.currencies);
                myUnit = new UnitCurrency(inpFile);
                break;
        }
        List<String> listUnits = myUnit.getUnitList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    public void onConvert(View view) {
        if (fromValue.getText().toString().equals("")){
            fromValue.setError("Please enter a value to be converted");
            fromValue.requestFocus();
        }else
            calculate();
     }

    public void calculate(){
        double tmpFrom = Double.parseDouble(fromValue.getText().toString());
        double toDouble = myUnit.convert(fromUnit,toUnit,tmpFrom);
        toDouble = (double) Math.round(toDouble * 100) / 100;
        toValue.setText(String.valueOf(toDouble));

        //Add a record to converthistory
        DatabaseOpenHelper dbHandler = new DatabaseOpenHelper(this,null,null,2);
        Conversions conv = new Conversions(unitType,tmpFrom,toDouble,fromUnitText,toUnitText);
        dbHandler.addRecord(conv);
    }
    public void onList(View view) {
        Intent myIntent = new Intent(this, CurrencyList.class);
        startActivity(myIntent);
    }
    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fromUnit",fromUnit);
        outState.putInt("toUnit",toUnit);
        outState.putString("fromValue",fromValue.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fromUnit = savedInstanceState.getInt("fromUnit");
        spinnerFrom.setSelection(fromUnit);
        toUnit = savedInstanceState.getInt("toUnit");
        spinnerTo.setSelection(toUnit);
        fromValue.setText(savedInstanceState.getString("fromValue"));
        if (!fromValue.getText().toString().equals(""))
            calculate();
    }
}
