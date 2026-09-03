package command;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import adapter.HexagonAdapter;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class ShapeLogParser {

	public static Shape parseShape(String text) {

		String[] parts = text.trim().split("\\s+");

		if (parts.length == 0) {
			throw new IllegalArgumentException();
		}

		String type = parts[0];

		Map<String, String> values = new HashMap<String, String>();

		for (int i = 1; i < parts.length; i++) {

			int separator = parts[i].indexOf("=");

			if (separator == -1) {
				continue;
			}

			String key = parts[i].substring(0, separator);

			String value = parts[i].substring(separator + 1);

			values.put(key, value);
		}

		if ("POINT".equals(type)) {

			return new Point(getInt(values, "x"), getInt(values, "y"), getBoolean(values, "selected"), getColor(values, "color"));
		}

		if ("LINE".equals(type)) {

			return new Line(new Point(getInt(values, "x1"),  getInt(values, "y1")), new Point(getInt(values, "x2"), getInt(values, "y2")), getBoolean(values, "selected"), getColor(values, "color"));
		}

		if ("RECTANGLE".equals(type)) {

			return new Rectangle(new Point(getInt(values, "x"), getInt(values, "y")), getInt(values, "width"), getInt(values, "height"), getBoolean(values, "selected"), getColor(values, "edgeColor"), getColor(values, "innerColor"));
		}

		if ("DONUT".equals(type)) {

			return new Donut(new Point(getInt(values, "x"), getInt(values, "y")), getInt(values, "radius"), getInt(values, "innerRadius"), getBoolean(values, "selected"), getColor(values, "edgeColor"), getColor(values, "innerColor"));
		}

		if ("HEXAGON".equals(type)) {

			return new HexagonAdapter(new Point(getInt(values, "x"), getInt(values, "y")), getInt(values, "radius"), getBoolean(values, "selected"), getColor(values, "edgeColor"), getColor(values, "innerColor"));
		}

		if ("CIRCLE".equals(type)) {

			return new Circle(new Point(getInt(values, "x"), getInt(values, "y")), getInt(values, "radius"), getBoolean(values, "selected"), getColor(values, "edgeColor"), getColor(values, "innerColor"));
		}

		throw new IllegalArgumentException();
	}


	private static int getInt(Map<String, String> values, String key) {

		String value = values.get(key);

		if (value == null) {
			throw new IllegalArgumentException();
		}

		return Integer.parseInt(value);
	}


	private static boolean getBoolean(Map<String, String> values, String key) {

		String value = values.get(key);

		if (value == null) {
			throw new IllegalArgumentException();
		}

		return Boolean.parseBoolean(value);
	}


	private static Color getColor(Map<String, String> values, String key) {

		return new Color(getInt(values, key),
				true);
	}
}