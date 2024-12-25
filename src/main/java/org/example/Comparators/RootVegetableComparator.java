package org.example.Comparators;

import org.example.CustomClasses.RootVegetable;

import java.util.Comparator;

public class RootVegetableComparator implements Comparator<RootVegetable> {
    @Override
    public int compare(RootVegetable r1, RootVegetable r2) {
        int typeComparison = r1.getType().compareTo(r2.getType());
        if (typeComparison != 0) return typeComparison;

        int weightComparison = Double.compare(r1.getWeight(), r2.getWeight());
        if (weightComparison != 0) return weightComparison;

        return r1.getColor().compareTo(r2.getColor());
    }
}