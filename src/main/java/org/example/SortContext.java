package org.example;

import java.util.List;
// Паттерн стратегия
class SortContext<T> {
    private SortStrategy<T> strategy;

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void executeSort(List<T> list, String fieldName) {
        if (strategy == null) {
            throw new IllegalStateException("SortStrategy is not set ");
        }
        strategy.sort(list, fieldName);
    }
}