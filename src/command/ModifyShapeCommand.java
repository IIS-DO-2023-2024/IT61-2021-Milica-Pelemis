package command;

import geometry.Shape;
import mvc.DrawingModel;

public class ModifyShapeCommand implements Command {

	private DrawingModel model;
	private Shape oldShape;
	private Shape newShape;
	private int index;

	public ModifyShapeCommand(
			DrawingModel model,
			Shape oldShape,
			Shape newShape,
			int index) {

		this.model = model;
		this.oldShape = oldShape.clone();
		this.newShape = newShape.clone();
		this.index = index;
	}

	@Override
	public void execute() {
		model.set(index, newShape.clone());
	}

	@Override
	public void unexecute() {
		model.set(index, oldShape.clone());
	}

	@Override
	public String toString() {

		return "MODIFY"
				+ " index=" + index
				+ " old=[" + ShapeLogFormatter.format(oldShape) + "]"
				+ " new=[" + ShapeLogFormatter.format(newShape) + "]";
	}
}