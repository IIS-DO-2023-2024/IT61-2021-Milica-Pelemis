package strategy;

import java.io.File;
import java.io.IOException;

public interface SaveStrategy {

	void save(File file) throws IOException;
}