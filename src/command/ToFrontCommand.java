package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCommand implements Command {

	private DrawingModel model;
	private int oldIndex;
	private int newIndex;
	private Shape shape;

	public ToFrontCommand(DrawingModel model, int oldIndex) {
		this.model = model;
		this.oldIndex = oldIndex;
		this.newIndex = oldIndex + 1;
	}

	@Override
	public void execute() {

		shape = model.get(oldIndex);

		model.remove(oldIndex);
		model.add(newIndex, shape);
	}

	@Override
	public void unexecute() {

		shape = model.get(newIndex);

		model.remove(newIndex);
		model.add(oldIndex, shape);
	}

	@Override
	public String toString() {

		return "TO_FRONT" + " oldIndex=" + oldIndex + " newIndex=" + newIndex + " shape=[" + ShapeLogFormatter.format(shape) + "]";
	}
}