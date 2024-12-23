package org.example;

import java.util.List;

interface SortStrategy<T> {
    void sort(List<T> list, String fieldName);
}