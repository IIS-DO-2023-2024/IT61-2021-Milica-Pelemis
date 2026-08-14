package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	
	private Point startPoint;
	private Point endPoint;
	
	//Konstruktori
	
	public Line () {
		
	}
	public Line (Point startPoint, Point endPoint) {
		this.startPoint=startPoint;
		this.endPoint=endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, Color color) {
		this(startPoint, endPoint);
		setColor(color);
	}
	
	public Line (Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}
	public boolean equals (Object obj) {
		if (obj instanceof Line) {
			
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint))
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return startPoint + "-->" + endPoint;
	}
	
	public boolean contains (int x, int y)
	{
		return startPoint.distance(x, y) + endPoint.distance(x, y) - length() <=2;
	}
	
	
	//Metoda za izracunavanje duzine linije
	public double length () {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	//Metode pristupa
	public Point getStartPoint () {
		return startPoint;
	}
	public void setStartPoint (Point startPoint) {
		this.startPoint = startPoint;
		//metoda klase i parametar metode se nazivaju isto pa zato treba this
	}

	public Point getEndPoint () {
		return endPoint;
	}
	public void setEndPoint (Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());

		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-2, startPoint.getY()-2, 4, 4);
			g.drawRect(endPoint.getX()-2, endPoint.getY()-2, 4, 4);

		}
		
	}
	
	@Override
	public void moveTo(int x, int y) {
	//nece se nista desiti kada pozovemo 	
		
	}
	
	@Override
	public void moveBy(int byX, int byY) {

		startPoint.moveBy(byX, byY);
		endPoint.moveBy(byX, byY);
	}
	
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Line) {
			return (int) (this.length() - ((Line) obj).length());
		}
		return 0;
	}
	
	@Override
	public Shape clone() {
		// TODO Auto-generated method stub
		
		Point clonedStartPoint = (Point) this.startPoint.clone();
		Point clonedEndPoint = (Point) this.endPoint.clone();

		Line clonedLine = new Line(
				clonedStartPoint,
				clonedEndPoint,
				this.isSelected(),
				this.getColor());

		return clonedLine;
	}
	
}
