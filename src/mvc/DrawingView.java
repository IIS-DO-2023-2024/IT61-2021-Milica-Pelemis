package mvc;

import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel{

	public DrawingView() {
		
	}
	
	private DrawingModel model = new DrawingModel();
	
	public DrawingModel getModel() {
		return model;
	}
	
	public void setModel (DrawingModel model) {
		this.model=model;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		ListIterator<Shape> it = model.getShapes().listIterator();
	       while (it.hasNext()) { 
	       	it.next().draw(g);
	       }
	}
}
