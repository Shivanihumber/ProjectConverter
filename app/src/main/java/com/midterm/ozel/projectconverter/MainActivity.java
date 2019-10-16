package com.midterm.ozel.projectconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    CheckBox agree;
    int convUnit = 0, selectedID;
    SharedPreferences myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myref = getPreferences(MODE_PRIVATE);
        rg = (RadioGroup) findViewById(R.id.radGroup);
        agree = (CheckBox) findViewById(R.id.chkAgree);
        agree.setChecked(myref.getBoolean("AGREE",false));

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            selectedID = checkedId;
                Log.d("FERFER","selection changed" + selectedID);
            switch (checkedId){
                case R.id.radLength:
                    convUnit = 1;
                    break;
                case R.id.radWeight:
                    convUnit = 2;
                    break;
                case R.id.radTemp:
                    convUnit = 3;
                    break;
                case R.id.radCurrency:
                    convUnit = 4;
                    break;
            }
                Log.d("FERFER","selection changed conv unit" + convUnit);
            setImage();
            }
        });

        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = myref.edit();
                editor.putBoolean("AGREE",agree.isChecked());
                editor.commit();
            }
        });
    }

    public void clickBtn(View view) {
        if (!agree.isChecked()){
            agree.setError("");
            agree.requestFocus();
            Toast toast = Toast.makeText(this,"You must accept terms and conditions !",Toast.LENGTH_SHORT);
            toast.show();
        }else if (convUnit == 0){
            Toast toast = Toast.makeText(this,"Please select type of conversion",Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Intent myIntent = new Intent(this, Convert.class);
            myIntent.putExtra("UnitType",convUnit);
            startActivity(myIntent);
        }
    }

    public void setImage(){
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        Log.d("FERFER","set image 1");
        switch (selectedID){
            case R.id.radLength:
                iv.setImageResource(R.drawable.onelength);
                break;
            case R.id.radWeight:
                iv.setImageResource(R.drawable.oneweight);
                break;
            case R.id.radTemp:
                iv.setImageResource(R.drawable.onethermo);
                break;
            case R.id.radCurrency:
                iv.setImageResource(R.drawable.onecurrency);
                break;
        }
        Log.d("FERFER","set image 2");
    }
    public void showInfo(View view) {
        Intent myIntent = new Intent(this, Info.class);
        startActivity(myIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("agree",agree.isChecked());
        outState.putInt("convUnit",convUnit);
        outState.putInt("selectedID",selectedID);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        agree.setChecked(savedInstanceState.getBoolean("agree"));
        convUnit = savedInstanceState.getInt("convUnit");
        selectedID = savedInstanceState.getInt("selectedID");
        if (selectedID != 0)
            rg.check(selectedID);
        setImage();
    }

    public void onHistory(View view) {
        Intent myIntent = new Intent(this, History.class);
        startActivity(myIntent);
    }
}
