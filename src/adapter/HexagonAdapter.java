package adapter;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	private Hexagon hexagon;
	private Color innerColor;

	public HexagonAdapter() {

		hexagon = new Hexagon(0, 0, 0);

		setColor(Color.BLACK);
		setInnerColor(Color.WHITE);
		setSelected(false);
	}

	public HexagonAdapter(Point center, int radius) {

		this();

		setX(center.getX());
		setY(center.getY());
		setRadius(radius);
	}

	public HexagonAdapter(
			Point center,
			int radius,
			boolean selected,
			Color color,
			Color innerColor) {

		this(center, radius);

		setSelected(selected);
		setColor(color);
		setInnerColor(innerColor);
	}

	public int getX() {
		return hexagon.getX();
	}

	public void setX(int x) {
		hexagon.setX(x);
	}

	public int getY() {
		return hexagon.getY();
	}

	public void setY(int y) {
		hexagon.setY(y);
	}

	public int getRadius() {
		return hexagon.getR();
	}

	public void setRadius(int radius) {
		hexagon.setR(radius);
	}

	public Point getCenter() {
		return new Point(getX(), getY());
	}

	public void setCenter(Point center) {

		setX(center.getX());
		setY(center.getY());
	}

	@Override
	public Color getColor() {
		return hexagon.getBorderColor();
	}

	@Override
	public void setColor(Color color) {

		super.setColor(color);
		hexagon.setBorderColor(color);
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {

		this.innerColor = innerColor;
		hexagon.setAreaColor(innerColor);
	}

	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}

	@Override
	public void setSelected(boolean selected) {

		super.setSelected(selected);
		hexagon.setSelected(selected);
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
	}

	@Override
	public void moveTo(int x, int y) {

		setX(x);
		setY(y);
	}

	@Override
	public void moveBy(int byX, int byY) {

		setX(getX() + byX);
		setY(getY() + byY);
	}

	@Override
	public Shape clone() {

		return new HexagonAdapter(new Point(getX(), getY()), getRadius(), isSelected(), getColor(), getInnerColor());
	}

	@Override
	public String toString() {

		return "Center point =(" + getX() + "," + getY() + ")" + " Radius =" + getRadius();
	}
}