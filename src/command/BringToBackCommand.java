package command;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCommand implements Command {

	private DrawingModel model;
	private int oldIndex;
	private int newIndex;
	private Shape shape;

	public BringToBackCommand(DrawingModel model, int oldIndex) {
		this.model = model;
		this.oldIndex = oldIndex;
		this.newIndex = 0;
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

		return "BRING_TO_BACK" + " oldIndex=" + oldIndex + " newIndex=" + newIndex + " shape=[" + ShapeLogFormatter.format(shape) + "]";
	}
}