package org.example.UI;

import org.example.CustomClasses.*;
import org.example.RandomGeneration.*;
import org.example.ReadFile.DataLoader;

import java.io.IOException;
import java.util.List;

// Класс для управления логикой интерфейса в зависимости от пользовательского ввода
public class SelectedHandler {
	private UserInterface ui;
	private RandomDataGenerator random;

	public SelectedHandler(UserInterface ui) {
		this.ui = ui;
	}

	// Функция для выбора метода заполнения данных
	public Object[] methodSelected(String selectedClass, InputHandler handler, int arrayLength) {
		Object[] array = null;

			// Вызываем вывод сообщения в консоль
			ui.chooseMethod();

			// Получаем то, что ввел пользователь
			String input = ui.getInput();
			// Если введен "0", то возвращаем в предыдущее меню
			if ("0".equalsIgnoreCase(input)) {
				return null;
			}

			try {
				int method = Integer.parseInt(input);           // Преобразовываем в целое число для обработки

				switch (method) {
					case 1:     // Выбираем ручной метод заполнения массива
						array = handler.manual(selectedClass, arrayLength);   // Вызов функции с логикой работы интерфейса ручного ввода
						break;
					case 2:
						array = switch (selectedClass) {
							case "Car" -> RandomDataGenerator.generateRandomCars(arrayLength);
							case "Book" -> RandomDataGenerator.generateRandomBooks(arrayLength);
							case "RootVegetable" ->
									RandomDataGenerator.generateRandomRootVegetable(arrayLength);
							default -> null;
						};// Выбираем генерацию случайных значений для заполнения массива
						break;
					case 3:     // Выбираем ввод данных из файла
						array = DataLoader.loadFromFile(selectedClass, arrayLength);
						break;
					default:
						return null; // Возврат в случае неверного ввода
				}
			} catch (NumberFormatException e) {
				System.out.println("Неверный ввод. Введите число.");
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
			}


        return array;
    }
}


		/*
		 *	Обрабатываем ручной метод ввода данных, позволяя
		 *	вводить объекты вручную, а затем выполнять над ними операции.
		 *	@param selectedClass стринг, представляющий имя текущего выбранного класса
		 *  @param handler отвечает за обработку ввода и выполнение операций с данными.
		 */


		/*if (objects != null && !objects.isEmpty()) {            // Условие, что список не пуст
			Object[] array = objects.toArray(new Object[0]);    // Преобразовываем список в массив для сортировки




			boolean exit = false;   // Булевое значение для работы с выходом из цикла
			while (!exit) {
				// Вывод меню для выбора того, что нужно делать
				handler.sorting(array, selectedClass);//сортируем
				ui.chooseOperation();
				String choose = ui.line("");
				switch (choose) {
					case "1":   // Поиск

						break;
					case "2":   // Запись значений в файл
						break;
					case "0":   // Выход в предыдущее меню, если введен "0"
						exit = true;
						break;
					default:    // Повторный вызов при невалидном вводе
						return;
				}
			}
		}*/
