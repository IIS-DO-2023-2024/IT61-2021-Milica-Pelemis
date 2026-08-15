package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import geometry.Line;
import geometry.Point;

public class DlgLine extends JDialog {

	private static final long serialVersionUID = 1L;

	private final Color backgroundColor = new Color(238, 232, 170);
	private final Color panelColor = new Color(250, 248, 232);
	private final Color borderColor = new Color(189, 183, 107);
	private final Color textColor = new Color(139, 69, 19);

	private final Font titleFont =
			new Font("Segoe UI", Font.BOLD, 18);

	private final Font labelFont =
			new Font("Segoe UI", Font.PLAIN, 13);

	private final Font buttonFont =
			new Font("Segoe UI", Font.BOLD, 13);

	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;

	private JButton btnEdgeColor;

	private Line line = null;
	private Color color = null;

	public static void main(String[] args) {

		try {

			DlgLine dialog = new DlgLine();

			dialog.setDefaultCloseOperation(
					JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgLine() {

		setModal(true);
		setTitle("Line");
		setResizable(false);
		setSize(430, 400);
		setLocationRelativeTo(null);

		getContentPane().setLayout(
				new BorderLayout());

		getContentPane().setBackground(
				backgroundColor);

		createHeader();
		createContent();
		createButtons();
	}

	private void createHeader() {

		JPanel headerPanel =
				new JPanel(new BorderLayout());

		headerPanel.setBackground(
				backgroundColor);

		headerPanel.setBorder(
				new EmptyBorder(
						18,
						20,
						12,
						20));

		JLabel lblTitle =
				new JLabel("Line");

		lblTitle.setHorizontalAlignment(
				SwingConstants.CENTER);

		lblTitle.setForeground(
				textColor);

		lblTitle.setFont(
				titleFont);

		JLabel lblSubtitle =
				new JLabel(
						"Enter start point, end point and edge color");

		lblSubtitle.setHorizontalAlignment(
				SwingConstants.CENTER);

		lblSubtitle.setForeground(
				new Color(120, 110, 80));

		lblSubtitle.setFont(
				new Font(
						"Segoe UI",
						Font.PLAIN,
						12));

		JPanel titlePanel =
				new JPanel(new BorderLayout());

		titlePanel.setOpaque(false);

		titlePanel.add(
				lblTitle,
				BorderLayout.NORTH);

		titlePanel.add(
				lblSubtitle,
				BorderLayout.SOUTH);

		headerPanel.add(
				titlePanel,
				BorderLayout.CENTER);

		getContentPane().add(
				headerPanel,
				BorderLayout.NORTH);
	}

	private void createContent() {

		JPanel wrapper =
				new JPanel(new BorderLayout());

		wrapper.setBackground(
				backgroundColor);

		wrapper.setBorder(
				new EmptyBorder(
						0,
						22,
						12,
						22));

		JPanel contentPanel =
				new JPanel(
						new GridBagLayout());

		contentPanel.setBackground(
				panelColor);

		contentPanel.setBorder(
				BorderFactory.createCompoundBorder(
						new LineBorder(
								borderColor,
								1,
								true),
						new EmptyBorder(
								18,
								20,
								18,
								20)));

		txtStartX = createTextField();
		addInputRow(
				contentPanel,
				"Start X",
				txtStartX,
				0);

		txtStartY = createTextField();
		addInputRow(
				contentPanel,
				"Start Y",
				txtStartY,
				1);

		txtEndX = createTextField();
		addInputRow(
				contentPanel,
				"End X",
				txtEndX,
				2);

		txtEndY = createTextField();
		addInputRow(
				contentPanel,
				"End Y",
				txtEndY,
				3);

		btnEdgeColor =
				createColorButton(
						"Edge Color",
						Color.WHITE);

		addColorRow(
				contentPanel,
				"Edge Color",
				btnEdgeColor,
				4);

		btnEdgeColor.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				color =
						JColorChooser.showDialog(
								DlgLine.this,
								"Choose edge color",
								color);

				if (color == null) {
					color = Color.BLACK;
				}

				updateColorButton(
						btnEdgeColor,
						color);
			}
		});

		wrapper.add(
				contentPanel,
				BorderLayout.CENTER);

		getContentPane().add(
				wrapper,
				BorderLayout.CENTER);
	}

	private JTextField createTextField() {

		JTextField textField =
				new JTextField();

		textField.setPreferredSize(
				new Dimension(170, 32));

		textField.setFont(
				labelFont);

		textField.setForeground(
				textColor);

		textField.setBackground(
				Color.WHITE);

		textField.setBorder(
				BorderFactory.createCompoundBorder(
						new LineBorder(
								borderColor,
								1,
								true),
						new EmptyBorder(
								4,
								8,
								4,
								8)));

		return textField;
	}

	private void addInputRow(
			JPanel panel,
			String text,
			JTextField textField,
			int row) {

		JLabel label =
				createLabel(text);

		GridBagConstraints gbcLabel =
				new GridBagConstraints();

		gbcLabel.gridx = 0;
		gbcLabel.gridy = row;

		gbcLabel.anchor =
				GridBagConstraints.WEST;

		gbcLabel.insets =
				new Insets(
						5,
						0,
						5,
						18);

		panel.add(
				label,
				gbcLabel);

		GridBagConstraints gbcField =
				new GridBagConstraints();

		gbcField.gridx = 1;
		gbcField.gridy = row;
		gbcField.weightx = 1.0;

		gbcField.fill =
				GridBagConstraints.HORIZONTAL;

		gbcField.insets =
				new Insets(
						5,
						0,
						5,
						0);

		panel.add(
				textField,
				gbcField);
	}

	private void addColorRow(
			JPanel panel,
			String text,
			JButton button,
			int row) {

		JLabel label =
				createLabel(text);

		GridBagConstraints gbcLabel =
				new GridBagConstraints();

		gbcLabel.gridx = 0;
		gbcLabel.gridy = row;

		gbcLabel.anchor =
				GridBagConstraints.WEST;

		gbcLabel.insets =
				new Insets(
						5,
						0,
						5,
						18);

		panel.add(
				label,
				gbcLabel);

		GridBagConstraints gbcButton =
				new GridBagConstraints();

		gbcButton.gridx = 1;
		gbcButton.gridy = row;
		gbcButton.weightx = 1.0;

		gbcButton.fill =
				GridBagConstraints.HORIZONTAL;

		gbcButton.insets =
				new Insets(
						5,
						0,
						5,
						0);

		panel.add(
				button,
				gbcButton);
	}

	private JLabel createLabel(
			String text) {

		JLabel label =
				new JLabel(text + ":");

		label.setFont(
				labelFont);

		label.setForeground(
				textColor);

		return label;
	}

	private JButton createColorButton(
			String text,
			Color color) {

		JButton button =
				new JButton(text);

		button.setFont(
				buttonFont);

		button.setPreferredSize(
				new Dimension(170, 32));

		button.setFocusPainted(false);

		button.setBorder(
				new LineBorder(
						borderColor,
						1,
						true));

		updateColorButton(
				button,
				color);

		return button;
	}

	private void updateColorButton(
			JButton button,
			Color color) {

		if (color == null) {
			color = Color.WHITE;
		}

		button.setBackground(
				color);

		int brightness =
				(color.getRed() * 299
				+ color.getGreen() * 587
				+ color.getBlue() * 114)
				/ 1000;

		if (brightness < 140) {
			button.setForeground(
					Color.WHITE);
		}
		else {
			button.setForeground(
					textColor);
		}
	}

	private void createButtons() {

		JPanel buttonPanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.CENTER,
								12,
								12));

		buttonPanel.setBackground(
				backgroundColor);

		buttonPanel.setBorder(
				new EmptyBorder(
						0,
						0,
						8,
						0));

		JButton btnOk =
				createActionButton("OK");

		JButton btnCancel =
				createActionButton("Cancel");

		btnOk.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				saveLine();
			}
		});

		btnCancel.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				dispose();
			}
		});

		buttonPanel.add(btnOk);
		buttonPanel.add(btnCancel);

		getRootPane().setDefaultButton(
				btnOk);

		getContentPane().add(
				buttonPanel,
				BorderLayout.SOUTH);
	}

	private JButton createActionButton(
			String text) {

		JButton button =
				new JButton(text);

		button.setPreferredSize(
				new Dimension(105, 34));

		button.setFont(
				buttonFont);

		button.setForeground(
				textColor);

		button.setBackground(
				Color.WHITE);

		button.setFocusPainted(false);

		button.setBorder(
				new LineBorder(
						borderColor,
						1,
						true));

		return button;
	}

	private void saveLine() {

		try {

			int x1 =
					Integer.parseInt(
							txtStartX.getText());

			int y1 =
					Integer.parseInt(
							txtStartY.getText());

			int x2 =
					Integer.parseInt(
							txtEndX.getText());

			int y2 =
					Integer.parseInt(
							txtEndY.getText());

			if (x1 < 0
					|| y1 < 0
					|| x2 < 0
					|| y2 < 0) {

				JOptionPane.showMessageDialog(
						this,
						"Numbers must be positive",
						"Error",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			line =
					new Line(
							new Point(x1, y1),
							new Point(x2, y2),
							false,
							color);

			dispose();
		}
		catch (Exception exception) {

			JOptionPane.showMessageDialog(
					this,
					"Invalid character found!",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Line getLine() {
		return line;
	}

	public void setColor(
			Color color) {

		this.color = color;

		updateColorButton(
				btnEdgeColor,
				color);
	}

	public void setLine(
			Line l) {

		txtStartX.setText(
				"" + l.getStartPoint().getX());

		txtStartY.setText(
				"" + l.getStartPoint().getY());

		txtEndX.setText(
				"" + l.getEndPoint().getX());

		txtEndY.setText(
				"" + l.getEndPoint().getY());

		color = l.getColor();

		updateColorButton(
				btnEdgeColor,
				color);
	}

	public void setStartPoint(
			Point p) {

		txtStartX.setText(
				"" + p.getX());

		txtStartY.setText(
				"" + p.getY());
	}

	public void setEndPoint(
			Point p) {

		txtEndX.setText(
				"" + p.getX());

		txtEndY.setText(
				"" + p.getY());
	}
}