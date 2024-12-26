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
	public List<?> manual(String selectedClass) throws IllegalArgumentException {
		if ("Car".equals(selectedClass)) {                  // Проверяем, что выбрана машина
			return inputCars(); // Вызов соотв. функции
		} else if ("Book".equals(selectedClass)) {          // Проверяем, что выбрана книга
			return inputBooks();
		} else if ("RootVegetable".equals(selectedClass)) { // Проверяем, что выбран Виктор Корнеплод
			return inputRoots();
		}
		// Если неверное имя класса, то даем исключение
		throw new IllegalArgumentException("Невалидный класс");
	}

	// Функция для ручного ввода параметров автомобиля
	private List<Car> inputCars() throws IllegalArgumentException {
		List<Car> cars = new ArrayList<>(); // Инициализируем список для хранения созданных объектов
		while (true) {                      // Цикл на неопределенный срок, пока не будет введен "end"
			String input = ui.line("Введите параметры машины (мощность, модель, год)\n" +
					"Для завершения ввода введите 'end':");

			// Проверка на ввод "end
			if ("end".equalsIgnoreCase(input)) {
				break;
			}

			// В качестве разделителя параметров использовать "," для корректной логики ввода
			String[] delimiter = input.split(",");

			// Если пользователь проставил != 3 запятых, то дать сообщение, что так делать нельзя
			if (delimiter.length != 3) {
				System.out.println("Неверный тип записи. Введите значения через запятую.");
				continue;
			}
			try {
				// Определение и обрезка значений {int,String,int}
				int power = Integer.parseInt(delimiter[0].trim());
				String model = delimiter[1].trim();
				int year = Integer.parseInt(delimiter[2].trim());

				// Инициализируем новый объект через билдер
				Car car = new Car.Builder().setPower(power).setModel(model).setYear(year).build();
				cars.add(car);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage()); // На всякий случай, если что-то пойдет не так
			}
		}
		return cars;    // Возвращаем список созданных объектов данного класса
	}

	/// Функция полностью повторяет логику функции inputCars
	private List<Book> inputBooks() throws IllegalArgumentException {
		List<Book> books = new ArrayList<>();
		while (true) {
			String input = ui.line("Введите параметры книги (автор, название, кол-во страниц)\n" +
					"Для завершения ввода введите 'end':");
			if ("end".equalsIgnoreCase(input)) {
				break;
			}
			String[] delimiter = input.split(",");
			if (delimiter.length != 3) {
				System.out.println("Неверный тип записи. Введите значения через запятую.");
				continue;
			}
			try {
				// Определение и обрезка значений {String,String,int}
				String author = delimiter[0].trim();
				String model = delimiter[1].trim();
				int page = Integer.parseInt(delimiter[2].trim());

				Book book = new Book.Builder().setAuthor(author).setTitle(model).setPages(page).build();
				books.add(book);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return books;
	}

	/// Функция полностью повторяет логику функции inputCars
	private List<RootVegetable> inputRoots() throws IllegalArgumentException {
		List<RootVegetable> roots = new ArrayList<>();
		while (true) {
			String input = ui.line("Введите параметры корнеплода (тип, вес, цвет)\n" +
					"Для завершения ввода введите 'end':");
			if ("end".equalsIgnoreCase(input)) {
				break;
			}
			String[] delimiter = input.split(",");
			if (delimiter.length != 3) {
				System.out.println("Неверный тип записи. Введите значения через запятую.");
				continue;
			}
			try {
				// Определение и обрезка значений {String,Double,String}
				String type = delimiter[0].trim();
				double weight = Double.parseDouble (delimiter[1].trim());
				String color = delimiter[2].trim();

				RootVegetable root = new RootVegetable.Builder().setType(type).setWeight(weight).setColor(color).build();
				roots.add(root);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return roots;
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
			System.out.println("Отсортированные объекты:");

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
	private Comparator<?> getComparator(String selectedClass) {
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
}
