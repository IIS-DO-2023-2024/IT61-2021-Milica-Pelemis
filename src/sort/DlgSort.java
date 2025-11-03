package sort;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class DlgSort extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private Circle circle = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSort dialog = new DlgSort();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSort() {
		setBackground(new Color(250, 250, 210));
		getContentPane().setBackground(new Color(250, 250, 210));
		setTitle("Milica Pelemis, IT61/2021");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setForeground(new Color(160, 82, 45));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Enter the circle values");
			lblNewLabel.setFont(new Font("Javanese Text", Font.PLAIN, 16));
			lblNewLabel.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblX = new JLabel("X coordinate:");
			lblX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblX.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 1;
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
			gbc_txtX.gridy = 1;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y coordinate:");
			lblY.setForeground(new Color(160, 82, 45));
			lblY.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 2;
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
			gbc_txtY.gridy = 2;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			lblRadius.setForeground(new Color(160, 82, 45));
			lblRadius.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			txtRadius.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtRadius.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 1;
			gbc_txtRadius.gridy = 3;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 228, 181));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				GridBagLayout gbl_buttonPane = new GridBagLayout();
				gbl_buttonPane.columnWidths = new int[]{154, 138, 112, 0};
				gbl_buttonPane.rowHeights = new int[]{37, 0};
				gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
				buttonPane.setLayout(gbl_buttonPane);
			}
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			cancelButton.setForeground(new Color(160, 82, 45));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Javanese Text", Font.PLAIN, 12));
				okButton.setForeground(new Color(160, 82, 45));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int x = Integer.parseInt(txtX.getText());
							int y = Integer.parseInt(txtY.getText());
							int radius = Integer.parseInt(txtRadius.getText());
							
							if(x<0 || y<0 || radius<1)
							{
								JOptionPane.showMessageDialog(null,  "Numbers must be positive!", "Error", JOptionPane.ERROR_MESSAGE );
								return; 
							}
							
							circle = new Circle (new Point (x,y), radius);
							dispose();
						}
						catch (Exception exception)
						{
							JOptionPane.showMessageDialog(null,  "Invalid character found!", "Error", JOptionPane.ERROR_MESSAGE );
							
						}
						
					}
				});
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.anchor = GridBagConstraints.NORTH;
				gbc_okButton.insets = new Insets(0, 0, 0, 5);
				gbc_okButton.gridx = 0;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			cancelButton.setActionCommand("Cancel");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.anchor = GridBagConstraints.NORTH;
			gbc_cancelButton.gridx = 2;
			gbc_cancelButton.gridy = 0;
			buttonPane.add(cancelButton, gbc_cancelButton);
		}
	}

	public Circle getCircle ()
	{
		return this.circle;
	}
	
	public void setCircle(Circle c) { 
		txtX.setText("" + c.getCenter().getX());
		txtY.setText("" + c.getCenter().getY());
		txtRadius.setText("" + c.getRadius());

	}
}
