package command;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCommand implements Command {

	private DrawingModel model;
	private Shape shape;
	private int index = -1;

	public AddShapeCommand(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}

	@Override
	public void execute() {

		if (index == -1) {
			index = model.size();
		}

		model.add(index, shape);
	}

	@Override
	public void unexecute() {

		model.remove(index);
	}

	@Override
	public String toString() {

		return "ADD " + ShapeLogFormatter.format(shape);
	}
}