package com.midterm.ozel.projectconverter;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnitCurrency extends Unit {
    private InputStream inpFile;
    private double[] valueList;

    public UnitCurrency(InputStream inpFile) {
        this.inpFile = inpFile;
        valueList = new double[50];
    }

    @Override

    public List<String> getUnitList() {
        List<String> list = new ArrayList<String>();
        Scanner scan = new Scanner(inpFile);
        int i=0;
        while (scan.hasNext()){
            String line = scan.nextLine();
            String[] pieces = line.split("=");
            list.add(pieces[0]);
            valueList[i] = Double.parseDouble(pieces[1]);
            i++;
        }
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