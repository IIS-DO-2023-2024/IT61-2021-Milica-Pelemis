package mvc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import geometry.Shape;

public class DrawingController {

	private final DrawingModel model;
	private final DrawingFrame frame;
	
	public DrawingController(DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;
		
		this.frame.getView().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { 
            	onClick(e.getX(), e.getY()); 
            	}
        });
	}
	
	
	private void onClick(int x, int y) {
	       List<Shape> rev = new ArrayList<>(model.getShapes());
	       Collections.reverse(rev);
	       for (Shape s : rev) {
	           if (s.contains(x, y)) {
	               s.setSelected(!s.isSelected()); 
	               frame.getView().repaint();
	               return;
	           }
	       }
	       for (Shape s : model.getShapes()) {
	       	s.setSelected(false);
        }
	        frame.getView().repaint();
	   }
}

