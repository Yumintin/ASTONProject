package org.example.UI;

import org.example.CustomClasses.Book;
import org.example.CustomClasses.Car;
import org.example.CustomClasses.RootVegetable;
import org.example.MergeSort.MergeSort;
import org.example.MergeSort.SortContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InputHandler {
	private UserInterface ui;
	public InputHandler(UserInterface ui) {
		this.ui = ui;
	}

	public List<?> manual(String selectedClass) throws IllegalArgumentException {
		if ("Car".equals(selectedClass)) {
			return inputCars();
		} else if ("Book".equals(selectedClass)) {
			return inputBooks();
		} else if ("RootVegetable".equals(selectedClass)) {
			return inputRoots();
		}
		throw new IllegalArgumentException("Невалидный класс");
	}

	private List<Car> inputCars() throws IllegalArgumentException {
		List<Car> cars = new ArrayList<>();
		while (true) {
			String input = ui.line("Введите параметры машины (мощность, модель, год)\n" +
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
				int power = Integer.parseInt(delimiter[0].trim());
				String model = delimiter[1].trim();
				int year = Integer.parseInt(delimiter[2].trim());

				Car car = new Car.Builder().setPower(power).setModel(model).setYear(year).build();
				cars.add(car);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return cars;
	}

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

	public void sorting(Object[] array, String selectedClass) {
		SortContext<Object> sortContext = new SortContext<>();
		sortContext.setStrategy(new MergeSort<>());
		Comparator<Object> comparator = (Comparator<Object>) getComparator(selectedClass);
		try {
			sortContext.executeSort(array, comparator);
			System.out.println("Отсортированные объекты:");
			for (Object obj : array) {
				System.out.println(obj.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private Comparator<?> getComparator(String selectedClass) {
		switch (selectedClass) {
			case "Car":
				return Car.getComparator();
			case "Book":
				return Book.getComparator();
			case "RootVegetable":
				return RootVegetable.getComparator();
			default:
				return null;
		}
	}
}
