package edu.adias.powermock.easymock.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
	private PrintWriter writer;

    public Logger() {
        try {
			writer = new PrintWriter(new FileWriter("target/logger.log"));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
    }

    public void log(String message) {
    	writer.println(message);
    }
}
