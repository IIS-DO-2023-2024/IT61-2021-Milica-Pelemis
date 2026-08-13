package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

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

	private JPanel contentPane;

	public DrawingFrame() {

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

		view.addMouseListener(new MouseAdapter() {
		});

		contentPane.add(view, BorderLayout.CENTER);

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(255, 228, 181));
		contentPane.add(pnlMenu, BorderLayout.WEST);

		GridBagLayout gbl_pnlMenu = new GridBagLayout();
		gbl_pnlMenu.columnWidths = new int[] {122, 0};
		gbl_pnlMenu.rowHeights = new int[] {198, 191, 226, 0};
		gbl_pnlMenu.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_pnlMenu.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlMenu.setLayout(gbl_pnlMenu);

		// DRAWING / SELECT
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 228, 181));

		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.insets = new Insets(0, 0, 5, 0);
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 0;
		pnlMenu.add(panel1, gbc_panel1);

		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[] {122, 0};
		gbl_panel1.rowHeights = new int[] {45, 34, 0};
		gbl_panel1.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
		panel1.setLayout(gbl_panel1);

		tglBtnDrawing.setBackground(new Color(250, 250, 210));
		tglBtnDrawing.setPreferredSize(new Dimension(100, 25));
		tglBtnDrawing.setForeground(new Color(160, 82, 45));
		tglBtnDrawing.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		tglBtnDrawing.setAlignmentX(Component.CENTER_ALIGNMENT);

		tglBtnDrawing.addActionListener(e -> controller.setOperationDrawing());

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
		tglBtnModifyOrDelete.setAlignmentX(Component.CENTER_ALIGNMENT);

		tglBtnModifyOrDelete.addActionListener(e -> controller.setOperationEditDelete());

		btnsOperation.add(tglBtnModifyOrDelete);

		GridBagConstraints gbc_tglBtnModifyOrDelete = new GridBagConstraints();
		gbc_tglBtnModifyOrDelete.gridx = 0;
		gbc_tglBtnModifyOrDelete.gridy = 1;
		panel1.add(tglBtnModifyOrDelete, gbc_tglBtnModifyOrDelete);

		// MODIFY / DELETE
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 228, 181));

		GridBagConstraints gbc_panel2 = new GridBagConstraints();
		gbc_panel2.fill = GridBagConstraints.BOTH;
		gbc_panel2.insets = new Insets(0, 0, 5, 0);
		gbc_panel2.gridx = 0;
		gbc_panel2.gridy = 1;
		pnlMenu.add(panel2, gbc_panel2);

		GridBagLayout gbl_panel2 = new GridBagLayout();
		gbl_panel2.columnWidths = new int[] {118, 0};
		gbl_panel2.rowHeights = new int[] {48, 41, 0};
		gbl_panel2.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_panel2.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
		panel2.setLayout(gbl_panel2);

		tglBtnModify.setBackground(new Color(250, 250, 210));
		tglBtnModify.setPreferredSize(new Dimension(100, 25));
		tglBtnModify.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		tglBtnModify.setForeground(new Color(160, 82, 45));
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
		tglBtnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);

		GridBagConstraints gbc_tglBtnDelete = new GridBagConstraints();
		gbc_tglBtnDelete.gridx = 0;
		gbc_tglBtnDelete.gridy = 1;
		panel2.add(tglBtnDelete, gbc_tglBtnDelete);

		// SHAPES
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 228, 181));

		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.gridx = 0;
		gbc_panel3.gridy = 2;
		pnlMenu.add(panel3, gbc_panel3);

		GridBagLayout gbl_panel3 = new GridBagLayout();
		gbl_panel3.columnWidths = new int[] {119, 0};
		gbl_panel3.rowHeights = new int[] {27, 31, 28, 27, 30, 0};
		gbl_panel3.columnWeights = new double[] {0.0, Double.MIN_VALUE};
		gbl_panel3.rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE
		};
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

		tglBtnModify.setEnabled(false);
		tglBtnDelete.setEnabled(false);
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
}