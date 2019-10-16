package com.midterm.ozel.projectconverter;

import java.util.ArrayList;
import java.util.List;

public class UnitTemperature extends Unit {
    @Override
    public List<String> getUnitList() {
        List<String> list = new ArrayList<String>();
        list.add("Celcius");
        list.add("Fahrenheit");
        list.add("Reaumur");
        list.add("Kelvin");
        return list;
    }
    public double convert(int fromInd, int toInd, double fromValue){
        if (fromInd == toInd)
            return fromValue;
        else {
            double tmpFrom=0, tmpTo=0;
            switch (fromInd){       //Convert from value to Celcius
                case 0:             //Celcius
                    tmpFrom = fromValue;
                    break;
                case 1:             //Fahrenight
                    tmpFrom = (fromValue - 32) * 5 / 9 ;
                    break;
                case 2:             //Reaumur
                    tmpFrom = fromValue * 10 / 8;
                    break;
                case 3:             //Kelvin
                    tmpFrom = (fromValue - 273.15);
                    break;
            }

            switch (toInd){       //Convert Celcius value to requested Unit
                case 0:             //Celcius
                    tmpTo = tmpFrom;
                    break;
                case 1:             //Fahrenight
                    tmpTo = (tmpFrom * 9 / 5) + 32 ;
                    break;
                case 2:             //Reaumur
                    tmpTo = fromValue * 8 / 10;
                    break;
                case 3:             //Kelvin
                    tmpTo = (tmpFrom + 273.15);
                    break;
            }

            return tmpTo;
        }
    }
}