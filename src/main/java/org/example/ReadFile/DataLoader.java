package org.example.ReadFile;
import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;

import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class DataLoader {// Метод для загрузки данных из файла в зависимости от типа объекта
    public static Object[] loadFromFile(String type) {
        File file = selectFile(); // Вызываем метод для выбора файла
        if (file == null) return new Object[0]; // Если файл не выбран, возвращаем пустой массив

        try {
            // Считываем строки из выбранного файла
            String[] lines = Files.readAllLines(file.toPath()).toArray(new String[0]);

            // Создаем массив конкретного типа
            switch (type.toLowerCase()) {
                case "car":
                    Car[] cars = new Car[lines.length];
                    for (int i = 0; i < lines.length; i++) {
                        String[] parts = lines[i].split(",");
                        cars[i] = new Car.Builder()
                                .setPower(Integer.parseInt(parts[0].trim()))
                                .setModel(parts[1].trim())
                                .setYear(Integer.parseInt(parts[2].trim()))
                                .build();
                    }
                    return cars;

                case "book":
                    Book[] books = new Book[lines.length];
                    for (int i = 0; i < lines.length; i++) {
                        String[] parts = lines[i].split(",");
                        books[i] = new Book.Builder()
                                .setAuthor(parts[0].trim())
                                .setTitle(parts[1].trim())
                                .setPages(Integer.parseInt(parts[2].trim()))
                                .build();
                    }
                    return books;

                case "rootvegetable":
                    RootVegetable[] rootCrops = new RootVegetable[lines.length];
                    for (int i = 0; i < lines.length; i++) {
                        String[] parts = lines[i].split(",");
                        rootCrops[i] = new RootVegetable.Builder()
                                .setType(parts[0].trim())
                                .setWeight(Double.parseDouble(parts[1].trim()))
                                .setColor(parts[2].trim())
                                .build();
                    }
                    return rootCrops;

                default:
                    throw new IllegalArgumentException("Неизвестный тип: " + type);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка обработки данных в файле: " + e.getMessage());
        }

        return new Object[0];
    }

    // Метод для выбора файла через графический интерфейс
    private static File selectFile() {
        JFileChooser fileChooser = new JFileChooser(); // Создаем объект для выбора файла
        fileChooser.setDialogTitle("Выберите файл для загрузки данных"); // Устанавливаем заголовок диалога
        int result = fileChooser.showOpenDialog(null); // Показываем диалоговое окно

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile(); // Возвращаем выбранный файл
        } else {
            System.out.println("Файл не был выбран."); // Сообщаем, если файл не выбран
            return null; // Возвращаем null
        }
    }
}
