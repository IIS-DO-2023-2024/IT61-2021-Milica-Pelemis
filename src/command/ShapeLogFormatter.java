package command;

import java.awt.Color;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class ShapeLogFormatter {

	public static String format(Shape shape) {

		if (shape instanceof Point) {

			Point point = (Point) shape;

			return "POINT"
					+ " x=" + point.getX()
					+ " y=" + point.getY()
					+ " color=" + colorToInt(point.getColor())
					+ " selected=" + point.isSelected();
		}

		else if (shape instanceof Line) {

			Line line = (Line) shape;

			return "LINE"
					+ " x1=" + line.getStartPoint().getX()
					+ " y1=" + line.getStartPoint().getY()
					+ " x2=" + line.getEndPoint().getX()
					+ " y2=" + line.getEndPoint().getY()
					+ " color=" + colorToInt(line.getColor())
					+ " selected=" + line.isSelected();
		}

		else if (shape instanceof Rectangle) {

			Rectangle rectangle = (Rectangle) shape;

			return "RECTANGLE"
					+ " x=" + rectangle.getUpperLeft().getX()
					+ " y=" + rectangle.getUpperLeft().getY()
					+ " width=" + rectangle.getwidth()
					+ " height=" + rectangle.getHeight()
					+ " edgeColor=" + colorToInt(rectangle.getColor())
					+ " innerColor=" + colorToInt(rectangle.getInnerColor())
					+ " selected=" + rectangle.isSelected();
		}

		else if (shape instanceof Donut) {

			Donut donut = (Donut) shape;

			return "DONUT"
					+ " x=" + donut.getCenter().getX()
					+ " y=" + donut.getCenter().getY()
					+ " radius=" + donut.getRadius()
					+ " innerRadius=" + donut.getInnerRadius()
					+ " edgeColor=" + colorToInt(donut.getColor())
					+ " innerColor=" + colorToInt(donut.getInnerColor())
					+ " selected=" + donut.isSelected();
		}

		else if (shape instanceof HexagonAdapter) {

			HexagonAdapter hexagon = (HexagonAdapter) shape;

			return "HEXAGON"
					+ " x=" + hexagon.getX()
					+ " y=" + hexagon.getY()
					+ " radius=" + hexagon.getRadius()
					+ " edgeColor=" + colorToInt(hexagon.getColor())
					+ " innerColor=" + colorToInt(hexagon.getInnerColor())
					+ " selected=" + hexagon.isSelected();
		}

		else if (shape instanceof Circle) {

			Circle circle = (Circle) shape;

			return "CIRCLE"
					+ " x=" + circle.getCenter().getX()
					+ " y=" + circle.getCenter().getY()
					+ " radius=" + circle.getRadius()
					+ " edgeColor=" + colorToInt(circle.getColor())
					+ " innerColor=" + colorToInt(circle.getInnerColor())
					+ " selected=" + circle.isSelected();
		}

		return "";
	}

	private static int colorToInt(Color color) {

		if (color == null) {
			return Color.BLACK.getRGB();
		}

		return color.getRGB();
	}
}