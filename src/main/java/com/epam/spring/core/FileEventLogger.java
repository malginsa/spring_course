package com.epam.spring.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileEventLogger implements EventLogger{

	private final Boolean APPEND = true;
	private File file;
	private String filename;

	public FileEventLogger(String filename) {
		this.filename = filename;
	}

	private void init() throws IOException {
		this.file = new File(filename);
		if(!file.canWrite()) {
			throw new IOException("Can't write to file " + this.filename);
		}
	}

	public void logEvent(Event event) {
		try {
			FileUtils.writeStringToFile(file, event.toString()+"\n", APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
