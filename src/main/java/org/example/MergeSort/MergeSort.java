package org.example.MergeSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация стратегии сортировки с использованием алгоритма сортировки слиянием.
 *
 * @param <T> тип элементов, которые будут сортироваться
 */
public class MergeSort<T> implements SortStrategy<T> {
    /**
     * Сортирует массив объектов типа T с использованием алгоритма сортировки слиянием.
     *
     * @param array      массив объектов для сортировки
     * @param comparator компаратор для сравнения элементов
     */
    @Override
    public void sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) return; // Базовый случай: массив из 1 или 0 элементов уже отсортирован
        mergeSort(array, 0, array.length - 1, comparator);
    }

    /**
     * Рекурсивная функция сортировки массива методом слияния.
     *
     * @param array      массив для сортировки
     * @param left       индекс левой границы
     * @param right      индекс правой границы
     * @param comparator компаратор для сравнения элементов
     */
    private void mergeSort(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2; // Находим середину
            mergeSort(array, left, mid, comparator); // Сортируем левую часть
            mergeSort(array, mid + 1, right, comparator); // Сортируем правую часть
            merge(array, left, mid, right, comparator); // Объединяем отсортированные части
        }
    }

    /**
     * Объединяет два отсортированных подмассива в один.
     *
     * @param array      массив, содержащий два отсортированных подмассива
     * @param left       индекс начала первого подмассива
     * @param mid        индекс конца первого подмассива
     * @param right      индекс конца второго подмассива
     * @param comparator компаратор для сравнения элементов
     */
    private void merge(T[] array, int left, int mid, int right, Comparator<T> comparator) {
        int n1 = mid - left + 1; // Размер первого подмассива
        int n2 = right - mid; // Размер второго подмассива

        // Создаем временные массивы для левой и правой частей
        T[] leftArray = Arrays.copyOfRange(array, left, mid + 1);
        T[] rightArray = Arrays.copyOfRange(array, mid + 1, right + 1);

        // Индексы для прохода по временным массивам
        int i = 0, j = 0, k = left;

        // Слияние массивов на основе сравнения элементов
        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k++] = leftArray[i++]; // Добавляем элемент из левого подмассива
            } else {
                array[k++] = rightArray[j++]; // Добавляем элемент из правого подмассива
            }
        }

        // Копируем оставшиеся элементы из левого подмассива
        while (i < n1) {
            array[k++] = leftArray[i++];
        }

        // Копируем оставшиеся элементы из правого подмассива
        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }
}