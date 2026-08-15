package geometry;

import java.awt.Color;
import java.awt.Graphics;

import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Prototype, Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean selected;
	private Color color;

	public abstract boolean contains(int x, int y);

	public abstract void draw(Graphics g);

	public abstract Shape clone();

	public Shape(boolean selected) {
		this.selected = selected;
	}

	public Shape() {

	}

	public Shape(Color color)
	{
		this.color = color;
	}

	public Shape(Color color, boolean selected)
	{
		this(color);
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}