package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCommand implements Command {

	private DrawingModel model;
	private int oldIndex;
	private int newIndex;

	public ToBackCommand(DrawingModel model, int oldIndex) {
		this.model = model;
		this.oldIndex = oldIndex;
		this.newIndex = oldIndex - 1;
	}

	@Override
	public void execute() {

		Shape shape = model.get(oldIndex);

		model.remove(oldIndex);
		model.add(newIndex, shape);
	}

	@Override
	public void unexecute() {

		Shape shape = model.get(newIndex);

		model.remove(newIndex);
		model.add(oldIndex, shape);
	}
}