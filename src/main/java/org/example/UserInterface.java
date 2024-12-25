package org.example;
import java.util.Scanner;

public class UserInterface {
	private final Scanner scanner;
	public UserInterface() {
		this.scanner = new Scanner(System.in);
	}

	public void chooseClass() {
		System.out.println("\nВыберите класс: ");
		System.out.println("1. Автомобиль");
		System.out.println("2. Книга");
		System.out.println("3. Корнеплод");
	}

	public void chooseMethod() {
		System.out.println("\nВыберите метод заполнения:");
		System.out.println("1. Ручной");
		System.out.println("2. Рандомный");
		System.out.println("3. Из файла");
	}

	public void chooseOperation() {
		System.out.println("\nВыберите операцию:");
		System.out.println("1. Сортировка");
		System.out.println("2. Поиск");
		System.out.println("3. Вывод в файл");
		System.out.println("0. Вернуться к выбору класса");
	}

	public String classSelected(int operation) {
		switch (operation) {
			case 1:
				return "Car";
			case 2:
				return "Book";
			case 3:
				return "RootVegetable";
			default:
				return "";
		}
	}

	public Scanner getScanner() {
		return scanner;
	}

	public String getInput() {
		return scanner.nextLine().trim();
	}

	public String line(String message) {
		System.out.println(message);
		return scanner.nextLine().trim();
	}
}
