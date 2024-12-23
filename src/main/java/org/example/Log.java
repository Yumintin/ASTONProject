package org.example;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Log implements LogInterface {
	private PrintWriter writer;

	private static final String RESET	= "\u001B[0m";
	private static final String RED		= "\u001B[31m";
	private static final String YELLOW	= "\u001B[33m";

	private void log(String message) {
		StringBuilder consoleMessage = new StringBuilder();
		String fileMessage;
		if (message.startsWith("!")) {
			consoleMessage.append(RED).append(message.substring(1)).append(RESET);
			fileMessage = "ERROR: " + message.substring(1);
		} else if (message.startsWith("*")) {
			consoleMessage.append(YELLOW).append(message.substring(1)).append(RESET);
			fileMessage = "WARNING: " + message.substring(1);
		} else {
			consoleMessage.append(message);
			fileMessage = message;
		}
		System.out.println(consoleMessage);
		if (writer != null) {
			writer.println(fileMessage);
			writer.flush();
		}
	}

	public Log(String fileName) throws IOException {
		createLog(fileName);
	}

	@Override
	public void msg(String message) {
		log(message);
	}

	public void createLog(String fileName) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName, false);
		this.writer = new PrintWriter(fileWriter);
	}

	public void closeLog() {
		if (writer != null) {
			writer.close();
			writer = null;
		}
	}
}
