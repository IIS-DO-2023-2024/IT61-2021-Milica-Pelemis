package drawing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FrmDrawing extends JFrame {

	private final int OPERATION_DRAWING = 1;
	private final int OPERATION_EDIT_DELETE = 0;
	
	private int activeOperation = OPERATION_DRAWING;
	
	private PnlDrawing pnlDrawing = new PnlDrawing();
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
	
	private Color edgeColor = Color.BLACK;
	private Color innerColor = Color.WHITE;
	boolean lineWaitingForEndPoint = false;
	private Point startPoint;
	
	
	private JPanel contentPane;
	private final JToggleButton tglBtnHexagon = new JToggleButton("Hexagon");
	private final JToggleButton tglBtnInsideColor = new JToggleButton("Inside Color");
	private final JToggleButton tglBtnOutsideColor = new JToggleButton("Outside Color");
	private final JPanel panelNorth = new JPanel();
	private final JToggleButton tglbtnUndo = new JToggleButton("Undo");
	private final JToggleButton tglbtnRedo = new JToggleButton("Redo");
	private final JPanel panelRight = new JPanel();
	private final JToggleButton tglbtnLoadNext = new JToggleButton("Load Next");
	private final JToggleButton tglbtnFront = new JToggleButton("Front");
	private final JToggleButton tglbtnBack = new JToggleButton("Back\r\n");
	private final JToggleButton tglbtnToFront = new JToggleButton("To Front");
	private final JToggleButton tglbtnToBack = new JToggleButton("To Back");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JTextArea textArea = new JTextArea();
	private final JLabel lblNewLabel = new JLabel("Log");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setFont(new Font("Californian FB", Font.BOLD, 14));
		setBackground(new Color(238, 232, 170));
		setTitle("Drawing App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1000, 700));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnlDrawing.addMouseListener(pnlDrawingClickListener());
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBorder(new CompoundBorder(null, new MatteBorder(0, 0, 0, 2, (Color) new Color(189, 183, 107))));
		pnlMenu.setPreferredSize(new Dimension(150, 10));
		pnlMenu.setBackground(new Color(238, 232, 170));
		contentPane.add(pnlMenu, BorderLayout.WEST);
		GridBagLayout gbl_pnlMenu = new GridBagLayout();
		gbl_pnlMenu.columnWidths = new int[]{150, 0};
		gbl_pnlMenu.rowHeights = new int[]{191, 270, 0};
		gbl_pnlMenu.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlMenu.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnlMenu.setLayout(gbl_pnlMenu);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(new java.awt.Rectangle(0, 0, 140, 0));
		panel2.setForeground(new Color(139, 69, 19));
		panel2.setBorder(new CompoundBorder(null, new MatteBorder(0, 0, 1, 0, (Color) new Color(189, 183, 107))));
		panel2.setBackground(new Color(238, 232, 170));
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 0;
		pnlMenu.add(panel2, gbc_panel2);
		GridBagLayout gbl_panel2 = new GridBagLayout();
		gbl_panel2.columnWidths = new int[]{150, 0};
		gbl_panel2.rowHeights = new int[]{48, 41, 0, 0};
		gbl_panel2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel2.setLayout(gbl_panel2);
		
		GridBagConstraints gbc_tglBtnInsideColor = new GridBagConstraints();
		gbc_tglBtnInsideColor.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnInsideColor.gridx = 0;
		gbc_tglBtnInsideColor.gridy = 1;
		tglBtnInsideColor.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnInsideColor.setMinimumSize(new Dimension(125, 30));
		tglBtnInsideColor.setActionCommand("");
		tglBtnInsideColor.setPreferredSize(new Dimension(125, 30));
		tglBtnInsideColor.setBackground(Color.WHITE);
		tglBtnInsideColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglBtnInsideColor.setForeground(new Color(139, 69, 19));
		tglBtnInsideColor.setFont(new Font("Californian FB", Font.BOLD, 14));
		panel2.add(tglBtnInsideColor, gbc_tglBtnInsideColor);
		
		GridBagConstraints gbc_tglBtnOutsideColor = new GridBagConstraints();
		gbc_tglBtnOutsideColor.gridx = 0;
		gbc_tglBtnOutsideColor.gridy = 2;
		tglBtnOutsideColor.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnOutsideColor.setMinimumSize(new Dimension(125, 30));
		tglBtnOutsideColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglBtnOutsideColor.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglBtnOutsideColor.setForeground(new Color(139, 69, 19));
		tglBtnOutsideColor.setBackground(Color.WHITE);
		tglBtnOutsideColor.setPreferredSize(new Dimension(125, 30));
		panel2.add(tglBtnOutsideColor, gbc_tglBtnOutsideColor);
		
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(238, 232, 170));
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.gridx = 0;
		gbc_panel3.gridy = 1;
		pnlMenu.add(panel3, gbc_panel3);
		GridBagLayout gbl_panel3 = new GridBagLayout();
		gbl_panel3.columnWidths = new int[]{150, 0};
		gbl_panel3.rowHeights = new int[]{27, 0, 31, 28, 27, 30, 0, 0};
		gbl_panel3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel3.setLayout(gbl_panel3);
		tglBtnPoint.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnPoint.setMinimumSize(new Dimension(125, 30));
		tglBtnPoint.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglBtnPoint.setBackground(Color.WHITE);
		tglBtnPoint.setPreferredSize(new Dimension(125, 30));
		tglBtnPoint.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglBtnPoint.setForeground(new Color(139, 69, 19));
		btnsShapes.add(tglBtnPoint);
		GridBagConstraints gbc_tglBtnPoint = new GridBagConstraints();
		gbc_tglBtnPoint.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnPoint.gridx = 0;
		gbc_tglBtnPoint.gridy = 1;
		panel3.add(tglBtnPoint, gbc_tglBtnPoint);
		tglBtnLine.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnLine.setMinimumSize(new Dimension(125, 30));
		tglBtnLine.setMaximumSize(new Dimension(59, 21));
		tglBtnLine.setBackground(Color.WHITE);
		tglBtnLine.setPreferredSize(new Dimension(125, 30));
		tglBtnLine.setForeground(new Color(139, 69, 19));
		tglBtnLine.setFont(new Font("Californian FB", Font.BOLD, 14));
		
		tglBtnLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnLine);
		GridBagConstraints gbc_tglBtnLine = new GridBagConstraints();
		gbc_tglBtnLine.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnLine.gridx = 0;
		gbc_tglBtnLine.gridy = 2;
		panel3.add(tglBtnLine, gbc_tglBtnLine);
		tglBtnRectangle.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnRectangle.setMinimumSize(new Dimension(125, 30));
		tglBtnRectangle.setBackground(Color.WHITE);
		tglBtnRectangle.setPreferredSize(new Dimension(125, 30));
		tglBtnRectangle.setForeground(new Color(160, 82, 45));
		tglBtnRectangle.setFont(new Font("Californian FB", Font.BOLD, 14));
		
		tglBtnRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnRectangle);
		GridBagConstraints gbc_tglBtnRectangle = new GridBagConstraints();
		gbc_tglBtnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnRectangle.gridx = 0;
		gbc_tglBtnRectangle.gridy = 3;
		panel3.add(tglBtnRectangle, gbc_tglBtnRectangle);
		tglBtnCircle.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnCircle.setMinimumSize(new Dimension(125, 30));
		tglBtnCircle.setBackground(Color.WHITE);
		tglBtnCircle.setPreferredSize(new Dimension(125, 30));
		tglBtnCircle.setForeground(new Color(139, 69, 19));
		tglBtnCircle.setFont(new Font("Californian FB", Font.BOLD, 14));
		
		tglBtnCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnCircle);
		GridBagConstraints gbc_tglBtnCircle = new GridBagConstraints();
		gbc_tglBtnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnCircle.gridx = 0;
		gbc_tglBtnCircle.gridy = 4;
		panel3.add(tglBtnCircle, gbc_tglBtnCircle);
		tglBtnDonut.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnDonut.setMinimumSize(new Dimension(125, 30));
		tglBtnDonut.setBackground(Color.WHITE);
		tglBtnDonut.setPreferredSize(new Dimension(125, 30));
		tglBtnDonut.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglBtnDonut.setForeground(new Color(139, 69, 19));
		
		tglBtnDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnDonut);
		GridBagConstraints gbc_tglBtnDonut = new GridBagConstraints();
		gbc_tglBtnDonut.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnDonut.gridx = 0;
		gbc_tglBtnDonut.gridy = 5;
		panel3.add(tglBtnDonut, gbc_tglBtnDonut);
		
		GridBagConstraints gbc_tglBtnHexagon = new GridBagConstraints();
		gbc_tglBtnHexagon.gridx = 0;
		gbc_tglBtnHexagon.gridy = 6;
		tglBtnHexagon.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnHexagon.setMinimumSize(new Dimension(125, 30));
		tglBtnHexagon.setPreferredSize(new Dimension(125, 30));
		tglBtnHexagon.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglBtnHexagon.setForeground(new Color(139, 69, 19));
		tglBtnHexagon.setBackground(Color.WHITE);
		tglBtnHexagon.setActionCommand("Hexagon");
		tglBtnHexagon.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel3.add(tglBtnHexagon, gbc_tglBtnHexagon);
		panelNorth.setBorder(new CompoundBorder(null, new MatteBorder(0, 0, 1, 0, (Color) new Color(189, 183, 107))));
		panelNorth.setPreferredSize(new Dimension(50, 50));
		panelNorth.setBackground(new Color(238, 232, 170));
		
		contentPane.add(panelNorth, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorth = new GridBagLayout();
		gbl_panelNorth.columnWidths = new int[]{579, 24, 523, 0};
		gbl_panelNorth.rowHeights = new int[]{50, 6, 0};
		gbl_panelNorth.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelNorth.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelNorth.setLayout(gbl_panelNorth);
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_2.setBackground(new Color(238, 232, 170));
		panelNorth.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{160, 144, 144, 144, 0};
		gbl_panel_2.rowHeights = new int[]{65, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		GridBagConstraints gbc_tglBtnDrawing = new GridBagConstraints();
		gbc_tglBtnDrawing.insets = new Insets(0, 0, 0, 5);
		gbc_tglBtnDrawing.gridx = 0;
		gbc_tglBtnDrawing.gridy = 0;
		panel_2.add(tglBtnDrawing, gbc_tglBtnDrawing);
		tglBtnDrawing.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnDrawing.setMinimumSize(new Dimension(125, 30));
		tglBtnDrawing.setBackground(Color.WHITE);
		tglBtnDrawing.setPreferredSize(new Dimension(125, 30));
		tglBtnDrawing.setForeground(new Color(139, 69, 19));
		tglBtnDrawing.setFont(new Font("Californian FB", Font.BOLD, 14));
		
		tglBtnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationDrawing();
			}
		});
		tglBtnDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(tglBtnDrawing);
		
		tglBtnDrawing.setSelected(true);
		GridBagConstraints gbc_tglBtnModifyOrDelete = new GridBagConstraints();
		gbc_tglBtnModifyOrDelete.insets = new Insets(0, 0, 0, 5);
		gbc_tglBtnModifyOrDelete.gridx = 1;
		gbc_tglBtnModifyOrDelete.gridy = 0;
		panel_2.add(tglBtnModifyOrDelete, gbc_tglBtnModifyOrDelete);
		tglBtnModifyOrDelete.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnModifyOrDelete.setMinimumSize(new Dimension(125, 30));
		tglBtnModifyOrDelete.setBackground(Color.WHITE);
		tglBtnModifyOrDelete.setPreferredSize(new Dimension(125, 30));
		tglBtnModifyOrDelete.setForeground(new Color(139, 69, 19));
		tglBtnModifyOrDelete.setFont(new Font("Californian FB", Font.BOLD, 14));
		
		tglBtnModifyOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationEditDelete();
			}
		});
		tglBtnModifyOrDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(tglBtnModifyOrDelete);
		GridBagConstraints gbc_tglBtnModify = new GridBagConstraints();
		gbc_tglBtnModify.insets = new Insets(0, 0, 0, 5);
		gbc_tglBtnModify.gridx = 2;
		gbc_tglBtnModify.gridy = 0;
		panel_2.add(tglBtnModify, gbc_tglBtnModify);
		tglBtnModify.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnModify.setMinimumSize(new Dimension(125, 30));
		tglBtnModify.setBackground(Color.WHITE);
		tglBtnModify.setPreferredSize(new Dimension(125, 30));
		tglBtnModify.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglBtnModify.setForeground(new Color(139, 69, 19));
		
		tglBtnModify.addActionListener(btnActionModifyClickListener());
		tglBtnModify.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_tglBtnDelete = new GridBagConstraints();
		gbc_tglBtnDelete.gridx = 3;
		gbc_tglBtnDelete.gridy = 0;
		panel_2.add(tglBtnDelete, gbc_tglBtnDelete);
		tglBtnDelete.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglBtnDelete.setMinimumSize(new Dimension(125, 30));
		tglBtnDelete.setBackground(Color.WHITE);
		tglBtnDelete.setPreferredSize(new Dimension(125, 30));
		tglBtnDelete.setForeground(new Color(139, 69, 19));
		tglBtnDelete.setFont(new Font("Californian FB", Font.BOLD, 14));
		
		tglBtnDelete.addActionListener(btnActionDeleteClickListener());
		tglBtnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 0;
		panel_1.setBorder(new CompoundBorder(null, new MatteBorder(0, 1, 0, 0, (Color) new Color(189, 183, 107))));
		panel_1.setPreferredSize(new Dimension(50, 10));
		panel_1.setBackground(new Color(238, 232, 170));
		panelNorth.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{27, 154, 108, 45, 138, 0};
		gbl_panel_1.rowHeights = new int[]{70, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		GridBagConstraints gbc_tglbtnUndo = new GridBagConstraints();
		gbc_tglbtnUndo.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnUndo.gridx = 1;
		gbc_tglbtnUndo.gridy = 0;
		panel_1.add(tglbtnUndo, gbc_tglbtnUndo);
		tglbtnUndo.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglbtnUndo.setMinimumSize(new Dimension(125, 30));
		tglbtnUndo.setPreferredSize(new Dimension(125, 30));
		tglbtnUndo.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglbtnUndo.setForeground(new Color(139, 69, 19));
		tglbtnUndo.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglbtnUndo.setBackground(Color.WHITE);
		GridBagConstraints gbc_tglbtnRedo = new GridBagConstraints();
		gbc_tglbtnRedo.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnRedo.gridx = 2;
		gbc_tglbtnRedo.gridy = 0;
		panel_1.add(tglbtnRedo, gbc_tglbtnRedo);
		tglbtnRedo.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglbtnRedo.setMinimumSize(new Dimension(125, 30));
		tglbtnRedo.setForeground(new Color(139, 69, 19));
		tglbtnRedo.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglbtnRedo.setBackground(Color.WHITE);
		tglbtnRedo.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglbtnRedo.setPreferredSize(new Dimension(125, 30));
		GridBagConstraints gbc_tglbtnLoadNext = new GridBagConstraints();
		gbc_tglbtnLoadNext.gridx = 4;
		gbc_tglbtnLoadNext.gridy = 0;
		panel_1.add(tglbtnLoadNext, gbc_tglbtnLoadNext);
		tglbtnLoadNext.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglbtnLoadNext.setMinimumSize(new Dimension(125, 30));
		tglbtnLoadNext.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglbtnLoadNext.setPreferredSize(new Dimension(125, 30));
		tglbtnLoadNext.setBackground(Color.WHITE);
		tglbtnLoadNext.setForeground(new Color(139, 69, 19));
		tglbtnLoadNext.setFont(new Font("Californian FB", Font.BOLD, 14));
		panelRight.setPreferredSize(new Dimension(150, 10));
		panelRight.setMinimumSize(new Dimension(150, 10));
		panelRight.setBorder(new CompoundBorder(null, new MatteBorder(0, 1, 0, 0, (Color) new Color(189, 183, 107))));
		panelRight.setBackground(new Color(238, 232, 170));
		
		contentPane.add(panelRight, BorderLayout.EAST);
		GridBagLayout gbl_panelRight = new GridBagLayout();
		gbl_panelRight.columnWidths = new int[]{149, 0};
		gbl_panelRight.rowHeights = new int[]{156, 0, 0, 0, 0, 0};
		gbl_panelRight.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelRight.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRight.setLayout(gbl_panelRight);
		
		GridBagConstraints gbc_tglbtnFront = new GridBagConstraints();
		gbc_tglbtnFront.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnFront.gridx = 0;
		gbc_tglbtnFront.gridy = 1;
		tglbtnFront.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglbtnFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		tglbtnFront.setBackground(Color.WHITE);
		tglbtnFront.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglbtnFront.setForeground(new Color(139, 69, 19));
		tglbtnFront.setMinimumSize(new Dimension(125, 30));
		tglbtnFront.setPreferredSize(new Dimension(125, 30));
		panelRight.add(tglbtnFront, gbc_tglbtnFront);
		
		GridBagConstraints gbc_tglbtnBack = new GridBagConstraints();
		gbc_tglbtnBack.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnBack.gridx = 0;
		gbc_tglbtnBack.gridy = 2;
		tglbtnBack.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglbtnBack.setPreferredSize(new Dimension(125, 30));
		tglbtnBack.setMinimumSize(new Dimension(125, 30));
		tglbtnBack.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglbtnBack.setForeground(new Color(139, 69, 19));
		tglbtnBack.setBackground(Color.WHITE);
		tglbtnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelRight.add(tglbtnBack, gbc_tglbtnBack);
		
		GridBagConstraints gbc_tglbtnToFront = new GridBagConstraints();
		gbc_tglbtnToFront.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnToFront.gridx = 0;
		gbc_tglbtnToFront.gridy = 3;
		tglbtnToFront.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglbtnToFront.setBackground(Color.WHITE);
		tglbtnToFront.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglbtnToFront.setForeground(new Color(139, 69, 19));
		tglbtnToFront.setMinimumSize(new Dimension(125, 30));
		tglbtnToFront.setPreferredSize(new Dimension(125, 30));
		tglbtnToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelRight.add(tglbtnToFront, gbc_tglbtnToFront);
		
		GridBagConstraints gbc_tglbtnToBack = new GridBagConstraints();
		gbc_tglbtnToBack.gridx = 0;
		gbc_tglbtnToBack.gridy = 4;
		tglbtnToBack.setBorder(new LineBorder(new Color(189, 183, 107)));
		tglbtnToBack.setBackground(Color.WHITE);
		tglbtnToBack.setFont(new Font("Californian FB", Font.BOLD, 14));
		tglbtnToBack.setForeground(new Color(139, 69, 19));
		tglbtnToBack.setPreferredSize(new Dimension(125, 30));
		tglbtnToBack.setMinimumSize(new Dimension(125, 30));
		tglbtnToBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelRight.add(tglbtnToBack, gbc_tglbtnToBack);
		scrollPane.setPreferredSize(new Dimension(1085, 150));
		scrollPane.setToolTipText("");
		scrollPane.setName("Log");
		scrollPane.setSize(new Dimension(300, 300));
		scrollPane.setFont(new Font("Californian FB", Font.BOLD, 14));
		scrollPane.setMinimumSize(new Dimension(300, 300));
		
		contentPane.add(scrollPane, BorderLayout.SOUTH);
		panel.setSize(new Dimension(0, 110));
		panel.setMinimumSize(new Dimension(10, 110));
		panel.setPreferredSize(new Dimension(1085, 150));
		
		scrollPane.setViewportView(panel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Californian FB", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(139, 69, 19));
		lblNewLabel.setPreferredSize(new Dimension(1085, 15));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(lblNewLabel);
		textArea.setFont(new Font("Californian FB", Font.PLAIN, 14));
		textArea.setPreferredSize(new Dimension(1085, 125));
		textArea.setSize(new Dimension(150, 150));
		
		panel.add(textArea);
		setOperationDrawing();
		
	}
	
	//klik na panel
	private MouseAdapter pnlDrawingClickListener() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point mouseClick = new Point(e.getX(), e.getY());
				pnlDrawing.deselect();
				
				if (activeOperation == OPERATION_EDIT_DELETE) 
				{
					pnlDrawing.select(mouseClick);
					return;
				}
				
				if (tglBtnPoint.isSelected()) 
				{
					DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint(mouseClick);
					dlgPoint.setColors(edgeColor);
					dlgPoint.setVisible(true);
					if(dlgPoint.getPoint() != null) pnlDrawing.addShape(dlgPoint.getPoint());
					return;
					
				} 
				else if (tglBtnLine.isSelected()) 
				{
					if(lineWaitingForEndPoint) 
					{
						DlgLine dlgLine = new DlgLine();
						Line line = new Line(startPoint,mouseClick);
						dlgLine.setLine(line);
						dlgLine.setColor(edgeColor);
						dlgLine.setVisible(true);
						if(dlgLine.getLine()!= null) pnlDrawing.addShape(dlgLine.getLine());
						lineWaitingForEndPoint=false;
						return;
					}
					startPoint = mouseClick;
					lineWaitingForEndPoint=true;
					return;
					
		
				} 
				else if (tglBtnRectangle.isSelected()) 
				{
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setPoint(mouseClick);
					dlgRectangle.setColors(edgeColor, innerColor);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) pnlDrawing.addShape(dlgRectangle.getRectangle());
					return;
				} 
				else if (tglBtnCircle.isSelected()) 
				{
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setPoint(mouseClick);
					dlgCircle.setColor(edgeColor);
					dlgCircle.setInnerConor(innerColor);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) pnlDrawing.addShape(dlgCircle.getCircle());
					return;
				} 
				else if (tglBtnDonut.isSelected()) 
				{
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setPoint(mouseClick);
					dlgDonut.setColors(edgeColor, innerColor);
					dlgDonut.setVisible(true);
					
					if(dlgDonut.getDonut() != null) pnlDrawing.addShape(dlgDonut.getDonut());
					return;
				}
			}
		};
	}
	
	//klik na modify dugme
	private ActionListener btnActionModifyClickListener() {
		return new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int index = pnlDrawing.getSelected();
				if (index == -1) return;
				
				Shape shape = pnlDrawing.getShape(index);
				
				if (shape instanceof Point) 
				{   DlgPoint dlgPoint = new DlgPoint();
					dlgPoint.setPoint((Point)shape);
					dlgPoint.setVisible(true);
					
					if(dlgPoint.getPoint() != null) 
					{
						pnlDrawing.setShape(index, dlgPoint.getPoint());
						pnlDrawing.repaint();
					}
				} 
				else if (shape instanceof Line) 
				{
					DlgLine dlgLine = new DlgLine();
					dlgLine.setLine((Line)shape);
					dlgLine.setVisible(true);
					
					if(dlgLine.getLine() != null) 
					{
						pnlDrawing.setShape(index, dlgLine.getLine());
						pnlDrawing.repaint();
					}
				} 
				else if (shape instanceof Rectangle) 
				{
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.setRectangle((Rectangle)shape);
					dlgRectangle.setVisible(true);
					
					if(dlgRectangle.getRectangle() != null) 
					{
						pnlDrawing.setShape(index, dlgRectangle.getRectangle());
						pnlDrawing.repaint();
					}
				
				}
				else if (shape instanceof Donut) 
				{
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.setDonut((Donut)shape);
					dlgDonut.setVisible(true);
						
					if(dlgDonut.getDonut() != null) 
					{
						pnlDrawing.setShape(index, dlgDonut.getDonut());
						pnlDrawing.repaint();
					}
				} 
				else if (shape instanceof Circle) 
				{
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.setCircle((Circle)shape);
					dlgCircle.setVisible(true);
					
					if(dlgCircle.getCircle() != null) 
					{
						pnlDrawing.setShape(index, dlgCircle.getCircle());
						pnlDrawing.repaint();
					}
				} 
			}
		};
	}
	
	//klik na delete dugme
	private ActionListener btnActionDeleteClickListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlDrawing.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"You don't have elements to delete!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "Do you really want to delete selected shape?", "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) 
				{
					pnlDrawing.removeSelected();
				}
			}
		};
	}

	private void setOperationDrawing() 
	{
		activeOperation = OPERATION_DRAWING;
		
		pnlDrawing.deselect();
		
		tglBtnModify.setEnabled(false);
		tglBtnDelete.setEnabled(false);
		
		tglBtnPoint.setEnabled(true);
		tglBtnLine.setEnabled(true);
		tglBtnRectangle.setEnabled(true);
		tglBtnCircle.setEnabled(true);
		tglBtnDonut.setEnabled(true);
		
	}
	
	private void setOperationEditDelete() 
	{
		activeOperation = OPERATION_EDIT_DELETE;
		
		tglBtnModify.setEnabled(true);
		tglBtnDelete.setEnabled(true);
		
		tglBtnPoint.setEnabled(false);
		tglBtnLine.setEnabled(false);
		tglBtnRectangle.setEnabled(false);
		tglBtnCircle.setEnabled(false);
		tglBtnDonut.setEnabled(false);
		
	}
}
