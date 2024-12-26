package org.example.MergeSort;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Пример данных для сортировки
        MyClass1[] array = {
                new MyClass1("Bob", 5.3, 31),   // Нечётный возраст
                new MyClass1("Alice", 7.2, 40), // Чётный возраст
                new MyClass1("Eve", 4.4, 29),   // Нечётный возраст
                new MyClass1("Charlie", 2.1, 22), // Чётный возраст
                new MyClass1("David", 3.6, 20), // Чётный возраст
                new MyClass1("Frank", 1.1, 33)  // Нечётный возраст
        };

        // Компаратор для сортировки MergeSort
        Comparator<MyClass1> comparator = Comparator
                .comparing(MyClass1::getName)
                .thenComparingDouble(MyClass1::getValue)
                .thenComparingInt(MyClass1::getAge);

        // Создание контекста сортировки и задания стратегии
        SortContext<MyClass1> sortContext = new SortContext<>();
        sortContext.setStrategy(new MergeSort<>());

        // Вывод массива до сортировки
        System.out.println("До сортировки:");
        Arrays.stream(array).forEach(System.out::println);

        // Выполнение сортировки (MergeSort)
        sortContext.executeSort(array, comparator);

        // Вывод массива после сортировки
        System.out.println("\nПосле сортировки mergeSort:");
        Arrays.stream(array).forEach(System.out::println);

        // Сортировка чётных чисел после сортировки
        CustomSorterEvenOdd.sortEvenNumbersAfterMergeSort(array);

        System.out.println("\nПосле сортировки чётных чисел:");
        Arrays.stream(array).forEach(System.out::println);
    }
}
