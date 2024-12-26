package org.example;
import org.example.UI.InputHandler;
import org.example.UI.SelectedHandler;
import org.example.UI.UserInterface;

import java.io.IOException;

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

				// Вызов функции с выбором метода заполнения данных
				select.methodSelected (selectedClass, inputHandler);
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