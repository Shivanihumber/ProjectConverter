package com.midterm.ozel.projectconverter;

import java.util.List;

public abstract class Unit {
    public Unit() {
    }
    public abstract List<String> getUnitList();
    public abstract double convert(int fromInd, int toInd, double fromValue);
}
