package org.example;

import org.example.IO.DataWriter;
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
		DataWriter.write("", "true");
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

				// Если пусто, то повторно выводим меню
				if (selectedClass.isEmpty()) {
					continue;
				}
				System.out.println("Был выбран класс: " + selectedClass +
									"\n=========================");
				// Инициализируем переменную для длины массива
				int desiredLength = select.getArrayLength();

				// Вызов функции с выбором метода заполнения данных
				boolean method=true;
				while(method) {
					Object[] array = select.methodSelected(selectedClass, inputHandler, desiredLength);
					if (array != null) {
						select.arrayOperations(ui, inputHandler, select, array, selectedClass);
					} else {
						method = false;
					}
				}
			} catch(NumberFormatException e) {
				System.out.println("Неверный ввод. Введите число.");
			}
		} while (!input.equalsIgnoreCase("end"));
		System.out.println("Выход...");
		ui.close();
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