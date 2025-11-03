 package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShapes{

	protected Point center;
	protected int radius;
	
	//Konstruktori
	
	public Circle() {
		
	}
	public Circle (Point center, int radius) {
		this.center=center;
		this.radius=radius;
	}
	public Circle (Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected (selected);
	}
	
	public Circle (Point center, int radius, boolean selected, Color color)
	{
		this(center, radius, selected);
		setColor(color);
	}
	public Circle (Point center, int radius, boolean selected, Color color, Color innerColor)
	{
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}
	
	public boolean equals (Object obj) {
		if (obj instanceof Circle)
		{
			Circle pomocna = (Circle) obj;
			if (pomocna.getCenter().equals(center) && pomocna.getRadius() ==radius)
			{
				return true;
			}
			else {
				return false;
			}
	}
			return false;
	}	
	public String toString () {
		return "Center point =" + center + "radius =" + radius;
	}
		
		
		
	public boolean contains (int x, int y) {
		return center.distance(x, y) <= radius;
	}
	public boolean contains (Point p) {
		return center.distance(p.getX(), p.getY()) <= radius;
	}
	
	
	
	//Obim kruga
	public double circumference() {
		return 2*radius*Math.PI;
	}
	//Povrsina kruga
	public double area() {
		return radius*radius*Math.PI;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter (Point center) {
		this.center = center;
	}
	
	public int getRadius () {
		return radius;
	}
	
	public void setRadius (int radius) throws Exception{
		if (radius<0) {
			throw new Exception ("Radijus ne moze biti manji od 0");
		}
		this.radius = radius;
	}

	public void fill(Graphics g)
	{
		g.setColor(getInnerColor());
		g.fillOval(center.getX()+1 - radius ,center.getY()+1 - radius, (radius-1)*2, (radius-1)*2);
	}
	
	public void draw(Graphics g) {
		
		g.setColor(getColor());
		g.drawOval(center.getX() - radius, center.getY() - radius, radius*2, radius*2);
		fill(g);
		
		if (isSelected())
		{
			g.setColor(Color.BLUE);
			g.drawRect(center.getX()-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()-radius-2, 4, 4);
			g.drawRect(center.getX()-2, center.getY()+radius-2, 4, 4);
			g.drawRect(center.getX()+radius-2, center.getY()-2, 4, 4);
			g.drawRect(center.getX()-radius-2, center.getY()-2, 4, 4);

		}
		
	}
	
	
	@Override
	public void moveTo(int x, int y) {

		center.moveTo(x, y);
	}
	
	@Override
	public void moveBy(int byX, int byY) {

		center.moveBy(byX, byY);
	}
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Circle) {
			return (int) (this.area() - ((Circle)obj).area());
		}
		return 0;
	}
	
	
	
}
