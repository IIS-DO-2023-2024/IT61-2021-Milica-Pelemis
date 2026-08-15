package strategy;

import java.io.File;
import java.io.IOException;

public class SaveContext {

	private SaveStrategy strategy;

	public void setStrategy(SaveStrategy strategy) {
		this.strategy = strategy;
	}

	public void save(File file) throws IOException {

		if (strategy != null) {
			strategy.save(file);
		}
	}
}