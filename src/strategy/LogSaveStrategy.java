package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogSaveStrategy implements SaveStrategy {

	private String log;

	public LogSaveStrategy(String log) {
		this.log = log;
	}

	@Override
	public void save(File file) throws IOException {

		BufferedWriter writer =
				new BufferedWriter(new FileWriter(file));

		writer.write(log);

		writer.close();
	}
}