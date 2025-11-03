package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel{

	public static void main (String [] args) {
		
		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		Drawing drawing = new Drawing();
		frame.getContentPane().add(drawing);
		frame.setVisible(true);
		
		
	}
	
	public void paint (Graphics g) {
		Point p1 = new Point (50,50);
		p1.draw(g);
		g.setColor(Color.RED);
		Line l1 = new Line (new Point (100, 100), new Point (200, 200));
		l1.draw(g);
		g.setColor(Color.GREEN);
		l1.getStartPoint().draw(g);
		
		Circle c1 = new Circle (new Point (500,100), 80);
		c1.draw(g);
		c1.getCenter().draw(g);
		
		g.setColor(Color.BLACK);
		
		Rectangle r1 = new Rectangle (l1.getEndPoint(), 100, 50);
		r1.draw(g);
		
		Donut d1 = new Donut (new Point (800,100), 50, 25);
		d1.draw(g);
		
		Rectangle k1 = new Rectangle (new Point (500, 500), 50, 50);
		k1.draw (g);
		
		//Zadatak 1 - liste
		ArrayList <Shape> shapes = new ArrayList <Shape> ();
		shapes.add(p1);
		shapes.add(l1);
		shapes.add(c1);
		shapes.add(r1);
		shapes.add(d1);
		
		shapes.get(0).moveBy(10, 0);
		
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext())
		{
			it.next().moveBy(10, 0);
		}
		
		//Zadatak 2 - liste
		/*shapes.get(3).draw(g);
		shapes.get(shapes.size()-1).draw(g);
		shapes.remove(1);
		shapes.get(1).draw(g);
		shapes.get(3).draw(g);
		shapes.add(3,l1);
		
		System.out.println(shapes.isEmpty());
		*/
		
		p1.setSelected(true);
		p1.draw(g);
		it = shapes.iterator();
		
		l1.setSelected(true);
		c1.setSelected(true);
		r1.setSelected(true);
		d1.setSelected(true);

		while (it.hasNext())
		{
			it.next().draw(g);;
		}
		
		
		
		
		
		
	}
}

