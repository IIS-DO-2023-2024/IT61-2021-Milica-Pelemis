package geometry;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {

		Point p= new Point ();
		p.setX(6);
		p.setY(5);
		p.setSelected(true);
		
		System.out.println("X coordinate: " + p.getX() + " Y coordinate: " + p.getY() + " Selected: " + p.isSelected());
		
		
		double result = p.distance(2, 2);
		System.out.println("Distance between points is: " + result);
		
		Line l1 = new Line();
		Point p1 = new Point();
		p1.setX(1);
		p1.setY(1);
		Circle c1 = new Circle ();
		
		//1. Inicijalizovati x koordinatu tacke p na vrijednost y koordinate tacke p1
		
		p.setX(p1.getY());
		System.out.println("Point p, x coordinate: " + p.getX());

		//2. Postaviti za pocetnu tacku linije l1 tacku p, a za krajnju tacku licije l1 tacku p1
		
		l1.setStartPoint(p);
		l1.setEndPoint(p1);
		System.out.println("Line l1 start point " + l1.getStartPoint().getX() + "," + l1.getStartPoint().getY());
		
		//3. Postaviti y koordinatu krajnje tacke l1 na 23
		
		l1.getEndPoint().setY(23);
		
		
		
		//Test konstruktora
		
		Point p2 = new Point (50, 100);
		Line l2 = new Line (p2, new Point (400, 500));
		System.out.println(p2);
		System.out.println(p1);
		System.out.println(l2);
		
		
		System.out.println(p2.equals(p1));
		
		System.out.println(p2 instanceof Point);
		System.out.println(p2 instanceof Object); //sve su instance ove klase
		
		
		System.out.println("\n \n \n Vjezbe 7");
		
		System.out.println(p1);
		p1.moveBy(5, 3);
		System.out.println(p1);
		
		System.out.println(l1);
		l1.moveTo(1, 1);
		System.out.println(l1);
		l1.moveBy(1, 1);
		System.out.println(l1);
		
		System.out.println(c1);
		c1.moveBy(1,1);
		System.out.println(c1);
		c1.moveTo(1, 1);
		System.out.println(c1);
		
		System.out.println("\n\n\n");
		
		int [ ] numbers = {5,4,3,2,1};
		System.out.println ("Nesortiran niz");
		for (int i=0; i<numbers.length; i++) {
			System.out.println(numbers[i]);
		}
		
		Arrays.sort(numbers);
		for (int i=0; i<numbers.length; i++) {
			System.out.println(numbers[i]);
		}
		
		Point p10 = new Point (10,10);
		Point p20 = new Point (20,20);
		Point p30 = new Point (30,30);
		Point p40 = new Point (140,140);
		Point p50 = new Point (5,5);
		
		Point [] points = {p10, p20, p30, p40, p50};
		
		System.out.println("Nesortiran niz tacaka");
		for (int i=0; i<points.length; i++) {
			System.out.println(points[i]);
		}
		System.out.println("Sortiran niz tacaka");
		Arrays.sort(points);
		for (int i=0; i<numbers.length; i++) {
			System.out.println(numbers[i]);
		}
		
		Circle c10 = new Circle (p10, 10);
		Circle c20 = new Circle (p20, 15);
		Circle c30 = new Circle (p30, 20);
		
		Circle [] circles = {c10, c20, c30};
		System.out.println("Nesortiran niz");
		for (int i=0; i<circles.length; i++)
		{
			System.out.println(circles[i]);
		}
		
		Arrays.sort(circles);
		System.out.println("Sortiran niz");
		for (int i =0; i<circles.length; i++)
		{
			System.out.println(circles[i]);
		}

		
		System.out.println("Circle c1 " + c1);
		try {
		    c1.setRadius(-10);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Radijus je negativna vrijednost");
		}
		System.out.println("Circle c1 " + c1);


	}

}
