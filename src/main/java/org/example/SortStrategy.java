package org.example;

import java.util.List;

interface SortStrategy<T> {
    void sort(T[] array, String fieldName);
}