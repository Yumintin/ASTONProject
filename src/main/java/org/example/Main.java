package org.example;
import java.io.IOException;

public class Main {
	private final LogInterface log;

	public Main(LogInterface loginterface) {
		this.log = loginterface;
	}

	public void initialize() {
		log.msg("Initializing program routines...");
		/* */
	}

	public static void main(String[] args) {
		try {
			Log initLog = new Log("app.log");
			Main app = new Main(initLog);
			app.initialize();
			initLog.closeLog();
		} catch (IOException e) {
			System.err.println("Can't create log: " + e.getMessage());
		}
	}
}