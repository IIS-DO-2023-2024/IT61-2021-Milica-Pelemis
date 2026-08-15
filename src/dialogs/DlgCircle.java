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

import geometry.Circle;
import geometry.Point;

public class DlgCircle extends JDialog {

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

	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;

	private JButton btnEdgeColor;
	private JButton btnFillColor;

	private Circle circle = null;
	private Color edgeColor = null;
	private Color innerColor = null;

	public static void main(String[] args) {

		try {

			DlgCircle dialog = new DlgCircle();

			dialog.setDefaultCloseOperation(
					JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgCircle() {

		setModal(true);
		setTitle("Circle");
		setResizable(false);
		setSize(430, 360);
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
				new JLabel("Circle");

		lblTitle.setHorizontalAlignment(
				SwingConstants.CENTER);

		lblTitle.setForeground(
				textColor);

		lblTitle.setFont(
				titleFont);

		JLabel lblSubtitle =
				new JLabel(
						"Enter position, radius and colors");

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

		txtX = createTextField();
		addInputRow(
				contentPanel,
				"X coordinate",
				txtX,
				0);

		txtY = createTextField();
		addInputRow(
				contentPanel,
				"Y coordinate",
				txtY,
				1);

		txtRadius = createTextField();
		addInputRow(
				contentPanel,
				"Radius",
				txtRadius,
				2);

		btnEdgeColor =
				createColorButton(
						"Edge Color",
						Color.WHITE);

		addColorRow(
				contentPanel,
				"Edge Color",
				btnEdgeColor,
				3);

		btnFillColor =
				createColorButton(
						"Fill Color",
						Color.WHITE);

		addColorRow(
				contentPanel,
				"Fill Color",
				btnFillColor,
				4);

		btnEdgeColor.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				edgeColor =
						JColorChooser.showDialog(
								DlgCircle.this,
								"Choose edge color",
								edgeColor);

				if (edgeColor == null) {
					edgeColor = Color.BLACK;
				}

				updateColorButton(
						btnEdgeColor,
						edgeColor);
			}
		});

		btnFillColor.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				innerColor =
						JColorChooser.showDialog(
								DlgCircle.this,
								"Choose fill color",
								innerColor);

				if (innerColor == null) {
					innerColor = Color.WHITE;
				}

				updateColorButton(
						btnFillColor,
						innerColor);
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

				saveCircle();
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

	private void saveCircle() {

		try {

			int x =
					Integer.parseInt(
							txtX.getText());

			int y =
					Integer.parseInt(
							txtY.getText());

			int radius =
					Integer.parseInt(
							txtRadius.getText());

			if (x < 0
					|| y < 0
					|| radius < 1) {

				JOptionPane.showMessageDialog(
						this,
						"Numbers must be positive!",
						"Error",
						JOptionPane.ERROR_MESSAGE);

				return;
			}

			circle =
					new Circle(
							new Point(x, y),
							radius,
							false,
							edgeColor,
							innerColor);

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

	public Circle getCircle() {
		return this.circle;
	}

	public void setCircle(Circle c) {

		txtX.setText(
				"" + c.getCenter().getX());

		txtY.setText(
				"" + c.getCenter().getY());

		txtRadius.setText(
				"" + c.getRadius());

		edgeColor = c.getColor();
		innerColor = c.getInnerColor();

		updateColorButton(
				btnEdgeColor,
				edgeColor);

		updateColorButton(
				btnFillColor,
				innerColor);
	}

	public void setPoint(Point p) {

		txtX.setText(
				"" + p.getX());

		txtY.setText(
				"" + p.getY());
	}

	public Color getEggeColor() {
		return edgeColor;
	}

	public Color getInnerColor() {
		return innerColor;
	}

	public void setColor(
			Color edgeColor) {

		this.edgeColor = edgeColor;

		updateColorButton(
				btnEdgeColor,
				edgeColor);
	}

	public void setInnerConor(
			Color innerColor) {

		this.innerColor = innerColor;

		updateColorButton(
				btnFillColor,
				innerColor);
	}
}