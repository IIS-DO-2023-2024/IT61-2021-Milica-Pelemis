package drawing;

import javax.swing.JPanel;

import geometry.Point;
import geometry.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class PnlDrawing extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private ArrayList<Shape> shapes = new ArrayList <Shape>();
	private int i;
	
	public PnlDrawing() {
		setBackground(Color.WHITE);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it =shapes.iterator();
		while(it.hasNext())
		{
		it.next().draw(g); 
    	}
	}
	
	public void addShape (Shape shape)
	{
		shapes.add(shape);
		repaint();
	}
	
	public Shape getShape(int j)
	{
		return shapes.get(j);
	}
	
	public void setShape(int j, Shape shape) {
		shapes.set(j, shape);
	}
	
	public void removeSelected() {
		shapes.removeIf(shape -> shape.isSelected());
		repaint();
	}
	
	public void deselect() {
		shapes.forEach(shape -> shape.setSelected(false));
		repaint();
	}
	
	public void select(Point point) {
		for (i = shapes.size()-1; i >= 0; i--) {
			if (shapes.get(i).contains(point.getX(), point.getY())) 
			{
				shapes.get(i).setSelected(true);
				repaint();
				return;
			}
		}
	}
	
	public int getSelected() {
		for (i = shapes.size()-1; i >= 0; i--) {
			if (shapes.get(i).isSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isEmpty() {
		return shapes.isEmpty();
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	

}
