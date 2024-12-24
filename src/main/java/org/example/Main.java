package org.example;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.example.files.XlsFileToClassObject.*;

public class Main {
	private final LogInterface log;

	public Main(LogInterface loginterface) {
		this.log = loginterface;
	}

	public void initialize() throws IOException {
		System.out.println("Инициализация интерфейса программы...");
		Scanner scanner = new Scanner(System.in);
		String input;

		System.out.println("\nДобро пожаловать в программу \"Aston\"");
		System.out.println("Для выхода из программы введите end");

		do {
			System.out.println("\nВыберите класс: ");
			System.out.println("1.\tАвтомобиль");
			System.out.println("2.\tКнига");
			System.out.println("3.\tКорнеплод");

			input = scanner.nextLine().trim();
			if (input.equalsIgnoreCase("end")) {
				break;
			}

			try {
				int operation = Integer.parseInt(input);
				String selectedClass = classSelected(operation);
				if (selectedClass.isEmpty()) {
					continue;
				}
				System.out.println("Вы выбрали класс " + selectedClass);
				System.out.println(" =========================");

				boolean validMethodSelected;
				do {
					validMethodSelected = true;
					System.out.println("Выберите метод заполнения:");
					System.out.println("1.\tРучной");
					System.out.println("2.\tРандомный");
					System.out.println("3.\tИз файла");
					System.out.println("0.\tВернуться к выбору класса");

					input = scanner.nextLine().trim();
					if (input.equalsIgnoreCase("end")) {
						break;
					}

					int method = Integer.parseInt(input);
					if (method == 0) {
						continue;
					}

					String methodSelected = methodSelected(method, selectedClass);
					if (methodSelected.isEmpty()) {
						validMethodSelected = false;
					}
				} while (!validMethodSelected && !input.equalsIgnoreCase("end"));

			} catch (NumberFormatException e) {
				System.out.println("Неверный ввод. Введите число.");
			} catch (IOException e) {
				System.out.println("При обработке файла произошла ошибка: " + e.getMessage());
			}
		} while (!input.equalsIgnoreCase("end"));
		System.out.println("Выход...");
		scanner.close();
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
				return "";
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
				String FILELOCATION = scanner.nextLine();

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
				return "";
		}
		return selectedMethod;
	}

	public static void main(String[] args) {
		try {
			Log initLog = new Log("app.log");
			Main app = new Main(initLog);
			app.initialize();
			initLog.closeLog();
		} catch (IOException e) {
			System.err.println("Невозможно создать лог: " + e.getMessage());
		}
	}
}