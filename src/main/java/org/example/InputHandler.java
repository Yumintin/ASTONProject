package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		Scanner scanner = ui.getScanner();

		System.out.print("Выберите сортировку по полю: ");
		if ("Car".equals(selectedClass)) {
			System.out.print("power,model,year");
		} else if ("Book".equals(selectedClass)) {
			System.out.print("author,title,pages");
		} else if ("RootVegetable".equals(selectedClass)) {
			System.out.print("type,weight,color");
		}

		String fieldName = scanner.nextLine().trim();
		try {
			sortContext.executeSort(array, fieldName);
			System.out.println("Отсортированные объекты:");
			for (Object obj : array) {
				System.out.println(obj.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
