package org.example;
import org.example.BinarySearch.BinarySearch;
import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;
import org.example.MergeSort.CustomSorterEvenOdd;
import org.example.IO.DataWriter;
import org.example.UI.InputHandler;
import org.example.UI.SelectedHandler;
import org.example.UI.UserInterface;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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

					Object[] array = select.methodSelected(selectedClass, inputHandler, desiredLength);
					System.out.println("до сортировки");
					for (Object item : array) {
						System.out.println(item);
					}
					inputHandler.sorting(array, selectedClass);//сортируем

					DataWriter.write(InputHandler.getDataAsString(array), "");////////////////////////////////////////СДЕЛАЛ МЕТОД СТАТИК ВДРУГ ЧТО_ТО СЛОМАЛ ПРОВЕРЬ
					boolean exit = false;   // Булевое значение для работы с выходом из цикла
					while (!exit) {
						// Вывод меню для выбора того, что нужно делать
						ui.chooseOperation();
						String choose = ui.line("");
						switch (choose) {
							case "1": {
								Comparator<Object> handlerComparator = (Comparator<Object>) inputHandler.getComparator(selectedClass);
								Object key = inputHandler.classExReturner(selectedClass);
								System.out.println("Индекс найденного элемента: " + BinarySearch.binarySearch(array, key, handlerComparator)); //
								break;
							}  // Поиск
							case "2": { //
								CustomSorterEvenOdd.sortEvenNumbersAfterMergeSort(reWriter(array, selectedClass));
								System.out.println("Отсортирован!");
								for (Object item : array) {
									System.out.println(item);
								}
								break;
							}
							case "0": {   // Выход в предыдущее меню, если введен "0"
								exit = true;
							}

						}
					}
			} catch (NumberFormatException e) {
				System.out.println("Неверный ввод. Введите число.");
			}
		} while (!input.equalsIgnoreCase("end"));
		System.out.println("Выход...");
		ui.close();
	}
	
private <T> T[] reWriter(Object[] array,String selectedClass)
{
	if(selectedClass=="Car")
		return (T[]) Arrays.copyOf(array,array.length,Car[].class);
	else if (selectedClass=="Book") {
		return (T[]) Arrays.copyOf(array,array.length, Book[].class);
	} else if (selectedClass=="RootVegetable") {
		return (T[]) Arrays.copyOf(array,array.length, RootVegetable[].class);
	}
    return null;
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