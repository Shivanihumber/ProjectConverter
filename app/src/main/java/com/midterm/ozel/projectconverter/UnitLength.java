package com.midterm.ozel.projectconverter;

import java.util.ArrayList;
import java.util.List;

public class UnitLength extends Unit {
    private double[] valueList ;
    @Override
    public List<String> getUnitList() {
        List<String> list = new ArrayList<String>();
        valueList = new double[20];
        list.add("nanometer");  valueList[0] = Math.pow(10.0,-9.0);
        list.add("millimeter"); valueList[1] = 0.001;
        list.add("centimeter"); valueList[2] = 0.01;
        list.add("decimeter");  valueList[3] = 0.1;
        list.add("meter");      valueList[4] = 1.0;
        list.add("dekameter");  valueList[5] = 10.0;
        list.add("hectometer"); valueList[6] = 100.0;
        list.add("kilometer");  valueList[7] = 1000.0;
        list.add("megameter");  valueList[8] = 10000.0;
        list.add("gigameter");  valueList[9] = 100000.0;
        list.add("mile");       valueList[10] = 1609.34;
        list.add("yard");       valueList[11] = 0.9144;
        list.add("foot");       valueList[12] = 0.3048;
        list.add("inch");       valueList[13] = 0.0254;
        list.add("light year"); valueList[14] = 9.4605284 * Math.pow(10.0,15.0);
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
