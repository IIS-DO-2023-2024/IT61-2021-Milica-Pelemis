package dialogs;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private Rectangle rectangle = null;
	private Color edgeColor = null;
	private Color innerColor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setModal(true);
		setTitle("Milica Pelemis, IT61/2021");
		setBackground(new Color(255, 228, 181));
		getContentPane().setBackground(new Color(255, 228, 181));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{209, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X coordinate:");
			lblX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblX.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			txtX.setForeground(new Color(160, 82, 45));
			txtX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate:");
			lblY.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblY.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			txtY.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtY.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			lblHeight.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblHeight.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 2;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			txtHeight.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtHeight.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 1;
			gbc_txtHeight.gridy = 2;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			lblWidth.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblWidth.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 3;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			txtWidth.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtWidth.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 1;
			gbc_txtWidth.gridy = 3;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JButton btnEdge = new JButton("Edge color:");
			btnEdge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edgeColor= JColorChooser.showDialog(null, "Choose edge color", edgeColor);
					if(edgeColor==null)
					{
						edgeColor = Color.BLACK;
					}
				}
			});
			btnEdge.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			btnEdge.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_btnEdge = new GridBagConstraints();
			gbc_btnEdge.insets = new Insets(0, 0, 0, 5);
			gbc_btnEdge.gridx = 0;
			gbc_btnEdge.gridy = 4;
			contentPanel.add(btnEdge, gbc_btnEdge);
		}
		{
			JButton btnInner = new JButton("Inner color");
			btnInner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(null, "Choose inner color", innerColor);
					if (innerColor== null)
					{
						innerColor = Color.WHITE;
					}
				}
			});
			btnInner.setForeground(new Color(160, 82, 45));
			btnInner.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_btnInner = new GridBagConstraints();
			gbc_btnInner.gridx = 1;
			gbc_btnInner.gridy = 4;
			contentPanel.add(btnInner, gbc_btnInner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 228, 181));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{221, 41, 123, 0};
			gbl_buttonPane.rowHeights = new int[]{21, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int X = Integer.parseInt(txtX.getText());
							int Y = Integer.parseInt(txtY.getText());
							int Height = Integer.parseInt(txtHeight.getText());
							int Width = Integer.parseInt(txtWidth.getText());
							
							if(X<0 || Y<0 || Height<1 || Width<1)
							{
								JOptionPane.showMessageDialog(null, "You entered wrong value!", "Error", JOptionPane.ERROR_MESSAGE);
							}
							rectangle = new Rectangle (new Point (X, Y), Width, Height, false, edgeColor, innerColor);
							dispose();
						}
						catch(Exception exception)
						{
							JOptionPane.showMessageDialog(null, "You entered wrong data type!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setFont(new Font("Javanese Text", Font.PLAIN, 12));
				okButton.setForeground(new Color(160, 82, 45));
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.anchor = GridBagConstraints.NORTH;
				gbc_okButton.insets = new Insets(0, 0, 0, 5);
				gbc_okButton.gridx = 0;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Javanese Text", Font.PLAIN, 12));
				cancelButton.setForeground(new Color(160, 82, 45));
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.anchor = GridBagConstraints.NORTH;
				gbc_cancelButton.gridx = 2;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
	}

	public Rectangle getRectangle()
	{
		return rectangle;
	}
	
	public void setPoint (Point p)
	{
		txtX.setText("" + p.getX());
		txtY.setText("" + p.getY());
	}
	
	public void setColors(Color edgeColor, Color innerColor) {
		this.edgeColor = edgeColor;
		this.innerColor = innerColor;
	}
	
	public void setRectangle (Rectangle r)
	{
		txtX.setText("" + r.getUpperLeft().getX());
		txtY.setText("" + r.getUpperLeft().getY());
		txtHeight.setText("" + r.getHeight());
		txtWidth.setText("" + r.getwidth());
		edgeColor = r.getColor();
		innerColor = r.getInnerColor();
	}
	
}
