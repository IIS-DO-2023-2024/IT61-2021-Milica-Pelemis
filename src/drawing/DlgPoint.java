package drawing;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private Point point = null;
	private Color color = null;
	private JButton btnColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setModal(true);
		setTitle("Milica Pelemis, IT61/2021");
		getContentPane().setBackground(new Color(255, 228, 181));
		setBackground(new Color(255, 228, 181));
		setBounds(100, 100, 379, 254);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{215, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X coordinate:");
			lblX.setForeground(new Color(160, 82, 45));
			lblX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
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
			JLabel lblNewLabel_1 = new JLabel("Y coordinate:\r\n");
			lblNewLabel_1.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblNewLabel_1.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
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
			JButton btnColor = new JButton("Color");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose a color", color);
					if (color == null)
					{
						color = Color.BLACK;
					}
				}
			});
			btnColor.setForeground(new Color(160, 82, 45));
			btnColor.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor.gridx = 0;
			gbc_btnColor.gridy = 2;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 228, 181));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{185, 65, 190, 0};
			gbl_buttonPane.rowHeights = new int[]{37, 0};
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
							
							if(X<0 || Y<0)
							{
								JOptionPane.showMessageDialog(null, "You entered wrong value!", "Error", JOptionPane.ERROR_MESSAGE);
							}
							point = new Point (X, Y, false, color);
							dispose();
						}
						catch(Exception exception){
							JOptionPane.showMessageDialog(null,  "You entered wrong data type!", "Error!", JOptionPane.ERROR_MESSAGE);
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
				cancelButton.setForeground(new Color(160, 82, 45));
				cancelButton.setFont(new Font("Javanese Text", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.anchor = GridBagConstraints.NORTH;
				gbc_cancelButton.gridx = 2;
				gbc_cancelButton.gridy = 0;
				buttonPane.add(cancelButton, gbc_cancelButton);
			}
		}
	}
	
	public Point getPoint()
	{
		return point;
	}
	
	public void setColors(Color color)
	{
		this.color = color;
	}
	
	public void setPoint (Point p)
	{
		txtX.setText("" + p.getX());
		txtY.setText("" + p.getY());
		color = p.getColor();
	}

}
