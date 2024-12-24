package org.example.MergeSort;

import java.util.Arrays;
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

        // Создание контекста сортировки и задания стратегии
        SortContext<MyClass> sortContext = new SortContext<>();
        sortContext.setStrategy(new MergeSort<>());

        // Вывод массива до сортировки
        System.out.println("До сортировки:");
        Arrays.stream(array).forEach(System.out::println);

        // Выполнение сортировки
        sortContext.executeSort(array, comparator);

        // Вывод массива после сортировки
        System.out.println("\nПосле сортировки:");
        Arrays.stream(array).forEach(System.out::println);
    }
}
