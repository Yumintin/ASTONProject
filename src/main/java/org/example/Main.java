package org.example;
import org.example.CustomClasses.Car;
import org.example.UI.InputHandler;
import org.example.UI.SelectedHandler;
import org.example.UI.UserInterface;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public void initialize() throws IOException {
		System.out.println("Инициализация интерфейса программы...");
		UserInterface ui = new UserInterface();
		InputHandler inputHandler = new InputHandler(ui);
		SelectedHandler select = new SelectedHandler(ui);
		String input;

		System.out.println("\nДобро пожаловать в программу \"Aston\"");
		System.out.println("Для выхода из программы введите end");
		do {
			// Вызов сообщений в консоль для выбора класса
			ui.chooseClass();
			// Обрабатываем пользовательский ввод
			input = ui.getInput();

			// Если введен "end" - выходим из программы
			if (input.equalsIgnoreCase("end")) {
				break;
			}
			try {
				// Обрабатываем ввод и определяем какой класс был выбран
				int operation = Integer.parseInt(input);
				String selectedClass = ui.classSelected(operation);

				// Если пусто, то повтрно выводим меню
				if (selectedClass.isEmpty()) {
					continue;
				}
				System.out.println("Был выбран класс: " + selectedClass +
									"\n=========================");
				Scanner scanner = new Scanner(System.in);
				int desiredLength = -1; // Инициализируем переменную для длины массива
				// Цикл для запроса корректного ввода
				while (desiredLength < 0) {
					System.out.print("Введите размер массива (должен быть неотрицательным): ");
					if (scanner.hasNextInt()) {
						desiredLength = scanner.nextInt();
						if (desiredLength < 0) {
							System.out.println("Размер массива не может быть отрицательным. Попробуйте снова.");
						}
					} else {
						System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
						scanner.next(); // Очищаем некорректный ввод
					}
				}
				// Вызов функции с выбором метода заполнения данных
				List<?> filledClass=select.methodSelected (selectedClass, inputHandler, desiredLength);
				if(filledClass!=null){
					for (Object item : filledClass) {
						System.out.println(item);
					}
				}
				if (filledClass != null && !filledClass.isEmpty()) {            // Условие, что список не пуст
					Object[] array = filledClass.toArray(new Object[0]);    // Преобразовываем список в массив для сортировки
					boolean exit = false;   // Булевое значение для работы с выходом из цикла
					while (!exit) {
						// Вывод меню для выбора того, что нужно делать
						inputHandler.sorting(array, selectedClass);//сортируем/// ////////////////////////////СЮДА ВЫВОД В ФАЙЛ

						ui.chooseOperation();



						String choose = ui.line("");
						switch (choose) {
							case "1":   // Поиск
								break;
							case "0":   // Выход в предыдущее меню, если введен "0"
								exit = true;
								break;
							default:    // Повторный вызов при невалидном вводе
								return;
						}
					}
				}else {
					System.out.println("Список пуст");
				}



			} catch (NumberFormatException e) {
				System.out.println("Неверный ввод. Введите число.");
			}
		} while (!input.equalsIgnoreCase("end"));
		System.out.println("Выход...");
	}

	public static void main(String[] args) {
		try {
			Main app = new Main();
			app.initialize();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}