package org.example.UI;

import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;
import org.example.MergeSort.MergeSort;
import org.example.MergeSort.SortContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Класс для непосредственной обработки данных
public class InputHandler {
	private UserInterface ui;
	public InputHandler(UserInterface ui) {
		this.ui = ui;
	}

	/*  Функция-распределитель.
		Отправляем отсюда вводить параметры вручную
		В зависимости от выбранного класса
	*/
	public Object classExReturner (String selectedClass)
	{
        return switch (selectedClass) {
            case "Car" -> inputCar();
            case "Book" -> inputBook();
            case "RootVegetable" -> inputRoot();
            default -> new Object[0];
        };
    }
	public Object[] manual(String selectedClass,int arrayLength) throws IllegalArgumentException {
		if ("Car".equals(selectedClass)) {                  // Проверяем, что выбрана машина
			return inputCars(arrayLength); // Вызов соотв. функции
		} else if ("Book".equals(selectedClass)) {          // Проверяем, что выбрана книга
			return inputBooks(arrayLength);
		} else if ("RootVegetable".equals(selectedClass)) { // Проверяем, что выбран Виктор Корнеплод
			return inputRoots(arrayLength);
		}
		// Если неверное имя класса, то даем исключение
		throw new IllegalArgumentException("Невалидный класс");
	}
	private Car inputCar(){
		String[] delimiter;
		Car car = null;

		while (true) {
			String input = ui.line("Введите параметры машины в формате:\nмощность, модель, год\n");
			// В качестве разделителя параметров использовать "," для корректной логики ввода
			delimiter = input.split(",");

			// Если пользователь проставил не 3 значения, вывести сообщение
			if (delimiter.length != 3) {
				System.out.println("Неверный тип записи. Введите значения через запятую.");
				continue; // Возвращаемся к началу цикла
			}
			try {
				// Определение и обрезка значений {int,String,int}
				int power = Integer.parseInt(delimiter[0].trim());
				String model = delimiter[1].trim();
				int year = Integer.parseInt(delimiter[2].trim());

				// Инициализируем новый объект через билдер
				car = new Car.Builder().setPower(power).setModel(model).setYear(year).build();
				break; // Выход из цикла, если объект успешно создан
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: мощность и год должны быть целыми числами. Пожалуйста, попробуйте снова.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage()); // На всякий случай, если что-то пойдет не так
			}
		}
		return car; // Возвращаем созданный объект или null, если произошла ошибка
	}
	// Функция для ручного ввода параметров автомобиля
	private Car[] inputCars(int arrayLength) throws IllegalArgumentException {
		Car[] cars = new Car[arrayLength]; // Инициализируем список для хранения созданных объектов
		for (int i=0;i<arrayLength;i++) {                      // Цикл основанный на длине массива
			System.out.println("До заполнения массива осталось:"+(arrayLength-i)+" шагов");
			cars[i]=inputCar();
        }
		return cars;    // Возвращаем список созданных объектов данного класса
	}

	/// Функция полностью повторяет логику функции inputCars
	// Метод для ввода параметров одной книги
	private Book inputBook() {
		String[] delimiter;
		Book book = null;
		while (true) {
			String input = ui.line("Введите параметры книги в формате:\nавтор, название, кол-во страниц\n");
			delimiter = input.split(","); // Разделяем ввод по запятой
			// Проверяем, что введено ровно 3 параметра
			if (delimiter.length != 3) {
				System.out.println("Неверный тип записи. Введите значения через запятую.");
				continue; // Возвращаемся к началу цикла
			}
			try {
				// Определяем и обрезаем значения {String, String, int}
				String author = delimiter[0].trim();
				String title = delimiter[1].trim();
				int pages = Integer.parseInt(delimiter[2].trim());

				// Инициализируем новый объект через билдер
				book = new Book.Builder().setAuthor(author).setTitle(title).setPages(pages).build();
				break; // Выход из цикла, если объект успешно создан
			} catch (NumberFormatException e) {
				System.out.println("Ошибка: количество страниц должно быть целым числом. Пожалуйста, попробуйте снова.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage()); // Обработка исключений на случай ошибок
			}
		}
		return book; // Возвращаем созданный объект книги или null, если произошла ошибка
	}


	// Метод для ввода массива книг
	private Book[] inputBooks(int arrayLength) throws IllegalArgumentException {
		Book[] books = new Book[arrayLength]; // Инициализируем массив для хранения книг

		for (int i = 0; i < arrayLength; i++) {
			System.out.println("До заполнения массива осталось:" + (arrayLength - i) + " шагов");
			books[i] = inputBook(); // Вводим одну книгу, используя метод inputBook
		}

		return books; // Возвращаем массив созданных книг
	}

	/// Функция полностью повторяет логику функции inputCars
// Метод для ввода параметров одного корнеплода
	private RootVegetable inputRoot() {
		String[] delimiter;
		RootVegetable root = null;

		while (true) {
			String input = ui.line("Введите параметры корнеплода (тип, вес, цвет)\n");
			delimiter = input.split(","); // Разделяем ввод по запятой
			// Проверяем, что введено ровно 3 параметра
			if (delimiter.length != 3) {
				System.out.println("Неверный тип записи. Введите значения через запятую.");
				continue; // Запрашиваем ввод снова
			}
			try {
				// Определение и обрезка значений {String, Double, String}
				String type = delimiter[0].trim();
				double weight = Double.parseDouble(delimiter[1].trim());
				String color = delimiter[2].trim();
				// Инициализируем новый объект через билдер
				root = new RootVegetable.Builder().setType(type).setWeight(weight).setColor(color).build();
				break; // Если объект создан успешно, выходим из цикла
			} catch (NumberFormatException e) {
				System.out.println("Вес должен быть числом. Пожалуйста, попробуйте снова.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage()); // Обработка исключений на случай ошибок
			}
		}
		return root; // Возвращаем созданный объект корнеплода или null, если произошла ошибка
	}


	// Метод для ввода массива корнеплодов
	private RootVegetable[] inputRoots(int arrayLength) throws IllegalArgumentException {
		RootVegetable[] roots = new RootVegetable[arrayLength]; // Инициализируем массив для хранения корнеплодов

		for (int i = 0; i < arrayLength; i++) {
			System.out.println("До заполнения массива осталось:" + (arrayLength - i) + " шагов");
			roots[i] = inputRoot(); // Вводим один корнеплод, используя метод inputRoot
		}

		return roots; // Возвращаем массив созданных корнеплодов
	}

	/**
	 *  Вызов сортировщика на основе предоставленного компаратора и получаемого класса.
	 *  @param array массив объектов, подлежащих сортировке.
	 *  @param selectedClass строка, представляющая имя получаемого класса
	*/
	public void sorting(Object[] array, String selectedClass) {
		// Инициализируем объекты для работы с сортировщиком
		SortContext<Object> sortContext = new SortContext<>();
		sortContext.setStrategy(new MergeSort<>());

		// Получаем соответствующий компаратор на основе выбранного класса
		Comparator<Object> comparator = (Comparator<Object>) getComparator(selectedClass);
		try {
			// Выполняем операцию сортировки, используя предоставленный компаратор
			sortContext.executeSort(array, comparator);
			System.out.println("\nОтсортированные объекты:");

			// Проходим по отсортированному массиву для вывода в консоль каждого объекта
			for (Object obj : array) {
				System.out.println(obj.toString());
			}
		} catch (Exception e) { // Обработка любых исключений, возникающих во время сортировки
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Получаем соответствующий компаратор для данного имени класса.
	 * @param selectedClass строка, представляющая имя получаемого класса.
	 */
	public Comparator<?> getComparator(String selectedClass) {
		switch (selectedClass) {
			case "Car":             // Получаем компаратор, соответствующий классу Car
				return Car.getComparator();
			case "Book":            // Получаем компаратор, соответствующий классу Book
				return Book.getComparator();
			case "RootVegetable":   // Получаем компаратор, соответствующий классу RootVegetable
				return RootVegetable.getComparator();
			default:
				return null;    // Если указан неизвестный класс.
		}
	}

	// Преобразование отсортированного массива объектов в строку для записи в файл
	public static String getDataAsString(Object[] array) {
		StringBuilder str = new StringBuilder();
		for (Object obj : array) {
			str.append(obj.toString()).append("\n");
		}
		return str.toString();
	}
}
