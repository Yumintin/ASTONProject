package org.example.IO;
import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;

import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class DataLoader {// Метод для загрузки данных из файла в зависимости от типа объекта
    public static Object[] loadFromFile(String type, int arrayLength) {
        File file = selectFile(); // Вызываем метод для выбора файла
        if (file == null) return new Object[0]; // Если файл не выбран, возвращаем пустой массив

        try {
            // Считываем строки из выбранного файла
            String[] lines = Files.readAllLines(file.toPath()).toArray(new String[0]);

            // Создаем массив конкретного типа
            switch (type.toLowerCase()) {
                case "car":
                    Car[] cars = new Car[arrayLength];
                    for (int i = 0; i < arrayLength; i++) {
                        String[] parts = lines[i].split(",");
                        cars[i] = new Car.Builder()
                                .setPower(Integer.parseInt(parts[0].trim()))
                                .setModel(parts[1].trim())
                                .setYear(Integer.parseInt(parts[2].trim()))
                                .build();
                    }
                    return cars;

                case "book":
                    Book[] books = new Book[arrayLength];
                    for (int i = 0; i < arrayLength; i++) {
                        String[] parts = lines[i].split(",");
                        books[i] = new Book.Builder()
                                .setAuthor(parts[0].trim())
                                .setTitle(parts[1].trim())
                                .setPages(Integer.parseInt(parts[2].trim()))
                                .build();
                    }
                    return books;

                case "rootvegetable":
                    RootVegetable[] rootCrops = new RootVegetable[arrayLength];
                    for (int i = 0; i < arrayLength; i++) {
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
        // Создаем временное окно для выбора файла
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true); // Убедитесь, что окно всегда сверху
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрытие окна
        frame.setVisible(false); // Делаем окно невидимым

        JFileChooser fileChooser = new JFileChooser(); // Создаем объект для выбора файла
        fileChooser.setDialogTitle("Выберите файл для загрузки данных"); // Устанавливаем заголовок диалога

        // Устанавливаем текущую директорию на папку проекта
        File projectDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(projectDirectory); // Устанавливаем директорию

        int result = fileChooser.showOpenDialog(frame); // Показываем диалоговое окно

        frame.dispose(); // Закрываем временное окно после выбора файла

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile(); // Возвращаем выбранный файл
        } else {
            System.out.println("Файл не был выбран."); // Сообщаем, если файл не выбран
            return null; // Возвращаем null
        }
    }
}
