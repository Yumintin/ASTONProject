package org.example.UI;

import java.util.Scanner;

public class UserInterface {
	private final Scanner scanner;
	public UserInterface() {
		this.scanner = new Scanner(System.in);
	}

	// Вывод в консоль меню выбора класса
	public void chooseClass() {
		System.out.println("\nВыберите класс: ");
		System.out.println("1. Автомобиль");
		System.out.println("2. Книга");
		System.out.println("3. Корнеплод");
	}

	// Вывод в консоль меню метода заполнения
	public void chooseMethod() {
		System.out.println("\nВыберите метод заполнения:");
		System.out.println("1. Ручной");
		System.out.println("2. Рандомный");
		System.out.println("3. Из файла");
		System.out.println("0. Возврат в прошлое меню");
	}

	// Вывод в консоль выбора операций с полученными данными
	public void chooseOperation() {
		System.out.println("\nВыберите операцию:");
		System.out.println("1. Поиск");
		System.out.println("2. Четная сортировка");
		System.out.println("0. Возврат в прошлое меню");
	}

	// Сопоставение ввода с соответствующими именами классов.
	public String classSelected(int operation) {
		switch (operation) {
			case 1:
				return "Car";
			case 2:
				return "Book";
			case 3:
				return "RootVegetable";
			default:
				return "";  // Ничего не возвращать в случае выбора вне возможного допустимого значения
		}
	}

	/*
		Получаем то, что ввел пользователь без пробелов
		для корректного заполнения данных, например
		в тот-же массив при ручном вводе, а также
		убирает "0" в начале строки, для корректного
		ввода int значений
	*/
	public String getInput() {
		String input = scanner.nextLine().trim();
		if (input.startsWith("0")) {
			input = input.substring(1);
		}
		return input;
	}

	/*
		Аналогичная функция, только с выводом
		сообщения в консоль
	 */
	public String line(String message) {
		System.out.println(message);
		return scanner.nextLine().trim();
	}

	public void close() {
		if (scanner != null) {
			scanner.close ();
		}
	}
}
