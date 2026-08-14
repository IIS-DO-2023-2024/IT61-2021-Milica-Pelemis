package command;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class DeleteShapeCommand implements Command {

	private DrawingModel model;
	private List<Shape> deletedShapes = new ArrayList<Shape>();
	private List<Integer> indexes = new ArrayList<Integer>();

	public DeleteShapeCommand(
			DrawingModel model,
			List<Shape> shapes,
			List<Integer> indexes) {

		this.model = model;
		this.indexes.addAll(indexes);

		for (Shape shape : shapes) {
			this.deletedShapes.add(shape.clone());
		}
	}

	@Override
	public void execute() {

		for (int i = indexes.size() - 1; i >= 0; i--) {
			model.remove(indexes.get(i));
		}
	}

	@Override
	public void unexecute() {

		for (int i = 0; i < indexes.size(); i++) {
			model.add(
					indexes.get(i),
					deletedShapes.get(i).clone());
		}
	}
}