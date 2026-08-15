package mvc;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JColorChooser;

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

import command.AddShapeCommand;
import command.Command;
import command.CommandManager;
import command.ModifyShapeCommand;
import command.DeleteShapeCommand;
import command.ToFrontCommand;
import command.ToBackCommand;
import command.BringToFrontCommand;
import command.BringToBackCommand;
import command.ShapeLogFormatter;

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

	private CommandManager commandManager = new CommandManager();

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

		frame.getTglBtnUndo().addActionListener(e -> undo());

		frame.getTglBtnRedo().addActionListener(e -> redo());

		frame.getTglBtnToFront().addActionListener(e -> moveToFront());

		frame.getTglBtnToBack().addActionListener(e -> moveToBack());

		frame.getTglBtnBringToFront().addActionListener(e -> bringToFront());

		frame.getTglBtnBringToBack().addActionListener(e -> bringToBack());
		
		frame.getTglBtnInsideColor().addActionListener(e -> chooseInnerColor());

		frame.getTglBtnOutsideColor().addActionListener(e -> chooseEdgeColor());

		this.frame.getView().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onClick(e.getX(), e.getY());
			}
		});

		updateUndoButton();
		updateRedoButton();
		updateColorButtons();
	}


	private void onClick(int x, int y) {

		if (activeOperation == OPERATION_EDIT_DELETE) {

			List<Shape> rev = new ArrayList<>(model.getShapes());
			Collections.reverse(rev);

			for (Shape s : rev) {
				if (s.contains(x, y)) {

					int index = getShapeIndex(s);

					s.setSelected(!s.isSelected());

					if (s.isSelected()) {
						addToLog(
								"SELECT index=" + index
								+ " shape=["
								+ ShapeLogFormatter.format(s)
								+ "]");
					}
					else {
						addToLog(
								"DESELECT index=" + index
								+ " shape=["
								+ ShapeLogFormatter.format(s)
								+ "]");
					}

					updateSelectionState();

					frame.getView().repaint();
					return;
				}
			}

			for (int i = 0; i < model.size(); i++) {

				Shape shape = model.get(i);

				if (shape.isSelected()) {

					shape.setSelected(false);

					addToLog(
							"DESELECT index=" + i
							+ " shape=["
							+ ShapeLogFormatter.format(shape)
							+ "]");
				}
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

				AddShapeCommand addShapeCommand = new AddShapeCommand(
						model,
						dlgPoint.getPoint());

				commandManager.executeCommand(addShapeCommand);
				addToLog(addShapeCommand.toString());

				updateUndoButton();
				updateRedoButton();

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

					AddShapeCommand addShapeCommand = new AddShapeCommand(
							model,
							dlgLine.getLine());

					commandManager.executeCommand(addShapeCommand);
					addToLog(addShapeCommand.toString());

					updateUndoButton();
					updateRedoButton();

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

				AddShapeCommand addShapeCommand = new AddShapeCommand(
						model,
						dlgRectangle.getRectangle());

				commandManager.executeCommand(addShapeCommand);
				addToLog(addShapeCommand.toString());

				updateUndoButton();
				updateRedoButton();

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

				AddShapeCommand addShapeCommand = new AddShapeCommand(
						model,
						dlgCircle.getCircle());

				commandManager.executeCommand(addShapeCommand);
				addToLog(addShapeCommand.toString());

				updateUndoButton();
				updateRedoButton();

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

				AddShapeCommand addShapeCommand = new AddShapeCommand(
						model,
						dlgDonut.getDonut());

				commandManager.executeCommand(addShapeCommand);
				addToLog(addShapeCommand.toString());

				updateUndoButton();
				updateRedoButton();

				frame.getView().repaint();
			}

			return;
		}
	}


	private void addToLog(String text) {

		frame.getTextArea().append(text + System.lineSeparator());
		frame.getTextArea().setCaretPosition(
				frame.getTextArea().getDocument().getLength());
	}


	private void updateSelectionState() {

		int selectedCount = 0;

		for (Shape shape : model.getShapes()) {

			if (shape.isSelected()) {
				selectedCount++;
			}
		}

		selectionSubject.setSelectedCount(selectedCount);

		if (selectedCount == 1) {

			int selectedIndex = getSelectedShapeIndex();

			frame.getTglBtnToFront().setEnabled(
					selectedIndex < model.size() - 1);

			frame.getTglBtnToBack().setEnabled(
					selectedIndex > 0);

			frame.getTglBtnBringToFront().setEnabled(
					selectedIndex < model.size() - 1);

			frame.getTglBtnBringToBack().setEnabled(
					selectedIndex > 0);
		}
		else {

			frame.getTglBtnToFront().setEnabled(false);
			frame.getTglBtnToBack().setEnabled(false);
			frame.getTglBtnBringToFront().setEnabled(false);
			frame.getTglBtnBringToBack().setEnabled(false);
		}
	}


	private void updateUndoButton() {

		frame.getTglBtnUndo().setEnabled(
				commandManager.canUndo());
	}


	private void updateRedoButton() {

		frame.getTglBtnRedo().setEnabled(
				commandManager.canRedo());
	}


	private void undo() {

		Command command = commandManager.getLastUndoCommand();

		if (command == null) {
			return;
		}

		commandManager.undo();

		addToLog("UNDO " + command.toString());

		updateSelectionState();

		updateUndoButton();
		updateRedoButton();

		frame.getTglBtnUndo().setSelected(false);

		frame.getView().repaint();
	}


	private void redo() {

		Command command = commandManager.getLastRedoCommand();

		if (command == null) {
			return;
		}

		commandManager.redo();

		addToLog("REDO " + command.toString());

		updateSelectionState();

		updateUndoButton();
		updateRedoButton();

		frame.getTglBtnRedo().setSelected(false);

		frame.getView().repaint();
	}


	private void moveToFront() {

		int index = getSelectedShapeIndex();

		if (index == -1 || index == model.size() - 1) {

			frame.getTglBtnToFront().setSelected(false);
			return;
		}

		ToFrontCommand toFrontCommand =
				new ToFrontCommand(
						model,
						index);

		commandManager.executeCommand(toFrontCommand);
		addToLog(toFrontCommand.toString());

		updateSelectionState();

		updateUndoButton();
		updateRedoButton();

		frame.getTglBtnToFront().setSelected(false);

		frame.getView().repaint();
	}


	private void moveToBack() {

		int index = getSelectedShapeIndex();

		if (index == -1 || index == 0) {

			frame.getTglBtnToBack().setSelected(false);
			return;
		}

		ToBackCommand toBackCommand =
				new ToBackCommand(
						model,
						index);

		commandManager.executeCommand(toBackCommand);
		addToLog(toBackCommand.toString());

		updateSelectionState();

		updateUndoButton();
		updateRedoButton();

		frame.getTglBtnToBack().setSelected(false);

		frame.getView().repaint();
	}


	private void bringToFront() {

		int index = getSelectedShapeIndex();

		if (index == -1 || index == model.size() - 1) {

			frame.getTglBtnBringToFront().setSelected(false);
			return;
		}

		BringToFrontCommand bringToFrontCommand =
				new BringToFrontCommand(
						model,
						index);

		commandManager.executeCommand(bringToFrontCommand);
		addToLog(bringToFrontCommand.toString());

		updateSelectionState();

		updateUndoButton();
		updateRedoButton();

		frame.getTglBtnBringToFront().setSelected(false);

		frame.getView().repaint();
	}


	private void bringToBack() {

		int index = getSelectedShapeIndex();

		if (index == -1 || index == 0) {

			frame.getTglBtnBringToBack().setSelected(false);
			return;
		}

		BringToBackCommand bringToBackCommand =
				new BringToBackCommand(
						model,
						index);

		commandManager.executeCommand(bringToBackCommand);
		addToLog(bringToBackCommand.toString());

		updateSelectionState();

		updateUndoButton();
		updateRedoButton();

		frame.getTglBtnBringToBack().setSelected(false);

		frame.getView().repaint();
	}
	
	private void chooseInnerColor() {

		Color selectedColor = JColorChooser.showDialog(
				frame,
				"Choose inside color",
				innerColor);

		if (selectedColor != null) {
			innerColor = selectedColor;
			updateColorButtons();
		}

		frame.getTglBtnInsideColor().setSelected(false);
	}


	private void chooseEdgeColor() {

		Color selectedColor = JColorChooser.showDialog(
				frame,
				"Choose outside color",
				edgeColor);

		if (selectedColor != null) {
			edgeColor = selectedColor;
			updateColorButtons();
		}

		frame.getTglBtnOutsideColor().setSelected(false);
	}


	private void updateColorButtons() {

		frame.getTglBtnInsideColor().setBackground(innerColor);
		frame.getTglBtnOutsideColor().setBackground(edgeColor);
	}


	private int getSelectedShapeIndex() {

		for (int i = 0; i < model.size(); i++) {

			if (model.get(i).isSelected()) {
				return i;
			}
		}

		return -1;
	}


	private int getShapeIndex(Shape targetShape) {

		for (int i = 0; i < model.size(); i++) {

			if (model.get(i) == targetShape) {
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

			Point oldPoint = (Point) shape.clone();

			DlgPoint dlgPoint = new DlgPoint();

			dlgPoint.setPoint((Point) shape);
			dlgPoint.setVisible(true);

			if (dlgPoint.getPoint() != null) {

				dlgPoint.getPoint().setSelected(true);

				ModifyShapeCommand modifyShapeCommand =
						new ModifyShapeCommand(
								model,
								oldPoint,
								dlgPoint.getPoint(),
								index);

				commandManager.executeCommand(modifyShapeCommand);
				addToLog(modifyShapeCommand.toString());

				updateSelectionState();

				updateUndoButton();
				updateRedoButton();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Line) {

			Line oldLine = (Line) shape.clone();

			DlgLine dlgLine = new DlgLine();

			dlgLine.setLine((Line) shape);
			dlgLine.setVisible(true);

			if (dlgLine.getLine() != null) {

				dlgLine.getLine().setSelected(true);

				ModifyShapeCommand modifyShapeCommand =
						new ModifyShapeCommand(
								model,
								oldLine,
								dlgLine.getLine(),
								index);

				commandManager.executeCommand(modifyShapeCommand);
				addToLog(modifyShapeCommand.toString());

				updateSelectionState();

				updateUndoButton();
				updateRedoButton();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Rectangle) {

			Rectangle oldRectangle = (Rectangle) shape.clone();

			DlgRectangle dlgRectangle = new DlgRectangle();

			dlgRectangle.setRectangle((Rectangle) shape);
			dlgRectangle.setVisible(true);

			if (dlgRectangle.getRectangle() != null) {

				dlgRectangle.getRectangle().setSelected(true);

				ModifyShapeCommand modifyShapeCommand =
						new ModifyShapeCommand(
								model,
								oldRectangle,
								dlgRectangle.getRectangle(),
								index);

				commandManager.executeCommand(modifyShapeCommand);
				addToLog(modifyShapeCommand.toString());

				updateSelectionState();

				updateUndoButton();
				updateRedoButton();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Donut) {

			Donut oldDonut = (Donut) shape.clone();

			DlgDonut dlgDonut = new DlgDonut();

			dlgDonut.setDonut((Donut) shape);
			dlgDonut.setVisible(true);

			if (dlgDonut.getDonut() != null) {

				dlgDonut.getDonut().setSelected(true);

				ModifyShapeCommand modifyShapeCommand =
						new ModifyShapeCommand(
								model,
								oldDonut,
								dlgDonut.getDonut(),
								index);

				commandManager.executeCommand(modifyShapeCommand);
				addToLog(modifyShapeCommand.toString());

				updateSelectionState();

				updateUndoButton();
				updateRedoButton();

				frame.getView().repaint();
			}
		}

		else if (shape instanceof Circle) {

			Circle oldCircle = (Circle) shape.clone();

			DlgCircle dlgCircle = new DlgCircle();

			dlgCircle.setCircle((Circle) shape);
			dlgCircle.setVisible(true);

			if (dlgCircle.getCircle() != null) {

				dlgCircle.getCircle().setSelected(true);

				ModifyShapeCommand modifyShapeCommand =
						new ModifyShapeCommand(
								model,
								oldCircle,
								dlgCircle.getCircle(),
								index);

				commandManager.executeCommand(modifyShapeCommand);
				addToLog(modifyShapeCommand.toString());

				updateSelectionState();

				updateUndoButton();
				updateRedoButton();

				frame.getView().repaint();
			}
		}
	}


	private void deleteSelectedShapes() {

		List<Shape> selectedShapes = new ArrayList<Shape>();
		List<Integer> selectedIndexes = new ArrayList<Integer>();

		for (int i = 0; i < model.size(); i++) {

			if (model.get(i).isSelected()) {

				selectedShapes.add(model.get(i));
				selectedIndexes.add(i);
			}
		}

		if (selectedShapes.isEmpty()) {
			return;
		}

		int option = JOptionPane.showConfirmDialog(
				frame,
				"Do you really want to delete selected shape(s)?",
				"Delete",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {

			DeleteShapeCommand deleteShapeCommand =
					new DeleteShapeCommand(
							model,
							selectedShapes,
							selectedIndexes);

			commandManager.executeCommand(deleteShapeCommand);
			addToLog(deleteShapeCommand.toString());

			updateSelectionState();

			updateUndoButton();
			updateRedoButton();

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

		frame.getTglBtnToFront().setEnabled(false);
		frame.getTglBtnToBack().setEnabled(false);
		frame.getTglBtnBringToFront().setEnabled(false);
		frame.getTglBtnBringToBack().setEnabled(false);

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

		frame.getTglBtnToFront().setEnabled(false);
		frame.getTglBtnToBack().setEnabled(false);
		frame.getTglBtnBringToFront().setEnabled(false);
		frame.getTglBtnBringToBack().setEnabled(false);

		frame.getTglBtnPoint().setEnabled(false);
		frame.getTglBtnLine().setEnabled(false);
		frame.getTglBtnRectangle().setEnabled(false);
		frame.getTglBtnCircle().setEnabled(false);
		frame.getTglBtnDonut().setEnabled(false);
	}
}