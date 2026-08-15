package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class DrawingSaveStrategy implements SaveStrategy {

	private DrawingModel model;

	public DrawingSaveStrategy(DrawingModel model) {
		this.model = model;
	}

	@Override
	public void save(File file) throws IOException {

		ObjectOutputStream output =
				new ObjectOutputStream(
						new FileOutputStream(file));

		output.writeObject(
				new ArrayList<Shape>(
						model.getShapes()));

		output.close();
	}
}