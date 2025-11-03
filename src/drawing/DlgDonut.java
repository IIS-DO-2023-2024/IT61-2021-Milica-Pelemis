package drawing;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

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

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private JTextField txtInnerRadius;
	private Donut donut = null;
	private Color edgeColor = null;
	private Color innerColor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setModal(true);
		setTitle("Milica Pelemis, IT61/2021");
		getContentPane().setBackground(new Color(255, 228, 181));
		setBackground(new Color(255, 228, 181));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{203, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			txtX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtX.setForeground(new Color(160, 82, 45));
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
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblRadius.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 2;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setForeground(new Color(160, 82, 45));
			txtRadius.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 2;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			lblInnerRadius.setForeground(new Color(160, 82, 45));
			lblInnerRadius.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 0;
			gbc_lblInnerRadius.gridy = 3;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			txtInnerRadius = new JTextField();
			txtInnerRadius.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtInnerRadius.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerRadius.gridx = 1;
			gbc_txtInnerRadius.gridy = 3;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
			JButton btnEdge = new JButton("Edge color");
			btnEdge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edgeColor = JColorChooser.showDialog(null, "Choose edge color", edgeColor);
					if (edgeColor == null) edgeColor = Color.BLACK;
				}
			});
			btnEdge.setForeground(new Color(160, 82, 45));
			btnEdge.setBackground(new Color(255, 255, 240));
			btnEdge.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_btnEdge = new GridBagConstraints();
			gbc_btnEdge.insets = new Insets(0, 0, 0, 5);
			gbc_btnEdge.gridx = 0;
			gbc_btnEdge.gridy = 5;
			contentPanel.add(btnEdge, gbc_btnEdge);
		}
		{
			JButton btnInner = new JButton("Inner color");
			btnInner.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(null, "Choose inner color", innerColor);
					if (innerColor == null) innerColor = Color.WHITE;
				}
			});
			btnInner.setBackground(new Color(255, 250, 240));
			btnInner.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			btnInner.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_btnInner = new GridBagConstraints();
			gbc_btnInner.gridx = 1;
			gbc_btnInner.gridy = 5;
			contentPanel.add(btnInner, gbc_btnInner);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 228, 181));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{214, 34, 144, 0};
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
							int Radius = Integer.parseInt(txtRadius.getText());
							int InnerRadius = Integer.parseInt(txtInnerRadius.getText());

							if(X < 0 || Y < 0 || Radius < 1 || InnerRadius < 1 || InnerRadius >= Radius) {
								JOptionPane.showMessageDialog(null,  "You entered wrong value!!", "Error!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							donut = new Donut(new Point(X, Y), Radius, InnerRadius, false, edgeColor, innerColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null,  "You entered wrong data type!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setBackground(new Color(255, 250, 240));
				okButton.setFont(new Font("Javanese Text", Font.PLAIN, 12));
				okButton.setForeground(new Color(160, 82, 45));
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
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
				cancelButton.setBackground(new Color(255, 250, 240));
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

	public Donut getDonut ()
	{
		return donut;
	}
	public void setPoint (Point point)
	{
		txtX.setText("" + point.getX());
		txtY.setText("" + point.getY());
	}
	public void setColors(Color edgeColor, Color innerColor)
	{
		this.edgeColor=edgeColor;
		this.innerColor=innerColor;
	}
	
	public void setDonut (Donut d)
	{
		txtX.setText("" + d.getCenter().getX());
		txtY.setText("" + d.getCenter().getY());
		txtRadius.setText("" + d.getRadius());
		txtInnerRadius.setText("" + d.getInnerRadius());
		edgeColor = d.getColor();
		innerColor = d.getInnerColor();
	}
	
}
