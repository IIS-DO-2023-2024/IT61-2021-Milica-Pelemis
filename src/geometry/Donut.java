package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {

	private int innerRadius;

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public Donut() {

	}

	// inicijalizovanje centra, radijusa, inner-a
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public Donut(Point center, int radius, int innerRadius,
			boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		setColor(color);
	}

	public Donut(Point center, int radius, int innerRadius,
			boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		setInnerColor(innerColor);
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	public boolean contains(int x, int y) {
		return super.contains(x, y)
				&& getCenter().distance(x, y) >= innerRadius;
	}

	public boolean contains(Point p) {
		return super.contains(p)
				&& getCenter().distance(p.getX(), p.getY()) >= innerRadius;
	}

	public String toString() {
		return "Center point =" + super.getCenter()
				+ " Radius =" + super.getRadius()
				+ " Inner radius =" + innerRadius;
	}

	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Ellipse2D outerCircle = new Ellipse2D.Double(
				getCenter().getX() - getRadius(),
				getCenter().getY() - getRadius(),
				getRadius() * 2,
				getRadius() * 2);

		Ellipse2D innerCircle = new Ellipse2D.Double(
				getCenter().getX() - innerRadius,
				getCenter().getY() - innerRadius,
				innerRadius * 2,
				innerRadius * 2);

		Area donutArea = new Area(outerCircle);

		donutArea.subtract(new Area(innerCircle));

		// boja unutrasnjosti 
		g2d.setColor(getInnerColor());
		g2d.fill(donutArea);

		// boja ivice - iscrta i spoljasnju i unutrasnju ivicu
		g2d.setColor(getColor());
		g2d.draw(donutArea);

		if (isSelected()) {

			g.setColor(Color.BLUE);

			// centar
			g.drawRect(
					getCenter().getX() - 2,
					getCenter().getY() - 2,
					4,
					4);

			// spoljasnji krug
			g.drawRect(
					getCenter().getX() - 2,
					getCenter().getY() - getRadius() - 2,
					4,
					4);

			g.drawRect(
					getCenter().getX() - 2,
					getCenter().getY() + getRadius() - 2,
					4,
					4);

			g.drawRect(
					getCenter().getX() + getRadius() - 2,
					getCenter().getY() - 2,
					4,
					4);

			g.drawRect(
					getCenter().getX() - getRadius() - 2,
					getCenter().getY() - 2,
					4,
					4);

			// unutrasnji krug
			g.drawRect(
					getCenter().getX() - innerRadius - 2,
					getCenter().getY() - 2,
					4,
					4);

			g.drawRect(
					getCenter().getX() + innerRadius - 2,
					getCenter().getY() - 2,
					4,
					4);

			g.drawRect(
					getCenter().getX() - 2,
					getCenter().getY() - innerRadius - 2,
					4,
					4);

			g.drawRect(
					getCenter().getX() - 2,
					getCenter().getY() + innerRadius - 2,
					4,
					4);
		}
	}

	public void fill(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		Ellipse2D outerCircle = new Ellipse2D.Double(
				getCenter().getX() - getRadius(),
				getCenter().getY() - getRadius(),
				getRadius() * 2,
				getRadius() * 2);

		Ellipse2D innerCircle = new Ellipse2D.Double(
				getCenter().getX() - innerRadius,
				getCenter().getY() - innerRadius,
				innerRadius * 2,
				innerRadius * 2);

		Area donutArea = new Area(outerCircle);

		donutArea.subtract(new Area(innerCircle));

		g2d.setColor(getInnerColor());
		g2d.fill(donutArea);
	}

	public int compareTo(Object obj) {

		if (obj instanceof Donut) {
			return (int) (this.area() - ((Donut) obj).area());
		}

		return 0;
	}
	
	
	@Override
	public Shape clone() {

		Point clonedCenter = (Point) this.getCenter().clone();

		Donut clonedDonut = new Donut(
				clonedCenter,
				this.getRadius(),
				this.innerRadius,
				this.isSelected(),
				this.getColor(),
				this.getInnerColor());

		return clonedDonut;
	}
}