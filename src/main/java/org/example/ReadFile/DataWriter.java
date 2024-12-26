package org.example.ReadFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {
	private static final String OUT = "export";

	// Запись результата сортировки в файл
	public static void write(String data) {
		File directory = new File(OUT);

		// Базовый случай валидации - создать dir, если не существует
		if (!directory.exists()) {
			directory.mkdirs();
		}
		File file = new File(directory, "result.log");

		// append: true для добавления данных не перезаписывая
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			writer.write(data);
			writer.newLine();   // Записывать через строку для лучшего восприятия
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}