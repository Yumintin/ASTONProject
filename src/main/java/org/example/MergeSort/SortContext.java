package org.example.MergeSort;

// Паттерн стратегия
import java.util.Comparator;

public class SortContext<T> {
    private SortStrategy<T> strategy;

    /**
     * Устанавливает стратегию сортировки.
     *
     * @param strategy объект, реализующий интерфейс SortStrategy
     */
    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    /**
     * Выполняет сортировку массива с использованием текущей стратегии и компаратора.
     *
     * @param array      массив объектов для сортировки
     * @param comparator компаратор для сравнения элементов
     */
    public void executeSort(T[] array, Comparator<T> comparator) {
        if (strategy == null) {
            throw new IllegalStateException("SortStrategy is not set");
        }
        strategy.sort(array, comparator);
    }
}