package geometry;

import java.awt.Color;

//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

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
	//Konstruktori 
	
	public Point () {
		
	}
	//poziva se u pozadini
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

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pomocna = (Point) obj;
			if (this.x == pomocna.getX() && this.x == pomocna.getY()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	//provjera da li je instanca neke klase
	
	public String toString () {
		return "(" + x + "," + y + ")"; 
	}
	
	public boolean contains (int x, int y)
	{
		return this.distance(x, y) <= 2;
		
		/*if (this.distance(x, y)<= 2)
		{
			return true;
		}
		else 
		{
			return false;
		}
		*/
	}
	
	
	//Metode pristupa 
	//GET i SET metode
	//za x promjenljivu get metoda, uvijek su public
	public int getX() {
		return x;
		//vraca vrijednost promjenljive x
	}
	//SET je uvijek void i ima ulazni parametar
	public void setX(int x) {
		this.x=x;
		//x=x2;
	}
	
	public int getY() {
		return this.y; // moze return y i be this
	}
	public void setY(int y)
	{
		this.y=y;
	}

	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine (x-2,y, x+2, y);  //horizontalna linija
		g.drawLine(x, y-2, x, y+2); //vertikalna linija 
		
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
	public int compareTo(Object obj) {
		
		if (obj instanceof Point) {
			return (int) (this.distance(0, 0) - ((Point) obj).distance(0, 0));
		}
		return 0;
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

