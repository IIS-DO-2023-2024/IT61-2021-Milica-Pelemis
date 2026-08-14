package mvc;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import dialogs.DlgCircle;
import dialogs.DlgDonut;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import observer.SelectionButtonsObserver;
import observer.SelectionSubject;

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

	private SelectionSubject selectionSubject = new SelectionSubject();

	public DrawingController(DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;

		selectionSubject.addObserver(
				new SelectionButtonsObserver(
						frame.getTglBtnModify(),
						frame.getTglBtnDelete()));

		frame.getTglBtnModify().addActionListener(e -> modifySelectedShape());

		frame.getTglBtnDelete().addActionListener(e -> deleteSelectedShapes());

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

					updateSelectionState();

					frame.getView().repaint();
					return;
				}
			}

			for (Shape s : model.getShapes()) {
				s.setSelected(false);
			}

			updateSelectionState();

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


	private void updateSelectionState() {

		int selectedCount = 0;

		for (Shape shape : model.getShapes()) {

			if (shape.isSelected()) {
				selectedCount++;
			}
		}

		selectionSubject.setSelectedCount(selectedCount);
	}


	private int getSelectedShapeIndex() {

		for (int i = 0; i < model.size(); i++) {

			if (model.get(i).isSelected()) {
				return i;
			}
		}

		return -1;
	}


	private void modifySelectedShape() {

		int index = getSelectedShapeIndex();

		if (index == -1) {
			return;
		}

		Shape shape = model.get(index);

		if (shape instanceof Point) {

			DlgPoint dlgPoint = new DlgPoint();

			dlgPoint.setPoint((Point) shape);
			dlgPoint.setVisible(true);

			if (dlgPoint.getPoint() != null) {

				dlgPoint.getPoint().setSelected(true);

				model.set(index, dlgPoint.getPoint());

				updateSelectionState();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Line) {

			DlgLine dlgLine = new DlgLine();

			dlgLine.setLine((Line) shape);
			dlgLine.setVisible(true);

			if (dlgLine.getLine() != null) {

				dlgLine.getLine().setSelected(true);

				model.set(index, dlgLine.getLine());

				updateSelectionState();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Rectangle) {

			DlgRectangle dlgRectangle = new DlgRectangle();

			dlgRectangle.setRectangle((Rectangle) shape);
			dlgRectangle.setVisible(true);

			if (dlgRectangle.getRectangle() != null) {

				dlgRectangle.getRectangle().setSelected(true);

				model.set(index, dlgRectangle.getRectangle());

				updateSelectionState();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Donut) {

			DlgDonut dlgDonut = new DlgDonut();

			dlgDonut.setDonut((Donut) shape);
			dlgDonut.setVisible(true);

			if (dlgDonut.getDonut() != null) {

				dlgDonut.getDonut().setSelected(true);

				model.set(index, dlgDonut.getDonut());

				updateSelectionState();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Circle) {

			DlgCircle dlgCircle = new DlgCircle();

			dlgCircle.setCircle((Circle) shape);
			dlgCircle.setVisible(true);

			if (dlgCircle.getCircle() != null) {

				dlgCircle.getCircle().setSelected(true);

				model.set(index, dlgCircle.getCircle());

				updateSelectionState();

				frame.getView().repaint();
			}
		}
	}


	private void deleteSelectedShapes() {

		boolean hasSelectedShapes = false;

		for (Shape shape : model.getShapes()) {

			if (shape.isSelected()) {
				hasSelectedShapes = true;
				break;
			}
		}

		if (!hasSelectedShapes) {
			return;
		}

		int option = JOptionPane.showConfirmDialog(
				frame,
				"Do you really want to delete selected shape(s)?",
				"Delete",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {

			for (int i = model.size() - 1; i >= 0; i--) {

				if (model.get(i).isSelected()) {
					model.remove(i);
				}
			}

			updateSelectionState();

			frame.getView().repaint();
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