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

import adapter.HexagonAdapter;
import geometry.Point;

public class DlgHexagon extends JDialog {

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
	private JButton btnInnerColor;

	private HexagonAdapter hexagon = null;

	private Color edgeColor = Color.BLACK;
	private Color innerColor = Color.WHITE;

	public DlgHexagon() {

		setModal(true);
		setTitle("Hexagon");
		setResizable(false);
		setSize(430, 360);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(backgroundColor);

		createHeader();
		createContent();
		createButtons();
	}

	private void createHeader() {

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBackground(backgroundColor);
		headerPanel.setBorder(
				new EmptyBorder(18, 20, 12, 20));

		JLabel lblTitle = new JLabel("Hexagon");
		lblTitle.setHorizontalAlignment(
				SwingConstants.CENTER);
		lblTitle.setForeground(textColor);
		lblTitle.setFont(titleFont);

		JLabel lblSubtitle =
				new JLabel(
						"Enter position, radius and colors");

		lblSubtitle.setHorizontalAlignment(
				SwingConstants.CENTER);

		lblSubtitle.setForeground(
				new Color(120, 110, 80));

		lblSubtitle.setFont(
				new Font("Segoe UI", Font.PLAIN, 12));

		JPanel titlePanel = new JPanel(
				new BorderLayout());

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

		JPanel wrapper = new JPanel(
				new BorderLayout());

		wrapper.setBackground(backgroundColor);

		wrapper.setBorder(
				new EmptyBorder(0, 22, 12, 22));

		JPanel contentPanel = new JPanel(
				new GridBagLayout());

		contentPanel.setBackground(panelColor);

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

		addInputRow(
				contentPanel,
				"X coordinate",
				createTextField(),
				0);

		txtX = lastCreatedTextField;

		addInputRow(
				contentPanel,
				"Y coordinate",
				createTextField(),
				1);

		txtY = lastCreatedTextField;

		addInputRow(
				contentPanel,
				"Radius",
				createTextField(),
				2);

		txtRadius = lastCreatedTextField;

		btnEdgeColor =
				createColorButton(
						"Edge color",
						edgeColor);

		addColorRow(
				contentPanel,
				"Edge color",
				btnEdgeColor,
				3);

		btnInnerColor =
				createColorButton(
						"Fill color",
						innerColor);

		addColorRow(
				contentPanel,
				"Fill color",
				btnInnerColor,
				4);

		btnEdgeColor.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				Color selectedColor =
						JColorChooser.showDialog(
								DlgHexagon.this,
								"Choose edge color",
								edgeColor);

				if (selectedColor != null) {

					edgeColor =
							selectedColor;

					updateColorButton(
							btnEdgeColor,
							edgeColor);
				}
			}
		});

		btnInnerColor.addActionListener(
				new ActionListener() {

			@Override
			public void actionPerformed(
					ActionEvent e) {

				Color selectedColor =
						JColorChooser.showDialog(
								DlgHexagon.this,
								"Choose fill color",
								innerColor);

				if (selectedColor != null) {

					innerColor =
							selectedColor;

					updateColorButton(
							btnInnerColor,
							innerColor);
				}
			}
		});

		wrapper.add(
				contentPanel,
				BorderLayout.CENTER);

		getContentPane().add(
				wrapper,
				BorderLayout.CENTER);
	}

	private JTextField lastCreatedTextField;

	private JTextField createTextField() {

		JTextField textField =
				new JTextField();

		textField.setPreferredSize(
				new Dimension(170, 32));

		textField.setFont(labelFont);
		textField.setForeground(textColor);
		textField.setBackground(Color.WHITE);

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

		lastCreatedTextField = textField;

		return textField;
	}

	private void addInputRow(
			JPanel panel,
			String text,
			JTextField textField,
			int row) {

		JLabel label = createLabel(text);

		GridBagConstraints gbcLabel =
				new GridBagConstraints();

		gbcLabel.gridx = 0;
		gbcLabel.gridy = row;

		gbcLabel.anchor =
				GridBagConstraints.WEST;

		gbcLabel.insets =
				new Insets(5, 0, 5, 18);

		panel.add(label, gbcLabel);

		GridBagConstraints gbcField =
				new GridBagConstraints();

		gbcField.gridx = 1;
		gbcField.gridy = row;

		gbcField.weightx = 1.0;

		gbcField.fill =
				GridBagConstraints.HORIZONTAL;

		gbcField.insets =
				new Insets(5, 0, 5, 0);

		panel.add(
				textField,
				gbcField);
	}

	private void addColorRow(
			JPanel panel,
			String text,
			JButton button,
			int row) {

		JLabel label = createLabel(text);

		GridBagConstraints gbcLabel =
				new GridBagConstraints();

		gbcLabel.gridx = 0;
		gbcLabel.gridy = row;

		gbcLabel.anchor =
				GridBagConstraints.WEST;

		gbcLabel.insets =
				new Insets(5, 0, 5, 18);

		panel.add(label, gbcLabel);

		GridBagConstraints gbcButton =
				new GridBagConstraints();

		gbcButton.gridx = 1;
		gbcButton.gridy = row;

		gbcButton.weightx = 1.0;

		gbcButton.fill =
				GridBagConstraints.HORIZONTAL;

		gbcButton.insets =
				new Insets(5, 0, 5, 0);

		panel.add(
				button,
				gbcButton);
	}

	private JLabel createLabel(String text) {

		JLabel label =
				new JLabel(text + ":");

		label.setFont(labelFont);
		label.setForeground(textColor);

		return label;
	}

	private JButton createColorButton(
			String text,
			Color color) {

		JButton button =
				new JButton(text);

		button.setFont(buttonFont);

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

		button.setBackground(color);

		int brightness =
				(color.getRed() * 299
				+ color.getGreen() * 587
				+ color.getBlue() * 114)
				/ 1000;

		if (brightness < 140) {
			button.setForeground(Color.WHITE);
		}
		else {
			button.setForeground(textColor);
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

				saveHexagon();
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

		getRootPane().setDefaultButton(btnOk);

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

		button.setFont(buttonFont);
		button.setForeground(textColor);
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);

		button.setBorder(
				new LineBorder(
						borderColor,
						1,
						true));

		return button;
	}

	private void saveHexagon() {

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

			hexagon =
					new HexagonAdapter(
							new Point(x, y),
							radius,
							false,
							edgeColor,
							innerColor);

			dispose();
		}
		catch (NumberFormatException exception) {

			JOptionPane.showMessageDialog(
					this,
					"Please enter valid numbers.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}

	public void setHexagon(
			HexagonAdapter hexagon) {

		txtX.setText(
				"" + hexagon.getX());

		txtY.setText(
				"" + hexagon.getY());

		txtRadius.setText(
				"" + hexagon.getRadius());

		edgeColor =
				hexagon.getColor();

		innerColor =
				hexagon.getInnerColor();

		updateColorButton(
				btnEdgeColor,
				edgeColor);

		updateColorButton(
				btnInnerColor,
				innerColor);
	}

	public void setPoint(Point point) {

		txtX.setText(
				"" + point.getX());

		txtY.setText(
				"" + point.getY());
	}

	public void setColors(
			Color edgeColor,
			Color innerColor) {

		this.edgeColor = edgeColor;
		this.innerColor = innerColor;

		updateColorButton(
				btnEdgeColor,
				edgeColor);

		updateColorButton(
				btnInnerColor,
				innerColor);
	}
}