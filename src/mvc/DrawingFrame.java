package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class DrawingFrame extends JFrame {

	public DrawingView view = new DrawingView();
	public DrawingController controller;

	private ButtonGroup btnsOperation = new ButtonGroup();
	private ButtonGroup btnsShapes = new ButtonGroup();

	private JToggleButton tglBtnDrawing = new JToggleButton("Drawing");
	private JToggleButton tglBtnModifyOrDelete = new JToggleButton("Select");

	private JButton tglBtnModify = new JButton("Modify");
	private JButton tglBtnDelete = new JButton("Delete");

	private JToggleButton tglBtnPoint = new JToggleButton("Point");
	private JToggleButton tglBtnLine = new JToggleButton("Line");
	private JToggleButton tglBtnRectangle = new JToggleButton("Rectangle");
	private JToggleButton tglBtnCircle = new JToggleButton("Circle");
	private JToggleButton tglBtnDonut = new JToggleButton("Donut");

	private JToggleButton tglBtnHexagon = new JToggleButton("Hexagon");

	private JToggleButton tglBtnInsideColor = new JToggleButton("Fill Color");
	private JToggleButton tglBtnOutsideColor = new JToggleButton("Edge Color");

	private JToggleButton tglBtnUndo = new JToggleButton("Undo");
	private JToggleButton tglBtnRedo = new JToggleButton("Redo");
	private JToggleButton tglBtnLoadNext = new JToggleButton("Load Next");

	private JMenuItem mntmSaveLog = new JMenuItem("Save Log");
	private JMenuItem mntmSaveDrawing = new JMenuItem("Save Drawing");
	private JMenuItem mntmLoadDrawing = new JMenuItem("Load Drawing");
	private JMenuItem mntmLoadLog = new JMenuItem("Load Log");

	private JToggleButton tglBtnToFront = new JToggleButton("To Front");
	private JToggleButton tglBtnToBack = new JToggleButton("To Back");
	private JToggleButton tglBtnBringToFront = new JToggleButton("Bring To Front");
	private JToggleButton tglBtnBringToBack = new JToggleButton("Bring To Back");

	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea textArea = new JTextArea();
	private JLabel lblLog = new JLabel("Command Log");

	private JPanel contentPane;

	private final Color backgroundColor = new Color(238, 232, 170);
	private final Color panelColor = new Color(250, 248, 232);
	private final Color borderColor = new Color(189, 183, 107);
	private final Color textColor = new Color(139, 69, 19);

	public DrawingFrame() {

		setFont(new Font("Segoe UI", Font.BOLD, 13));
		setBackground(backgroundColor);
		setTitle("Drawing App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1000, 700));

		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/* FILE MENU
		 */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(panelColor);
		menuBar.setBorder(new MatteBorder(0, 0, 1, 0, borderColor));
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnFile.setForeground(textColor);
		menuBar.add(mnFile);

		styleMenuItem(mntmSaveLog);
		styleMenuItem(mntmSaveDrawing);
		styleMenuItem(mntmLoadDrawing);
		styleMenuItem(mntmLoadLog);

		mnFile.add(mntmSaveLog);
		mnFile.add(mntmSaveDrawing);
		mnFile.addSeparator();
		mnFile.add(mntmLoadDrawing);
		mnFile.add(mntmLoadLog);

		/* DRAWING VIEW
		 */
		contentPane.add(view, BorderLayout.CENTER);

		/* LEFT PANEL
		 */
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBorder(new CompoundBorder(null, new MatteBorder(0, 0, 0, 2, borderColor)));

		pnlMenu.setPreferredSize(new Dimension(150, 10));
		pnlMenu.setBackground(backgroundColor);

		contentPane.add(pnlMenu, BorderLayout.WEST);

		GridBagLayout gbl_pnlMenu = new GridBagLayout();
		gbl_pnlMenu.columnWidths = new int[] {150, 0};
		gbl_pnlMenu.rowHeights = new int[] {191, 270, 0};
		gbl_pnlMenu.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_pnlMenu.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};

		pnlMenu.setLayout(gbl_pnlMenu);

		/* ACTIVE COLORS
		 */
		JPanel pnlColors = new JPanel();

		pnlColors.setBorder(new CompoundBorder(null, new MatteBorder(0, 0, 1, 0, borderColor)));

		pnlColors.setBackground(backgroundColor);

		GridBagConstraints gbc_pnlColors = new GridBagConstraints();

		gbc_pnlColors.fill = GridBagConstraints.BOTH;
		gbc_pnlColors.insets = new Insets(0, 0, 5, 0);
		gbc_pnlColors.gridx = 0;
		gbc_pnlColors.gridy = 0;

		pnlMenu.add(pnlColors, gbc_pnlColors);

		GridBagLayout gbl_pnlColors = new GridBagLayout();
		gbl_pnlColors.columnWidths = new int[] {150, 0};
		gbl_pnlColors.rowHeights = new int[] {48, 41, 41, 0};
		gbl_pnlColors.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_pnlColors.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};

		pnlColors.setLayout(gbl_pnlColors);

		JLabel lblColors = new JLabel("Active colors");
		lblColors.setHorizontalAlignment(SwingConstants.CENTER);
		lblColors.setForeground(textColor);
		lblColors.setFont(new Font("Segoe UI", Font.BOLD, 14));

		GridBagConstraints gbc_lblColors = new GridBagConstraints();

		gbc_lblColors.insets = new Insets(0, 0, 5, 0);
		gbc_lblColors.gridx = 0;
		gbc_lblColors.gridy = 0;

		pnlColors.add(lblColors, gbc_lblColors);

		styleButton(tglBtnInsideColor);

		GridBagConstraints gbc_insideColor = new GridBagConstraints();

		gbc_insideColor.insets = new Insets(0, 0, 5, 0);
		gbc_insideColor.gridx = 0;
		gbc_insideColor.gridy = 1;

		pnlColors.add(tglBtnInsideColor, gbc_insideColor);

		styleButton(tglBtnOutsideColor);

		GridBagConstraints gbc_outsideColor = new GridBagConstraints();

		gbc_outsideColor.gridx = 0;
		gbc_outsideColor.gridy = 2;

		pnlColors.add(tglBtnOutsideColor, gbc_outsideColor);

		/* SHAPES
		 */
		JPanel pnlShapes = new JPanel();
		pnlShapes.setBackground(backgroundColor);

		GridBagConstraints gbc_pnlShapes = new GridBagConstraints();

		gbc_pnlShapes.fill = GridBagConstraints.BOTH;
		gbc_pnlShapes.gridx = 0;
		gbc_pnlShapes.gridy = 1;

		pnlMenu.add(pnlShapes, gbc_pnlShapes);

		GridBagLayout gbl_pnlShapes = new GridBagLayout();

		gbl_pnlShapes.columnWidths = new int[] {150, 0};
		gbl_pnlShapes.rowHeights = new int[] {27, 31, 31, 31, 31, 31, 31, 0};

		gbl_pnlShapes.columnWeights = new double[] {0.0, Double.MIN_VALUE};

		gbl_pnlShapes.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};

		pnlShapes.setLayout(gbl_pnlShapes);

		JLabel lblShapes = new JLabel("Shapes");
		lblShapes.setHorizontalAlignment(SwingConstants.CENTER);
		lblShapes.setForeground(textColor);
		lblShapes.setFont(new Font("Segoe UI", Font.BOLD, 14));

		GridBagConstraints gbc_lblShapes = new GridBagConstraints();

		gbc_lblShapes.insets = new Insets(0, 0, 5, 0);
		gbc_lblShapes.gridx = 0;
		gbc_lblShapes.gridy = 0;

		pnlShapes.add(lblShapes, gbc_lblShapes);

		styleButton(tglBtnPoint);
		btnsShapes.add(tglBtnPoint);

		GridBagConstraints gbc_point = new GridBagConstraints();

		gbc_point.insets = new Insets(0, 0, 5, 0);
		gbc_point.gridx = 0;
		gbc_point.gridy = 1;

		pnlShapes.add(tglBtnPoint, gbc_point);

		styleButton(tglBtnLine);
		btnsShapes.add(tglBtnLine);

		GridBagConstraints gbc_line = new GridBagConstraints();

		gbc_line.insets = new Insets(0, 0, 5, 0);
		gbc_line.gridx = 0;
		gbc_line.gridy = 2;

		pnlShapes.add(tglBtnLine, gbc_line);

		styleButton(tglBtnRectangle);
		btnsShapes.add(tglBtnRectangle);

		GridBagConstraints gbc_rectangle = new GridBagConstraints();

		gbc_rectangle.insets = new Insets(0, 0, 5, 0);
		gbc_rectangle.gridx = 0;
		gbc_rectangle.gridy = 3;

		pnlShapes.add(tglBtnRectangle, gbc_rectangle);

		styleButton(tglBtnCircle);
		btnsShapes.add(tglBtnCircle);

		GridBagConstraints gbc_circle = new GridBagConstraints();

		gbc_circle.insets = new Insets(0, 0, 5, 0);
		gbc_circle.gridx = 0;
		gbc_circle.gridy = 4;

		pnlShapes.add(tglBtnCircle, gbc_circle);

		styleButton(tglBtnDonut);
		btnsShapes.add(tglBtnDonut);

		GridBagConstraints gbc_donut = new GridBagConstraints();

		gbc_donut.insets = new Insets(0, 0, 5, 0);
		gbc_donut.gridx = 0;
		gbc_donut.gridy = 5;

		pnlShapes.add(tglBtnDonut, gbc_donut);

		styleButton(tglBtnHexagon);
		btnsShapes.add(tglBtnHexagon);

		GridBagConstraints gbc_hexagon = new GridBagConstraints();

		gbc_hexagon.gridx = 0;
		gbc_hexagon.gridy = 6;

		pnlShapes.add(tglBtnHexagon, gbc_hexagon);

		/* TOP PANEL
		 */
		JPanel panelNorth = new JPanel();

		panelNorth.setBorder(new CompoundBorder(null, new MatteBorder(0, 0, 1, 0, borderColor)));

		panelNorth.setPreferredSize(new Dimension(50, 50));
		panelNorth.setBackground(backgroundColor);

		contentPane.add(panelNorth, BorderLayout.NORTH);

		GridBagLayout gbl_panelNorth = new GridBagLayout();

		gbl_panelNorth.columnWidths = new int[] {579, 24, 523, 0};

		gbl_panelNorth.rowHeights = new int[] {50, 0};

		gbl_panelNorth.columnWeights = new double[] {1.0, 0.0, 1.0, Double.MIN_VALUE};

		gbl_panelNorth.rowWeights = new double[] {1.0, Double.MIN_VALUE};

		panelNorth.setLayout(gbl_panelNorth);

		/* TOP LEFT COMMANDS
		 */
		JPanel pnlMainCommands = new JPanel();
		pnlMainCommands.setBackground(backgroundColor);

		GridBagConstraints gbc_mainCommands = new GridBagConstraints();

		gbc_mainCommands.insets = new Insets(0, 0, 0, 5);
		gbc_mainCommands.gridx = 0;
		gbc_mainCommands.gridy = 0;

		panelNorth.add(pnlMainCommands, gbc_mainCommands);

		GridBagLayout gbl_mainCommands = new GridBagLayout();

		gbl_mainCommands.columnWidths = new int[] {160, 144, 144, 144, 0};

		gbl_mainCommands.rowHeights = new int[] {50, 0};

		gbl_mainCommands.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};

		gbl_mainCommands.rowWeights = new double[] {1.0, Double.MIN_VALUE};

		pnlMainCommands.setLayout(gbl_mainCommands);

		styleButton(tglBtnDrawing);

		tglBtnDrawing.addActionListener(e -> controller.setOperationDrawing());

		btnsOperation.add(tglBtnDrawing);
		tglBtnDrawing.setSelected(true);

		GridBagConstraints gbc_drawing = new GridBagConstraints();

		gbc_drawing.insets = new Insets(0, 0, 0, 5);
		gbc_drawing.gridx = 0;
		gbc_drawing.gridy = 0;

		pnlMainCommands.add(tglBtnDrawing, gbc_drawing);

		styleButton(tglBtnModifyOrDelete);

		tglBtnModifyOrDelete.addActionListener(e -> controller.setOperationEditDelete());

		btnsOperation.add(tglBtnModifyOrDelete);

		GridBagConstraints gbc_select = new GridBagConstraints();

		gbc_select.insets = new Insets(0, 0, 0, 5);
		gbc_select.gridx = 1;
		gbc_select.gridy = 0;

		pnlMainCommands.add(tglBtnModifyOrDelete, gbc_select);

		styleButton(tglBtnModify);

		GridBagConstraints gbc_modify = new GridBagConstraints();

		gbc_modify.insets = new Insets(0, 0, 0, 5);
		gbc_modify.gridx = 2;
		gbc_modify.gridy = 0;

		pnlMainCommands.add(tglBtnModify, gbc_modify);

		styleButton(tglBtnDelete);

		GridBagConstraints gbc_delete = new GridBagConstraints();

		gbc_delete.gridx = 3;
		gbc_delete.gridy = 0;

		pnlMainCommands.add(tglBtnDelete, gbc_delete);

		/* TOP RIGHT COMMANDS
		 */
		JPanel pnlHistory = new JPanel();

		pnlHistory.setBorder(new CompoundBorder(null, new MatteBorder(0, 1, 0, 0, borderColor)));

		pnlHistory.setBackground(backgroundColor);

		GridBagConstraints gbc_history = new GridBagConstraints();

		gbc_history.gridx = 2;
		gbc_history.gridy = 0;

		panelNorth.add(pnlHistory, gbc_history);

		GridBagLayout gbl_history = new GridBagLayout();

		gbl_history.columnWidths = new int[] {27, 154, 108, 45, 138, 0};

		gbl_history.rowHeights = new int[] {50, 0};

		gbl_history.columnWeights = new double[] {0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};

		gbl_history.rowWeights = new double[] {1.0, Double.MIN_VALUE};

		pnlHistory.setLayout(gbl_history);

		styleButton(tglBtnUndo);

		GridBagConstraints gbc_undo = new GridBagConstraints();

		gbc_undo.insets = new Insets(0, 0, 0, 5);
		gbc_undo.gridx = 1;
		gbc_undo.gridy = 0;

		pnlHistory.add(tglBtnUndo, gbc_undo);

		styleButton(tglBtnRedo);

		GridBagConstraints gbc_redo = new GridBagConstraints();

		gbc_redo.insets = new Insets(0, 0, 0, 5);
		gbc_redo.gridx = 2;
		gbc_redo.gridy = 0;

		pnlHistory.add(tglBtnRedo, gbc_redo);

		styleButton(tglBtnLoadNext);

		GridBagConstraints gbc_loadNext = new GridBagConstraints();

		gbc_loadNext.gridx = 4;
		gbc_loadNext.gridy = 0;

		pnlHistory.add(tglBtnLoadNext, gbc_loadNext);

		/* RIGHT PANEL - Z ORDER
		 */
		JPanel pnlRight = new JPanel();

		pnlRight.setPreferredSize(new Dimension(150, 10));
		pnlRight.setMinimumSize(new Dimension(150, 10));

		pnlRight.setBorder(new CompoundBorder(null, new MatteBorder(0, 1, 0, 0, borderColor)));

		pnlRight.setBackground(backgroundColor);

		contentPane.add(pnlRight, BorderLayout.EAST);

		GridBagLayout gbl_right = new GridBagLayout();

		gbl_right.columnWidths = new int[] {149, 0};

		gbl_right.rowHeights = new int[] {80, 40, 40, 40, 40, 0};

		gbl_right.columnWeights = new double[] {0.0, Double.MIN_VALUE};

		gbl_right.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};

		pnlRight.setLayout(gbl_right);

		JLabel lblZOrder = new JLabel("Z order");

		lblZOrder.setForeground(textColor);
		lblZOrder.setFont(new Font("Segoe UI", Font.BOLD, 14));

		GridBagConstraints gbc_zLabel = new GridBagConstraints();

		gbc_zLabel.gridx = 0;
		gbc_zLabel.gridy = 0;

		pnlRight.add(lblZOrder, gbc_zLabel);

		styleButton(tglBtnToFront);

		GridBagConstraints gbc_toFront = new GridBagConstraints();

		gbc_toFront.insets = new Insets(0, 0, 5, 0);
		gbc_toFront.gridx = 0;
		gbc_toFront.gridy = 1;

		pnlRight.add(tglBtnToFront, gbc_toFront);

		styleButton(tglBtnToBack);

		GridBagConstraints gbc_toBack = new GridBagConstraints();

		gbc_toBack.insets = new Insets(0, 0, 5, 0);
		gbc_toBack.gridx = 0;
		gbc_toBack.gridy = 2;

		pnlRight.add(tglBtnToBack, gbc_toBack);

		styleButton(tglBtnBringToFront);

		GridBagConstraints gbc_bringToFront = new GridBagConstraints();

		gbc_bringToFront.insets = new Insets(0, 0, 5, 0);
		gbc_bringToFront.gridx = 0;
		gbc_bringToFront.gridy = 3;

		pnlRight.add(tglBtnBringToFront, gbc_bringToFront);

		styleButton(tglBtnBringToBack);

		GridBagConstraints gbc_bringToBack = new GridBagConstraints();

		gbc_bringToBack.gridx = 0;
		gbc_bringToBack.gridy = 4;

		pnlRight.add(tglBtnBringToBack, gbc_bringToBack);

		/* LOG
		 */
		scrollPane.setPreferredSize(new Dimension(1085, 155));

		scrollPane.setBorder(new MatteBorder(1, 0, 0, 0, borderColor));

		contentPane.add(scrollPane, BorderLayout.SOUTH);

		JPanel pnlLog = new JPanel();
		pnlLog.setBackground(Color.WHITE);
		pnlLog.setLayout(new BorderLayout());

		scrollPane.setViewportView(pnlLog);

		lblLog.setHorizontalAlignment(SwingConstants.CENTER);

		lblLog.setFont(new Font("Segoe UI", Font.BOLD, 13));

		lblLog.setForeground(textColor);
		lblLog.setBackground(panelColor);
		lblLog.setOpaque(true);

		lblLog.setPreferredSize(new Dimension(1085, 30));

		pnlLog.add(lblLog, BorderLayout.NORTH);

		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

		textArea.setForeground(new Color(70, 70, 70));
		textArea.setBackground(new Color(255, 255, 252));
		textArea.setMargin(new Insets(8, 10, 8, 10));
		textArea.setEditable(false);

		pnlLog.add(textArea, BorderLayout.CENTER);

		/* TOOLTIPS
		 */
		tglBtnDrawing.setToolTipText("Draw a new shape");

		tglBtnModifyOrDelete.setToolTipText("Select one or more shapes");

		tglBtnModify.setToolTipText("Modify the selected shape");

		tglBtnDelete.setToolTipText("Delete selected shapes");

		tglBtnPoint.setToolTipText("Draw a point");
		tglBtnLine.setToolTipText("Draw a line");
		tglBtnRectangle.setToolTipText("Draw a rectangle");
		tglBtnCircle.setToolTipText("Draw a circle");
		tglBtnDonut.setToolTipText("Draw a donut");
		tglBtnHexagon.setToolTipText("Draw a hexagon");

		tglBtnInsideColor.setToolTipText("Choose active fill color");

		tglBtnOutsideColor.setToolTipText("Choose active edge color");

		tglBtnUndo.setToolTipText("Undo the last command");

		tglBtnRedo.setToolTipText("Redo the last undone command");

		tglBtnLoadNext.setToolTipText("Execute the next command from the loaded log");

		tglBtnToFront.setToolTipText("Move selected shape one position forward");

		tglBtnToBack.setToolTipText("Move selected shape one position backward");

		tglBtnBringToFront.setToolTipText("Move selected shape to the front");

		tglBtnBringToBack.setToolTipText("Move selected shape to the back");

		/* INITIAL STATE
		 */
		tglBtnModify.setEnabled(false);
		tglBtnDelete.setEnabled(false);

		tglBtnUndo.setEnabled(false);
		tglBtnRedo.setEnabled(false);
		tglBtnLoadNext.setEnabled(false);

		tglBtnToFront.setEnabled(false);
		tglBtnToBack.setEnabled(false);
		tglBtnBringToFront.setEnabled(false);
		tglBtnBringToBack.setEnabled(false);
	}

	private void styleButton(Component component) {

		if (component instanceof javax.swing.AbstractButton) {

			javax.swing.AbstractButton button = (javax.swing.AbstractButton) component;

			button.setBorder(new LineBorder(borderColor, 1, true));

			button.setMinimumSize(new Dimension(125, 32));

			button.setPreferredSize(new Dimension(125, 32));

			button.setBackground(Color.WHITE);
			button.setForeground(textColor);
			button.setFocusPainted(false);

			button.setFont(new Font("Segoe UI", Font.BOLD, 13));

			button.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
	}

	private void styleMenuItem(JMenuItem menuItem) {

		menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		menuItem.setForeground(textColor);
		menuItem.setBackground(panelColor);
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(
			DrawingController controller) {
		this.controller = controller;
	}

	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public JToggleButton getTglBtnDrawing() {
		return tglBtnDrawing;
	}

	public JToggleButton getTglBtnModifyOrDelete() {
		return tglBtnModifyOrDelete;
	}

	public JButton getTglBtnModify() {
		return tglBtnModify;
	}

	public JButton getTglBtnDelete() {
		return tglBtnDelete;
	}

	public JToggleButton getTglBtnPoint() {
		return tglBtnPoint;
	}

	public JToggleButton getTglBtnLine() {
		return tglBtnLine;
	}

	public JToggleButton getTglBtnRectangle() {
		return tglBtnRectangle;
	}

	public JToggleButton getTglBtnCircle() {
		return tglBtnCircle;
	}

	public JToggleButton getTglBtnDonut() {
		return tglBtnDonut;
	}

	public JToggleButton getTglBtnHexagon() {
		return tglBtnHexagon;
	}

	public JToggleButton getTglBtnInsideColor() {
		return tglBtnInsideColor;
	}

	public JToggleButton getTglBtnOutsideColor() {
		return tglBtnOutsideColor;
	}

	public JToggleButton getTglBtnUndo() {
		return tglBtnUndo;
	}

	public JToggleButton getTglBtnRedo() {
		return tglBtnRedo;
	}

	public JToggleButton getTglBtnLoadNext() {
		return tglBtnLoadNext;
	}

	public JToggleButton getTglBtnToFront() {
		return tglBtnToFront;
	}

	public JToggleButton getTglBtnToBack() {
		return tglBtnToBack;
	}

	public JToggleButton getTglBtnBringToFront() {
		return tglBtnBringToFront;
	}

	public JToggleButton getTglBtnBringToBack() {
		return tglBtnBringToBack;
	}

	public JMenuItem getMntmSaveLog() {
		return mntmSaveLog;
	}

	public JMenuItem getMntmSaveDrawing() {
		return mntmSaveDrawing;
	}

	public JMenuItem getMntmLoadDrawing() {
		return mntmLoadDrawing;
	}

	public JMenuItem getMntmLoadLog() {
		return mntmLoadLog;
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}