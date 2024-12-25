package org.example.Comparators;

import org.example.CustomClasses.Car;

import java.util.Comparator;

public class CarComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        int yearComparison = Integer.compare(c1.getYear(), c2.getYear());
        if (yearComparison != 0) return yearComparison;

        int modelComparison = c1.getModel().compareTo(c2.getModel());
        if (modelComparison != 0) return modelComparison;

        return Integer.compare(c1.getPower(), c2.getPower());
    }
}