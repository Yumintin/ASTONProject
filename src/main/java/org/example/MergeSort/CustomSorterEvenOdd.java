package org.example.MergeSort;

import java.util.Arrays;
import java.util.Comparator;

public class CustomSorterEvenOdd {

    /**
     * Сортирует чётные элементы массива после применения сортировки,
     * игнорируя нечисловые поля.
     *
     * @param array массив объектов для сортировки
     */
    public static <T extends Sortable> void sortEvenNumbersAfterMergeSort(T[] array) {
        if (array == null || array.length == 0) return;

        // Создаем массив для хранения чётных элементов и индексов
        T[] evenElements = Arrays.copyOf(array, array.length);
        int evenCount = 0;

        // Находим чётные элементы по полю, возвращаемому через getSortField()
        for (int i = 0; i < array.length; i++) {
            T element = array[i];
            Number fieldValue = element.getSortField();
            if (fieldValue != null && fieldValue.intValue() % 2 == 0) {
                evenElements[evenCount++] = element;
            }
        }

        // Если чётные элементы существуют, сортируем их
        if (evenCount > 0) {
            Arrays.sort(evenElements, 0, evenCount, Comparator.comparingInt(e -> e.getSortField().intValue()));
        }

        // Вставляем отсортированные чётные элементы обратно в исходный массив
        int evenIndex = 0;
        for (int i = 0; i < array.length; i++) {
            T element = array[i];
            Number fieldValue = element.getSortField();
            if (fieldValue != null && fieldValue.intValue() % 2 == 0) {
                array[i] = evenElements[evenIndex++];
            }
        }
    }
}