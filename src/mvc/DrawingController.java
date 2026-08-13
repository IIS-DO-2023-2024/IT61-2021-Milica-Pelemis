package mvc;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dialogs.DlgCircle;
import dialogs.DlgDonut;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;

import geometry.Line;
import geometry.Point;
import geometry.Shape;

public class DrawingController {

	private final DrawingModel model;
	private final DrawingFrame frame;

	private final int OPERATION_DRAWING = 1;
	private final int OPERATION_EDIT_DELETE = 0;

	private int activeOperation = OPERATION_DRAWING;

	private Color edgeColor = Color.BLACK;
	private Color innerColor = Color.WHITE;

	boolean lineWaitingForEndPoint = false;
	private Point startPoint;

	public DrawingController(DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;

		this.frame.getView().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClick(e.getX(), e.getY());
			}
		});
	}


	private void onClick(int x, int y) {

		if (activeOperation == OPERATION_EDIT_DELETE) {

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
			return;
		}

		Point mouseClick = new Point(x, y);

		if (frame.getTglBtnPoint().isSelected()) {

			DlgPoint dlgPoint = new DlgPoint();

			dlgPoint.setPoint(mouseClick);
			dlgPoint.setColors(edgeColor);
			dlgPoint.setVisible(true);

			if (dlgPoint.getPoint() != null) {
				model.add(dlgPoint.getPoint());
				frame.getView().repaint();
			}

			return;
		}

		else if (frame.getTglBtnLine().isSelected()) {

			if (lineWaitingForEndPoint) {

				DlgLine dlgLine = new DlgLine();

				Line line = new Line(startPoint, mouseClick);

				dlgLine.setLine(line);
				dlgLine.setColor(edgeColor);
				dlgLine.setVisible(true);

				if (dlgLine.getLine() != null) {
					model.add(dlgLine.getLine());
					frame.getView().repaint();
				}

				lineWaitingForEndPoint = false;
				return;
			}

			startPoint = mouseClick;
			lineWaitingForEndPoint = true;
			return;
		}

		else if (frame.getTglBtnRectangle().isSelected()) {

			DlgRectangle dlgRectangle = new DlgRectangle();

			dlgRectangle.setPoint(mouseClick);
			dlgRectangle.setColors(edgeColor, innerColor);
			dlgRectangle.setVisible(true);

			if (dlgRectangle.getRectangle() != null) {
				model.add(dlgRectangle.getRectangle());
				frame.getView().repaint();
			}

			return;
		}

		else if (frame.getTglBtnCircle().isSelected()) {

			DlgCircle dlgCircle = new DlgCircle();

			dlgCircle.setPoint(mouseClick);
			dlgCircle.setColor(edgeColor);
			dlgCircle.setInnerConor(innerColor);
			dlgCircle.setVisible(true);

			if (dlgCircle.getCircle() != null) {
				model.add(dlgCircle.getCircle());
				frame.getView().repaint();
			}

			return;
		}

		else if (frame.getTglBtnDonut().isSelected()) {

			DlgDonut dlgDonut = new DlgDonut();

			dlgDonut.setPoint(mouseClick);
			dlgDonut.setColors(edgeColor, innerColor);
			dlgDonut.setVisible(true);

			if (dlgDonut.getDonut() != null) {
				model.add(dlgDonut.getDonut());
				frame.getView().repaint();
			}

			return;
		}
	}


	public void setOperationDrawing() {

		activeOperation = OPERATION_DRAWING;

		for (Shape shape : model.getShapes()) {
			shape.setSelected(false);
		}

		frame.getTglBtnModify().setEnabled(false);
		frame.getTglBtnDelete().setEnabled(false);

		frame.getTglBtnPoint().setEnabled(true);
		frame.getTglBtnLine().setEnabled(true);
		frame.getTglBtnRectangle().setEnabled(true);
		frame.getTglBtnCircle().setEnabled(true);
		frame.getTglBtnDonut().setEnabled(true);

		frame.getView().repaint();
	}


	public void setOperationEditDelete() {

		activeOperation = OPERATION_EDIT_DELETE;

		frame.getTglBtnModify().setEnabled(false);
		frame.getTglBtnDelete().setEnabled(false);

		frame.getTglBtnPoint().setEnabled(false);
		frame.getTglBtnLine().setEnabled(false);
		frame.getTglBtnRectangle().setEnabled(false);
		frame.getTglBtnCircle().setEnabled(false);
		frame.getTglBtnDonut().setEnabled(false);
	}
}