package org.example;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

import static org.example.files.XlsFileToClassObject.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в программу Aston");
        //System.out.println("Для выхода из программы введите end");

        System.out.println("Выберите класс: ");
        System.out.println("1. Автомобиль");
        System.out.println("2. Книга");
        System.out.println("3. Корнеплод");

        String input = scanner.nextLine();

        int operation = Integer.parseInt(input);
        String selectedClass = classSelected(operation);
        System.out.println("Вы выбрали класс " + selectedClass);

        System.out.println(" =========================");
        System.out.println("Выберите метод заполнения:");
        System.out.println("1. Ручной");
        System.out.println("2. Рандомный");
        System.out.println("3. Из файла");

        input = scanner.nextLine();
        int method = Integer.parseInt(input);

        String methodSelected = methodSelected(method, selectedClass);
        //System.out.println("Вы выбрали метод " + methodSelected);

    }

    private static String classSelected(int operation) {
        String selectedClass = "";
        switch (operation) {
            case 1: // автомобиль
                selectedClass = "Car";
                break;
            case 2: // Книга
                selectedClass = "Book";
                break;
            case 3: // корнеплод
                selectedClass = "RootVegetable";
                break;
            default:
                System.out.println("Такой операции нет");
        }

        return selectedClass;
    }

    private static String methodSelected(int operation, String classSelected) throws IOException {
        String selectedMethod = "";
        switch (operation) {
            case 1: // ручной
                selectedMethod = "input";
                break;
            case 2: // рандомный
                selectedMethod = "random";
                break;
            case 3: // из файла
                selectedMethod = "fromFile";
                System.out.println("введите путь к файлу");
                Scanner scanner = new Scanner(System.in);
                // TODO: сделать через Проводник выбор документа
                String FILELOCATION = scanner.nextLine();;

                if (classSelected.equals("Car")) {
                    List<Car> cars  = excelDataToListOfObjets_Car(FILELOCATION);
                    System.out.println(cars);
                }
                else if (classSelected.equals("Book")) {
                    List<Book> books = excelDataToListOfObjets_Book(FILELOCATION);
                    System.out.println(books);
                }
                else if (classSelected.equals("RootVegetable")) {
                    List<RootVegetable> rootVegetables = excelDataToListOfObjets_RootVegitables(FILELOCATION);
                    System.out.println(rootVegetables);
                }

                break;
            default:
                System.out.println("Такой операции нет");
        }

        return selectedMethod;
    }


}