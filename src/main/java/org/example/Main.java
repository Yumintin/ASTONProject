package org.example;

import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;
import org.example.ReadFile.DataLoader;
import org.example.RandomGeneration.RandomDataGenerator;
public class Main {
    public static void main(String[] args) {
        {
            /* КАК ЧИТАТЬ С ФАЙЛА
            Car[] cars = (Car[]) DataLoader.loadFromFile("car"); // Загрузка данных для Car
            for (Car car : cars) {
                System.out.println(car); // Печатаем данные для проверки
            }
            Book[] books = (Book[]) DataLoader.loadFromFile("book"); // Загрузка данных для Book
            for (Book book : books) {
                System.out.println(book); // Печатаем данные для проверки
            }

            RootVegetable[] rootCrops = (RootVegetable[]) DataLoader.loadFromFile("rootvegetable"); // Загрузка данных для RootVegetable
            for (RootVegetable rootCrop : rootCrops) {
                System.out.println(rootCrop); // Печатаем данные для проверки
            }
             */

            /* РАНДОМНОЕ ЗАПОЛНЕНИЕ
            Car[] cars = RandomDataGenerator.generateRandomCars(5);
            for (Car car : cars) {
                System.out.println(car); // Печатаем данные для проверки
            }*/
        }
    }
}
