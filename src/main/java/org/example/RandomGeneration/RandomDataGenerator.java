package org.example.RandomGeneration;
import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;

import java.util.Random;

public class RandomDataGenerator {
private static final String[] CAR_MODELS = {"Sedan", "Hatchback", "SUV", "Coupe", "Convertible", "Wagon", "Pickup", "Compact", "Roadster", "Minivan"};
    private static final String[] BOOK_AUTHORS = {"Author A", "Author B", "Author C", "Author D", "Author E", "Author F", "Author G", "Author H", "Author I", "Author J"};
    private static final String[] BOOK_TITLES = {"Title A", "Title B", "Title C", "Title D", "Title E", "Title F", "Title G", "Title H", "Title I", "Title J"};
    private static final String[] ROOTVEGETABLE_TYPES = {"Carrot", "Potato", "Beet", "Turnip", "Radish", "Parsnip", "Sweet Potato", "Yam", "Ginger", "Onion"};
    private static final String[] ROOTVEGETABLE_COLORS = {"Orange", "Brown", "Red", "White", "Pink", "Cream", "Yellow", "Purple"};

    private static final Random RANDOM = new Random();

    // Генерация случайного массива объектов Car
    public static Car[] generateRandomCars(int count) {
        Car[] cars = new Car[count];
        for (int i = 0; i < count; i++) {
            cars[i] = new Car.Builder()
                    .setPower(RANDOM.nextInt(101) + 100) // Случайная мощность от 100 до 200
                    .setModel(CAR_MODELS[RANDOM.nextInt(CAR_MODELS.length)])
                    .setYear(2015 + RANDOM.nextInt(11)) // Случайный год от 2015 до 2025
                    .build();
        }
        return cars;
    }

    // Генерация случайного массива объектов Book
    public static Book[] generateRandomBooks(int count) {
        Book[] books = new Book[count];
        for (int i = 0; i < count; i++) {
            books[i] = new Book.Builder()
                    .setAuthor(BOOK_AUTHORS[RANDOM.nextInt(BOOK_AUTHORS.length)])
                    .setTitle(BOOK_TITLES[RANDOM.nextInt(BOOK_TITLES.length)])
                    .setPages(RANDOM.nextInt(401) + 100) // Случайное количество страниц от 100 до 500
                    .build();
        }
        return books;
    }

    // Генерация случайного массива объектов RootCrop
    public static RootVegetable[] generateRandomRootVegetable(int count) {
        RootVegetable[] rootCrops = new RootVegetable[count];
        for (int i = 0; i < count; i++) {
            rootCrops[i] = new RootVegetable.Builder()
                    .setType(ROOTVEGETABLE_TYPES[RANDOM.nextInt(ROOTVEGETABLE_TYPES.length)])
                    .setWeight(Math.round((RANDOM.nextDouble() * 2 + 0.5) * 10.0) / 10.0) // Вес от 0.5 до 2.5 с округлением до 0.1
                    .setColor(ROOTVEGETABLE_COLORS[RANDOM.nextInt(ROOTVEGETABLE_COLORS.length)])
                    .build();
        }
        return rootCrops;
    }
}
