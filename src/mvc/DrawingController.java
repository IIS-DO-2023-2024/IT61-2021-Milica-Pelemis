package mvc;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import dialogs.DlgCircle;
import dialogs.DlgDonut;
import dialogs.DlgHexagon;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import adapter.HexagonAdapter;

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

import strategy.DrawingSaveStrategy;
import strategy.LogSaveStrategy;
import strategy.SaveContext;
import command.ShapeLogFormatter;
import command.ShapeLogParser;

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

	private List<String> loadedLogLines = new ArrayList<String>();
	private int loadedLogIndex = 0;

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

		frame.getMntmSaveLog().addActionListener(e -> saveLog());

		frame.getMntmSaveDrawing().addActionListener(e -> saveDrawing());

		frame.getMntmLoadDrawing().addActionListener(e -> loadDrawing());

		frame.getMntmLoadLog().addActionListener(e -> loadLog());

		frame.getTglBtnLoadNext().addActionListener(e -> loadNextCommand());

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

		else if (frame.getTglBtnHexagon().isSelected()) {

			DlgHexagon dlgHexagon = new DlgHexagon();

			dlgHexagon.setPoint(mouseClick);
			dlgHexagon.setColors(edgeColor, innerColor);
			dlgHexagon.setVisible(true);

			if (dlgHexagon.getHexagon() != null) {

				AddShapeCommand addShapeCommand = new AddShapeCommand(
						model,
						dlgHexagon.getHexagon());

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

		frame.getTglBtnInsideColor().setForeground(
				getTextColorForBackground(innerColor));

		frame.getTglBtnOutsideColor().setForeground(
				getTextColorForBackground(edgeColor));
	}


	private Color getTextColorForBackground(Color backgroundColor) {

		int brightness =
				(backgroundColor.getRed() * 299
				+ backgroundColor.getGreen() * 587
				+ backgroundColor.getBlue() * 114)
				/ 1000;

		if (brightness < 140) {
			return Color.WHITE;
		}

		return new Color(139, 69, 19);
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

		else if (shape instanceof HexagonAdapter) {

			HexagonAdapter oldHexagon =
					(HexagonAdapter) shape.clone();

			DlgHexagon dlgHexagon = new DlgHexagon();

			dlgHexagon.setHexagon((HexagonAdapter) shape);
			dlgHexagon.setVisible(true);

			if (dlgHexagon.getHexagon() != null) {

				dlgHexagon.getHexagon().setSelected(true);

				ModifyShapeCommand modifyShapeCommand =
						new ModifyShapeCommand(
								model,
								oldHexagon,
								dlgHexagon.getHexagon(),
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


	private void saveLog() {

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Save command log");
		fileChooser.setFileFilter(
				new FileNameExtensionFilter(
						"Text files (*.txt)",
						"txt"));

		if (fileChooser.showSaveDialog(frame)
				== JFileChooser.APPROVE_OPTION) {

			File file = addExtension(
					fileChooser.getSelectedFile(),
					".txt");

			SaveContext saveContext = new SaveContext();

			saveContext.setStrategy(
					new LogSaveStrategy(
							frame.getTextArea().getText()));

			try {

				saveContext.save(file);

				JOptionPane.showMessageDialog(
						frame,
						"Command log saved successfully.",
						"Save Log",
						JOptionPane.INFORMATION_MESSAGE);
			}
			catch (IOException e) {

				JOptionPane.showMessageDialog(
						frame,
						"Error while saving command log.",
						"Save Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	private void saveDrawing() {

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Save drawing");
		fileChooser.setFileFilter(
				new FileNameExtensionFilter(
						"Serialized drawing (*.ser)",
						"ser"));

		if (fileChooser.showSaveDialog(frame)
				== JFileChooser.APPROVE_OPTION) {

			File file = addExtension(
					fileChooser.getSelectedFile(),
					".ser");

			SaveContext saveContext = new SaveContext();

			saveContext.setStrategy(
					new DrawingSaveStrategy(model));

			try {

				saveContext.save(file);

				JOptionPane.showMessageDialog(
						frame,
						"Drawing saved successfully.",
						"Save Drawing",
						JOptionPane.INFORMATION_MESSAGE);
			}
			catch (IOException e) {

				JOptionPane.showMessageDialog(
						frame,
						"Error while saving drawing.",
						"Save Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	private void loadDrawing() {

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Load drawing");
		fileChooser.setFileFilter(
				new FileNameExtensionFilter(
						"Serialized drawing (*.ser)",
						"ser"));

		if (fileChooser.showOpenDialog(frame)
				== JFileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();

			try {

				ObjectInputStream input =
						new ObjectInputStream(
								new FileInputStream(file));

				Object loadedObject = input.readObject();

				input.close();

				if (!(loadedObject instanceof List<?>)) {

					JOptionPane.showMessageDialog(
							frame,
							"Selected file does not contain a valid drawing.",
							"Load Error",
							JOptionPane.ERROR_MESSAGE);

					return;
				}

				List<?> loadedList = (List<?>) loadedObject;
				List<Shape> loadedShapes = new ArrayList<Shape>();

				for (Object object : loadedList) {

					if (!(object instanceof Shape)) {

						JOptionPane.showMessageDialog(
								frame,
								"Selected file does not contain a valid drawing.",
								"Load Error",
								JOptionPane.ERROR_MESSAGE);

						return;
					}

					Shape shape = (Shape) object;
					shape.setSelected(false);
					loadedShapes.add(shape);
				}

				model.getShapes().clear();
				model.getShapes().addAll(loadedShapes);

				commandManager.clear();

				frame.getTextArea().setText("");

				updateSelectionState();
				updateUndoButton();
				updateRedoButton();

				frame.getView().repaint();

				JOptionPane.showMessageDialog(
						frame,
						"Drawing loaded successfully.",
						"Load Drawing",
						JOptionPane.INFORMATION_MESSAGE);
			}
			catch (IOException | ClassNotFoundException e) {

				JOptionPane.showMessageDialog(
						frame,
						"Error while loading drawing.",
						"Load Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	private void loadLog() {

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setDialogTitle("Load command log");
		fileChooser.setFileFilter(
				new FileNameExtensionFilter(
						"Text files (*.txt)",
						"txt"));

		if (fileChooser.showOpenDialog(frame)
				== JFileChooser.APPROVE_OPTION) {

			File file = fileChooser.getSelectedFile();

			List<String> lines = new ArrayList<String>();

			try {

				BufferedReader reader =
						new BufferedReader(
								new FileReader(file));

				String line;

				while ((line = reader.readLine()) != null) {

					if (!line.trim().isEmpty()) {
						lines.add(line.trim());
					}
				}

				reader.close();
			}
			catch (IOException e) {

				JOptionPane.showMessageDialog(
						frame,
						"Error while loading command log.",
						"Load Error",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			if (lines.isEmpty()) {

				JOptionPane.showMessageDialog(
						frame,
						"Selected command log is empty.",
						"Load Log",
						JOptionPane.INFORMATION_MESSAGE);

				return;
			}

			loadedLogLines.clear();
			loadedLogLines.addAll(lines);
			loadedLogIndex = 0;

			model.getShapes().clear();
			commandManager.clear();

			frame.getTextArea().setText("");
			frame.getTglBtnLoadNext().setEnabled(true);
			frame.getTglBtnLoadNext().setSelected(false);

			lineWaitingForEndPoint = false;

			updateSelectionState();
			updateUndoButton();
			updateRedoButton();

			frame.getView().repaint();

			JOptionPane.showMessageDialog(
					frame,
					"Command log loaded. Use Load Next to execute commands step by step.",
					"Load Log",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}


	private void loadNextCommand() {

		frame.getTglBtnLoadNext().setSelected(false);

		if (loadedLogIndex >= loadedLogLines.size()) {

			frame.getTglBtnLoadNext().setEnabled(false);
			return;
		}

		String logLine = loadedLogLines.get(loadedLogIndex);

		try {

			executeLogLine(logLine);

			addToLog(logLine);

			loadedLogIndex++;

			updateSelectionState();
			updateUndoButton();
			updateRedoButton();

			frame.getView().repaint();

			if (loadedLogIndex >= loadedLogLines.size()) {

				frame.getTglBtnLoadNext().setEnabled(false);
			}
		}
		catch (Exception e) {

			frame.getTglBtnLoadNext().setEnabled(false);

			JOptionPane.showMessageDialog(
					frame,
					"Error in log line "
							+ (loadedLogIndex + 1)
							+ ":\n"
							+ logLine,
					"Load Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	private void executeLogLine(String logLine) {

		if (logLine.startsWith("ADD ")) {

			Shape shape =
					ShapeLogParser.parseShape(
							logLine.substring("ADD ".length()));

			commandManager.executeCommand(
					new AddShapeCommand(
							model,
							shape));

			return;
		}

		if (logLine.startsWith("MODIFY ")) {

			int oldMarker = logLine.indexOf(" old=[");
			int newMarker = logLine.indexOf("] new=[");

			if (oldMarker == -1 || newMarker == -1) {
				throw new IllegalArgumentException();
			}

			int index = Integer.parseInt(
					logLine.substring(
							"MODIFY index=".length(),
							oldMarker));

			String oldShapeText =
					logLine.substring(
							oldMarker + " old=[".length(),
							newMarker);

			String newShapeText =
					logLine.substring(
							newMarker + "] new=[".length(),
							logLine.length() - 1);

			Shape oldShape =
					ShapeLogParser.parseShape(oldShapeText);

			Shape newShape =
					ShapeLogParser.parseShape(newShapeText);

			commandManager.executeCommand(
					new ModifyShapeCommand(
							model,
							oldShape,
							newShape,
							index));

			return;
		}

		if (logLine.startsWith("DELETE ")) {

			String deleteText =
					logLine.substring("DELETE ".length());

			String[] deletedParts =
					deleteText.split(" \\| ");

			List<Shape> deletedShapes =
					new ArrayList<Shape>();

			List<Integer> deletedIndexes =
					new ArrayList<Integer>();

			for (String part : deletedParts) {

				int shapeMarker = part.indexOf(" shape=[");

				if (shapeMarker == -1
						|| !part.endsWith("]")) {

					throw new IllegalArgumentException();
				}

				int index = Integer.parseInt(
						part.substring(
								"index=".length(),
								shapeMarker));

				String shapeText =
						part.substring(
								shapeMarker
								+ " shape=[".length(),
								part.length() - 1);

				deletedIndexes.add(index);
				deletedShapes.add(
						ShapeLogParser.parseShape(
								shapeText));
			}

			commandManager.executeCommand(
					new DeleteShapeCommand(
							model,
							deletedShapes,
							deletedIndexes));

			return;
		}

		if (logLine.startsWith("SELECT index=")) {

			int index =
					getLogIndex(
							logLine,
							"SELECT index=");

			model.get(index).setSelected(true);
			return;
		}

		if (logLine.startsWith("DESELECT index=")) {

			int index =
					getLogIndex(
							logLine,
							"DESELECT index=");

			model.get(index).setSelected(false);
			return;
		}

		if (logLine.startsWith("TO_FRONT ")) {

			int oldIndex =
					getLogValue(
							logLine,
							"oldIndex=");

			commandManager.executeCommand(
					new ToFrontCommand(
							model,
							oldIndex));

			return;
		}

		if (logLine.startsWith("TO_BACK ")) {

			int oldIndex =
					getLogValue(
							logLine,
							"oldIndex=");

			commandManager.executeCommand(
					new ToBackCommand(
							model,
							oldIndex));

			return;
		}

		if (logLine.startsWith("BRING_TO_FRONT ")) {

			int oldIndex =
					getLogValue(
							logLine,
							"oldIndex=");

			commandManager.executeCommand(
					new BringToFrontCommand(
							model,
							oldIndex));

			return;
		}

		if (logLine.startsWith("BRING_TO_BACK ")) {

			int oldIndex =
					getLogValue(
							logLine,
							"oldIndex=");

			commandManager.executeCommand(
					new BringToBackCommand(
							model,
							oldIndex));

			return;
		}

		if (logLine.startsWith("UNDO ")) {

			if (!commandManager.canUndo()) {
				throw new IllegalStateException();
			}

			commandManager.undo();
			return;
		}

		if (logLine.startsWith("REDO ")) {

			if (!commandManager.canRedo()) {
				throw new IllegalStateException();
			}

			commandManager.redo();
			return;
		}

		throw new IllegalArgumentException();
	}


	private int getLogIndex(
			String logLine,
			String prefix) {

		int shapeMarker = logLine.indexOf(" shape=[");

		if (shapeMarker == -1) {
			throw new IllegalArgumentException();
		}

		return Integer.parseInt(
				logLine.substring(
						prefix.length(),
						shapeMarker));
	}


	private int getLogValue(
			String logLine,
			String key) {

		int start = logLine.indexOf(key);

		if (start == -1) {
			throw new IllegalArgumentException();
		}

		start += key.length();

		int end = logLine.indexOf(" ", start);

		if (end == -1) {
			end = logLine.length();
		}

		return Integer.parseInt(
				logLine.substring(start, end));
	}


	private File addExtension(
			File file,
			String extension) {

		if (!file.getName()
				.toLowerCase()
				.endsWith(extension)) {

			return new File(
					file.getAbsolutePath()
					+ extension);
		}

		return file;
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
		frame.getTglBtnHexagon().setEnabled(true);

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
		frame.getTglBtnHexagon().setEnabled(false);
	}
}