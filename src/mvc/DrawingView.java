package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {

	public DrawingView() {
		setBackground(Color.WHITE);
		setOpaque(true);
	}

	private DrawingModel model = new DrawingModel();

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public void paint(Graphics g) {
		super.paint(g);
		ListIterator<Shape> it = model.getShapes().listIterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}
}