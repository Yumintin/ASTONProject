package org.example.BinarySearch;

import java.util.Comparator;

public class BinarySearch {

    //метод бинарного поиска
    public static <T> int binarySearch(T[] array, T searchItemKey, Comparator<? super T> comparator) {

        //если нет объекта поиска
        if (searchItemKey == null)
            return -1;

        int left = 0; //Начало массива
        int right = array.length - 1; //Конец массива

        while (left <= right) {
            int mid = left + (right - left) / 2; //Находим середину массива

            //сравнение элемента в середине с искомым элементом
            int comparisonResult = comparator.compare(array[mid], searchItemKey);

            if (comparisonResult == 0) {
                return mid; //Элемент найден
            } else if (comparisonResult < 0) {
                left = mid + 1; //Ищем в правой половине
            } else {
                right = mid - 1; //Ищем в левой половине
            }
        }

        return -1; //Элемент не найден
    }

}
