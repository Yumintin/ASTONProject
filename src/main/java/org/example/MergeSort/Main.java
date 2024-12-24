package org.example.MergeSort;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        MyClass[] array = {
                new MyClass("Alice", "Smith", 30),
                new MyClass("Bob", "Brown", 25),
                new MyClass("Charlie", "Davis", 25),
                new MyClass("Alice", "Jones", 22)
        };
        // Это наверне как раз и надо в сам класс добавить
        Comparator<MyClass> comparator = Comparator
                .comparing(MyClass::getFirstName)
                .thenComparing(MyClass::getLastName)
                .thenComparingInt(MyClass::getAge);

        // Сортировка с помощью MergeSort
        MergeSort<MyClass> mergeSort = new MergeSort<>();
        mergeSort.sort(array, comparator);

        // Вывод результата
        System.out.println("Sorted by id, then name:");
        for (MyClass myClass : array) {
            System.out.println(myClass);
    }
}}
