package org.example;

import java.lang.reflect.Field;
import java.util.List;

// Универсальная стратегия сортировки
class MergeSort<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, String fieldName) {
        if (list == null || list.isEmpty()) {
            return; // Если список null или пуст, сортировать нечего
        }

        try {
            // Получаем класс первого элемента списка
            Class<?> clazz = list.getFirst().getClass();

            // Находим нужное поле и делаем его доступным
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            // Сортируем список
            list.sort((o1, o2) -> {
                try {
                    // Извлекаем значения полей
                    Object value1 = field.get(o1);
                    Object value2 = field.get(o2);

                    // Сравниваем значения, если они реализуют Comparable
                    if (value1 instanceof Comparable && value2 instanceof Comparable) {
                        return ((Comparable<Object>) value1).compareTo(value2);
                    } else {
                        throw new IllegalArgumentException(
                                "Field values are not comparable: " + value1 + ", " + value2
                        );
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error accessing field : " + fieldName, e);
                }
            });
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Field not found: " + fieldName, e);
        }
    }
}