package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShapes {

	private Point upperLeft;
	private int width;
	private int height;
	
	//Konstruktori
	
	public Rectangle() {
		
	}
	public Rectangle (Point upperLeft, int width, int height) {
		this.upperLeft=upperLeft;
		this.width=width;
		this.height=height;
	}
	public Rectangle (Point upperLeft, int width, int height, boolean selected) {
		this(upperLeft,width, height);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeft, int width, int height, boolean selected, Color color)
	{
		this(upperLeft, width, height, selected);
		setColor(color);
	}
	
	public Rectangle (Point upperLeft, int width, int height, Color color, Color innerColor)
	{
		this(upperLeft, width, height);
		setColor(color);
		setInnerColor(innerColor);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, width, height, selected, color);
		setInnerColor(innerColor);
	}
	
	public boolean equals (Object obj) {
		if (obj instanceof Rectangle)
		{
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeft.equals(pomocna.getUpperLeft()) && this.width == width && this.height== height) {
				return true;
			}
			else {
				return false;
			}
		}
		else 
		{
			return false;
		}
	}
	
	public String toString () {
		return upperLeft + ",width" + width + ", height" + height; 
	}
	
	//Overloading
	public boolean contains (int x, int y) {
		return (upperLeft.getX() <x && upperLeft.getX() + width > x && upperLeft.getY() < y && upperLeft.getY() + height > y);
	}
	
	public boolean contains (Point p) {
		return upperLeft.getX() < p.getX() && upperLeft.getX() + width > p.getX() && upperLeft.getY() < p.getY() && upperLeft.getY() + height > p.getY();
	}
	
	//Povrsina pravougaonika
	public int area() {
		return width*height;
		
	}
	//Obim pravougaonika
	public int circumference() {
		return 2*width + 2*height;
	}
	
	//Metode pristupa
	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft; 
	}
	public int getwidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight () {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void fill(Graphics g)
	{
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeft.getX()+1, this.upperLeft.getY()+1, this.width-1, this.height-1);
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());

		g.drawRect(upperLeft.getX(), upperLeft.getY(), width, height);
		fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeft.getX()-2, upperLeft.getY()-2, 4, 4);
			g.drawRect(upperLeft.getX()+width-2, upperLeft.getY()-2, 4, 4);
			g.drawRect(upperLeft.getX()-2, upperLeft.getY()+ height-2, 4, 4);
			g.drawRect(upperLeft.getX()+width-2, upperLeft.getY()+height-2, 4, 4);

		}
		
	}
	//Vjezbe 7
	@Override
	public void moveTo(int x, int y) {

		upperLeft.moveTo(x, y);
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		
		upperLeft.moveBy(byX, byY);
	}
	
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle)obj).area());
		}
		return 0;
	}
	
	
	
}
