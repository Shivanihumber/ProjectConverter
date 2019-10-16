package com.midterm.ozel.projectconverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversions {
    private int type;
    private String datetime;
    private double fromValue;
    private double toValue;
    private String fromUnit;
    private String toUnit;

    public Conversions(){
    }

    public Conversions(int type, double fromValue, double toValue, String fromUnit, String toUnit) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        this.datetime = df.format(new Date());
        this.type = type;
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    public int getType() {
        return type;
    }

    public String getDatetime() {
        return datetime;
    }

    public double getFromValue() {
        return fromValue;
    }

    public double getToValue() {
        return toValue;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setFromValue(double fromValue) {
        this.fromValue = fromValue;
    }

    public void setToValue(double toValue) {
        this.toValue = toValue;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public String toString(){
        String tmpTxt="";
        switch (type){
            case 1:
                tmpTxt = "Length: ";
                break;
            case 2:
                tmpTxt = "Weight: ";
                break;
            case 3:
                tmpTxt = "Temperature: ";
                break;
            case 4:
                tmpTxt = "Currency: ";
                break;
        }
        tmpTxt += "\t\t" + datetime + "\t\t" ;
        tmpTxt += fromValue + " " + fromUnit + " = ";
        tmpTxt += toValue + " " + toUnit ;
        return tmpTxt;
    }
}
