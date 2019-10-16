package com.midterm.ozel.projectconverter;

import java.util.ArrayList;
import java.util.List;

public class UnitWeight extends Unit {
    private double[] valueList ;
    @Override
    public List<String> getUnitList() {
        List<String> list = new ArrayList<String>();
        valueList = new double[20];
        list.add("milligram");  valueList[0] = 0.00001;
        list.add("gram");       valueList[1] = 0.001;
        list.add("kilogram");   valueList[2] = 1.0;
        list.add("ton");        valueList[3] = 1000.0;
        list.add("ounce");      valueList[4] = 0.028349523125;
        list.add("pound");      valueList[5] = 0.45359237;
        return list;
    }

    public double convert(int fromInd, int toInd, double fromValue){
        if (fromInd == toInd)
            return fromValue;
        else {
            double tmpDouble = fromValue * valueList[fromInd] / valueList[toInd];
            return tmpDouble;
        }
    }
}
