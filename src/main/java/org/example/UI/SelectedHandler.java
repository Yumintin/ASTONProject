package org.example.UI;

import org.example.IO.*;
import org.example.RandomGeneration.*;
import org.example.BinarySearch.BinarySearch;
import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;
import org.example.MergeSort.CustomSorterEvenOdd;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

// Класс для управления логикой интерфейса в зависимости от пользовательского ввода
public class SelectedHandler {
	private UserInterface ui;
	private RandomDataGenerator random;

	public SelectedHandler(UserInterface ui) {
		this.ui = ui;
	}

	// Функция получения длины массива
	public int getArrayLength() {
	int desiredLength = -1; // Инициализация длины массива
	while (desiredLength < 0) {
		System.out.print("Введите размер массива (должен быть неотрицательным): ");
			if (ui.scanner.hasNextInt()) {
				desiredLength = ui.scanner.nextInt();
				if (desiredLength < 0) {
					System.out.println("Размер массива не может быть отрицательным. Попробуйте снова.");
				}
			} else {
				System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
				ui.scanner.next(); // Очищаем некорректный ввод
			}
		}
		return desiredLength;
	}

	// Функция для выбора метода заполнения данных
	public Object[] methodSelected(String selectedClass, InputHandler handler, int arrayLength) {
		Object[] array = null;
		ui.chooseMethod ();		// Вызываем вывод сообщения в консоль
		while (true) {
			// Получаем то, что ввел пользователь
			String choose = ui.line ("");
			try {
				switch (choose) {
					case "1":			// Выбираем ручной метод заполнения массива
						array = handler.manual (selectedClass, arrayLength);
						return array;
					case "2":			// Рандомная генерация
						array = switch (selectedClass) {
							case "Car" -> RandomDataGenerator.generateRandomCars (arrayLength);
							case "Book" -> RandomDataGenerator.generateRandomBooks (arrayLength);
							case "RootVegetable" -> RandomDataGenerator.generateRandomRootVegetable (arrayLength);
							default -> null;
						};
						return array;
					case "3":			// Выбираем ввод данных из файла
						array = DataLoader.loadFromFile (selectedClass, arrayLength);
						return array;
					case "0":
						return null;
				}
			} catch (NumberFormatException e) {
				System.out.println ("Неверный ввод. Введите число.");
			}
		}
	}

		// Функция с операциями для массива
		public void arrayOperations(UserInterface ui, InputHandler handler, SelectedHandler select, Object[]
		array, String selectedClass) throws IOException {
			if (array != null) {
				System.out.println ("До сортировки");
				for (Object item : array) {
					System.out.println (item);
				}
				handler.sorting (array, selectedClass); //сортируем
				System.out.println("\nМассив отсортирован!");
				DataWriter.write (InputHandler.getDataAsString (array), "");

				boolean exit = false;	// Булевое значение для работы с выходом из цикла
				while (!exit) {
					ui.chooseOperation ();
					String choose = ui.line ("");
					switch (choose) {
						case "1":	// Поиск
							Comparator<Object> handlerComparator = (Comparator<Object>) handler.getComparator (selectedClass);
							Object key = handler.classExReturner (selectedClass);
							System.out.println ("\nИндекс найденного элемента: " + BinarySearch.binarySearch (array, key, handlerComparator));
							break;
						case "2":	// Четная сортировка
							CustomSorterEvenOdd.sortEvenNumbersAfterMergeSort (reWriter (array, selectedClass));
							System.out.println ("\nОтсортировано");
							for (Object item : array) {
								System.out.println (item);
							}
							break;
						case "0":
							exit = true; // Выход в предыдущее меню, если введен "0"
					}
				}
			} else {
				System.err.println ("Массив не был создан.");
			}
		}

		@SuppressWarnings("unchecked")
		private <T > T[]reWriter (Object[]array, String selectedClass) {
			if (selectedClass.equals ("Car"))
				return (T[]) Arrays.copyOf (array, array.length, Car[].class);
			else if (selectedClass.equals ("Book"))
				return (T[]) Arrays.copyOf (array, array.length, Book[].class);
			else if (selectedClass.equals ("RootVegetable"))
				return (T[]) Arrays.copyOf (array, array.length, RootVegetable[].class);
			return null;
		}
	}