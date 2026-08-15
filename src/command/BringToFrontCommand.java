package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCommand implements Command {

	private DrawingModel model;
	private int oldIndex;
	private int newIndex;
	private Shape shape;

	public BringToFrontCommand(DrawingModel model, int oldIndex) {
		this.model = model;
		this.oldIndex = oldIndex;
		this.newIndex = model.size() - 1;
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

		return "BRING_TO_FRONT"
				+ " oldIndex=" + oldIndex
				+ " newIndex=" + newIndex
				+ " shape=[" + ShapeLogFormatter.format(shape) + "]";
	}
}