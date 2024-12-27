package org.example.ReadFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DataWriter {
	private static final String OUT = "export";
	private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("(dd/MM/yyyy): HH:mm:ss");

	// Запись результата сортировки в файл
	public static void write(String data, String startup) {
		File directory = new File(OUT);

		// Базовый случай валидации - создать dir, если не существует
		if (!directory.exists()) {
			directory.mkdirs();
		}
		File file = new File(directory, "result.log");

		// append: true для добавления данных не перезаписывая
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
			if (Objects.equals (startup, "true")) {
				timestamp(writer);
			}
			writer.write(data);
			writer.newLine();   // Записывать через строку для лучшего восприятия
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void timestamp(BufferedWriter writer) throws IOException {
		LocalDateTime now = LocalDateTime.now();
		String timestamp = now.format(TIMESTAMP);
		writer.write(timestamp);
	}
}