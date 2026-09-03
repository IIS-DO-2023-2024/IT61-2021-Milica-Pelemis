package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape{

	private int x;
	private int y;	
	
	public double distance(int x2, int y2)
	{
		double dx=x-x2;
		double dy = y-y2;
		double d = Math.sqrt(dx*dx+ dy*dy);
		return d;
	}
	
	public Point () {
		
	}
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public Point (int x, int y, boolean selected) {
		this(x,y);
		setSelected (selected);
	}
	
	public Point (int x, int y, Color color)
	{
		this(x, y);
		setColor(color);
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		// TODO Auto-generated constructor stub
		this(x, y, selected);
		setColor(color);
	}
	
	public String toString () {
		return "(" + x + "," + y + ")"; 
	}
	
	public boolean contains (int x, int y)
	{
		return this.distance(x, y) <= 2;
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x=x;
		//x=x2;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y)
	{
		this.y=y;
	}

	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine (x-2,y, x+2, y); 
		g.drawLine(x, y-2, x, y+2); 
		
		if (isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(x-2, y-2, 4, 4);
		}
		
	}

	@Override
	public void moveTo(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	@Override
	public void moveBy(int byX, int byY) {
		
		this.x = this.x + byX;
		this.y = this.y + byY;
		
	}

	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		
		Point clonedPoint = new Point(
				this.getX(),
				this.getY(),
				this.isSelected(),
				this.getColor());

		return clonedPoint;
	}
}

