package org.example.MergeSort;

import java.util.Comparator;


/**
 * Интерфейс для стратегии сортировки.
 *
 * @param <T> тип элементов, которые будут сортироваться
 */
public interface SortStrategy<T> {
    /**
     * Сортирует массив объектов типа T с использованием заданного компаратора.
     *
     * @param array      массив объектов для сортировки
     * @param comparator компаратор для сравнения элементов
     */
    void sort(T[] array, Comparator<T> comparator);
}