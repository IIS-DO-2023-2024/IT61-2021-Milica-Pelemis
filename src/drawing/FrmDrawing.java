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
		setBackground(new Color(255, 228, 181));
		setTitle("Milica Pelemis, IT61/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(1000, 700));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 181));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pnlDrawing.addMouseListener(pnlDrawingClickListener());
		contentPane.add(pnlDrawing, BorderLayout.CENTER);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(255, 228, 181));
		contentPane.add(pnlMenu, BorderLayout.WEST);
		GridBagLayout gbl_pnlMenu = new GridBagLayout();
		gbl_pnlMenu.columnWidths = new int[]{122, 0};
		gbl_pnlMenu.rowHeights = new int[]{198, 191, 226, 0};
		gbl_pnlMenu.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlMenu.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlMenu.setLayout(gbl_pnlMenu);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 181));
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.insets = new Insets(0, 0, 5, 0);
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 0;
		pnlMenu.add(panel1, gbc_panel1);
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[]{122, 0};
		gbl_panel1.rowHeights = new int[]{45, 34, 0};
		gbl_panel1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel1.setLayout(gbl_panel1);
		tglBtnDrawing.setBackground(new Color(250, 250, 210));
		tglBtnDrawing.setPreferredSize(new Dimension(100, 25));
		tglBtnDrawing.setForeground(new Color(160, 82, 45));
		tglBtnDrawing.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		
		tglBtnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationDrawing();
			}
		});
		tglBtnDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(tglBtnDrawing);
		GridBagConstraints gbc_tglBtnDrawing = new GridBagConstraints();
		gbc_tglBtnDrawing.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnDrawing.gridx = 0;
		gbc_tglBtnDrawing.gridy = 0;
		panel1.add(tglBtnDrawing, gbc_tglBtnDrawing);
		
		tglBtnDrawing.setSelected(true);
		tglBtnModifyOrDelete.setBackground(new Color(250, 250, 210));
		tglBtnModifyOrDelete.setPreferredSize(new Dimension(100, 25));
		tglBtnModifyOrDelete.setForeground(new Color(160, 82, 45));
		tglBtnModifyOrDelete.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		
		tglBtnModifyOrDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOperationEditDelete();
			}
		});
		tglBtnModifyOrDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsOperation.add(tglBtnModifyOrDelete);
		GridBagConstraints gbc_tglBtnModifyOrDelete = new GridBagConstraints();
		gbc_tglBtnModifyOrDelete.gridx = 0;
		gbc_tglBtnModifyOrDelete.gridy = 1;
		panel1.add(tglBtnModifyOrDelete, gbc_tglBtnModifyOrDelete);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 228, 181));
		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 1;
		pnlMenu.add(panel2, gbc_panel2);
		GridBagLayout gbl_panel2 = new GridBagLayout();
		gbl_panel2.columnWidths = new int[]{118, 0};
		gbl_panel2.rowHeights = new int[]{48, 41, 0};
		gbl_panel2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel2.setLayout(gbl_panel2);
		tglBtnModify.setBackground(new Color(250, 250, 210));
		tglBtnModify.setPreferredSize(new Dimension(100, 25));
		tglBtnModify.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		tglBtnModify.setForeground(new Color(160, 82, 45));
		
		tglBtnModify.addActionListener(btnActionModifyClickListener());
		tglBtnModify.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_tglBtnModify = new GridBagConstraints();
		gbc_tglBtnModify.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnModify.gridx = 0;
		gbc_tglBtnModify.gridy = 0;
		panel2.add(tglBtnModify, gbc_tglBtnModify);
		tglBtnDelete.setBackground(new Color(250, 250, 210));
		tglBtnDelete.setPreferredSize(new Dimension(100, 25));
		tglBtnDelete.setForeground(new Color(160, 82, 45));
		tglBtnDelete.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		
		tglBtnDelete.addActionListener(btnActionDeleteClickListener());
		tglBtnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_tglBtnDelete = new GridBagConstraints();
		gbc_tglBtnDelete.gridx = 0;
		gbc_tglBtnDelete.gridy = 1;
		panel2.add(tglBtnDelete, gbc_tglBtnDelete);
		
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 228, 181));
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.gridx = 0;
		gbc_panel3.gridy = 2;
		pnlMenu.add(panel3, gbc_panel3);
		GridBagLayout gbl_panel3 = new GridBagLayout();
		gbl_panel3.columnWidths = new int[]{119, 0};
		gbl_panel3.rowHeights = new int[]{27, 31, 28, 27, 30, 0};
		gbl_panel3.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel3.setLayout(gbl_panel3);
		tglBtnPoint.setBackground(new Color(250, 250, 210));
		tglBtnPoint.setPreferredSize(new Dimension(100, 25));
		tglBtnPoint.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		tglBtnPoint.setForeground(new Color(160, 82, 45));
		btnsShapes.add(tglBtnPoint);
		GridBagConstraints gbc_tglBtnPoint = new GridBagConstraints();
		gbc_tglBtnPoint.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnPoint.gridx = 0;
		gbc_tglBtnPoint.gridy = 0;
		panel3.add(tglBtnPoint, gbc_tglBtnPoint);
		tglBtnLine.setBackground(new Color(250, 250, 210));
		tglBtnLine.setPreferredSize(new Dimension(100, 25));
		tglBtnLine.setForeground(new Color(160, 82, 45));
		tglBtnLine.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		
		tglBtnLine.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnLine);
		GridBagConstraints gbc_tglBtnLine = new GridBagConstraints();
		gbc_tglBtnLine.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnLine.gridx = 0;
		gbc_tglBtnLine.gridy = 1;
		panel3.add(tglBtnLine, gbc_tglBtnLine);
		tglBtnRectangle.setBackground(new Color(250, 250, 210));
		tglBtnRectangle.setPreferredSize(new Dimension(100, 25));
		tglBtnRectangle.setForeground(new Color(160, 82, 45));
		tglBtnRectangle.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		
		tglBtnRectangle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnRectangle);
		GridBagConstraints gbc_tglBtnRectangle = new GridBagConstraints();
		gbc_tglBtnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnRectangle.gridx = 0;
		gbc_tglBtnRectangle.gridy = 2;
		panel3.add(tglBtnRectangle, gbc_tglBtnRectangle);
		tglBtnCircle.setBackground(new Color(250, 250, 210));
		tglBtnCircle.setPreferredSize(new Dimension(100, 25));
		tglBtnCircle.setForeground(new Color(160, 82, 45));
		tglBtnCircle.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		
		tglBtnCircle.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnCircle);
		GridBagConstraints gbc_tglBtnCircle = new GridBagConstraints();
		gbc_tglBtnCircle.insets = new Insets(0, 0, 5, 0);
		gbc_tglBtnCircle.gridx = 0;
		gbc_tglBtnCircle.gridy = 3;
		panel3.add(tglBtnCircle, gbc_tglBtnCircle);
		tglBtnDonut.setBackground(new Color(250, 250, 210));
		tglBtnDonut.setPreferredSize(new Dimension(100, 25));
		tglBtnDonut.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		tglBtnDonut.setForeground(new Color(160, 82, 45));
		
		tglBtnDonut.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnsShapes.add(tglBtnDonut);
		GridBagConstraints gbc_tglBtnDonut = new GridBagConstraints();
		gbc_tglBtnDonut.gridx = 0;
		gbc_tglBtnDonut.gridy = 4;
		panel3.add(tglBtnDonut, gbc_tglBtnDonut);
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
