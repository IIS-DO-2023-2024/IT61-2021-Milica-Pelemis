package mvc;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class DrawingFrame extends JFrame{
	
	public DrawingView view = new DrawingView();
	public DrawingController controller;
	
	public DrawingFrame() {
		view.addMouseListener(new MouseAdapter() {
		});
		
		getContentPane().add(view, BorderLayout.CENTER);
	}
	
	public DrawingController getController() {
		return controller;
	}
	
	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	public DrawingView getView() {
		return view;
	}
	
	public void setView(DrawingView view) {
		this.view = view;
	}

}
