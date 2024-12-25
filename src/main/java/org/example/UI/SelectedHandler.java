package org.example.UI;

import java.io.IOException;
import java.util.List;

public class SelectedHandler {
	private UserInterface ui;
	public SelectedHandler(UserInterface ui) {
		this.ui = ui;
	}

	public void methodSelected(String selectedClass, InputHandler handler) {
		while (true) {
			ui.chooseMethod();
			String input = ui.getInput();
			if ("0".equalsIgnoreCase(input)) {
				break;
			}
			try {
				int method = Integer.parseInt(input);
				switch (method) {
					case 1:
						manualMethod (selectedClass, handler);
						break;
					case 2:
					case 3:
						break;
					default:
						return;
				}
			} catch (NumberFormatException e) {
				System.out.println("Неверный ввод. Введите число.");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void manualMethod(String selectedClass, InputHandler handler) throws IOException {
		List<?> objects = handler.manual(selectedClass);
		if (objects != null && !objects.isEmpty()) {
			Object[] array = objects.toArray(new Object[0]);
			boolean exit = false;
			while (!exit) {
				ui.chooseOperation();
				String choose = ui.line("");
				switch (choose) {
					case "1":
					case "2":
					case "3":
						handler.sorting(array, selectedClass);
						break;
					case "0":
						exit = true;
						break;
					default:
						return;
				}
			}
		}
	}
}
