package dialogs;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
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

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private Line line = null;
	private Color color = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
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
		gbl_contentPanel.columnWidths = new int[]{202, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartX = new JLabel("X coordinate od start point:");
			lblStartX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblStartX.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblStartX = new GridBagConstraints();
			gbc_lblStartX.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartX.gridx = 0;
			gbc_lblStartX.gridy = 0;
			contentPanel.add(lblStartX, gbc_lblStartX);
		}
		{
			txtStartX = new JTextField();
			txtStartX.setForeground(new Color(160, 82, 45));
			txtStartX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_txtStartX = new GridBagConstraints();
			gbc_txtStartX.insets = new Insets(0, 0, 5, 0);
			gbc_txtStartX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartX.gridx = 1;
			gbc_txtStartX.gridy = 0;
			contentPanel.add(txtStartX, gbc_txtStartX);
			txtStartX.setColumns(10);
		}
		{
			JLabel lblStartY = new JLabel("Y coordinate od start point:");
			lblStartY.setForeground(new Color(160, 82, 45));
			lblStartY.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_lblStartY = new GridBagConstraints();
			gbc_lblStartY.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartY.gridx = 0;
			gbc_lblStartY.gridy = 1;
			contentPanel.add(lblStartY, gbc_lblStartY);
		}
		{
			txtStartY = new JTextField();
			txtStartY.setForeground(new Color(160, 82, 45));
			txtStartY.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_txtStartY = new GridBagConstraints();
			gbc_txtStartY.insets = new Insets(0, 0, 5, 0);
			gbc_txtStartY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartY.gridx = 1;
			gbc_txtStartY.gridy = 1;
			contentPanel.add(txtStartY, gbc_txtStartY);
			txtStartY.setColumns(10);
		}
		{
			JLabel lblEndX = new JLabel("X coordinate od end point:");
			lblEndX.setForeground(new Color(160, 82, 45));
			lblEndX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			GridBagConstraints gbc_lblEndX = new GridBagConstraints();
			gbc_lblEndX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndX.gridx = 0;
			gbc_lblEndX.gridy = 2;
			contentPanel.add(lblEndX, gbc_lblEndX);
		}
		{
			txtEndX = new JTextField();
			txtEndX.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtEndX.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_txtEndX = new GridBagConstraints();
			gbc_txtEndX.insets = new Insets(0, 0, 5, 0);
			gbc_txtEndX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndX.gridx = 1;
			gbc_txtEndX.gridy = 2;
			contentPanel.add(txtEndX, gbc_txtEndX);
			txtEndX.setColumns(10);
		}
		{
			JLabel lblEndY = new JLabel("Y coordinate of end point:");
			lblEndY.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			lblEndY.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_lblEndY = new GridBagConstraints();
			gbc_lblEndY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndY.gridx = 0;
			gbc_lblEndY.gridy = 3;
			contentPanel.add(lblEndY, gbc_lblEndY);
		}
		{
			txtEndY = new JTextField();
			txtEndY.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			txtEndY.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_txtEndY = new GridBagConstraints();
			gbc_txtEndY.insets = new Insets(0, 0, 5, 0);
			gbc_txtEndY.anchor = GridBagConstraints.ABOVE_BASELINE;
			gbc_txtEndY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndY.gridx = 1;
			gbc_txtEndY.gridy = 3;
			contentPanel.add(txtEndY, gbc_txtEndY);
			txtEndY.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Line color");
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose a color", color);
					if (color == null) 
						{
						color = Color.BLACK;
						}
				}
			});
			btnColor.setFont(new Font("Javanese Text", Font.PLAIN, 12));
			btnColor.setForeground(new Color(160, 82, 45));
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor.gridx = 0;
			gbc_btnColor.gridy = 4;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 228, 181));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{162, 146, 115, 0};
			gbl_buttonPane.rowHeights = new int[]{21, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int x1 = Integer.parseInt(txtStartX.getText());
							int y1 = Integer.parseInt(txtStartY.getText());
							int x2 = Integer.parseInt(txtEndX.getText());
							int y2 = Integer.parseInt(txtEndY.getText());
							
							if (x1<0 || y1<0 || x2<0 || y2<0)
							{
								JOptionPane.showMessageDialog(null,  "Numbers must be positive", "Error", JOptionPane.ERROR_MESSAGE);
							}
							
							line = new Line (new Point (x1, y1), new Point (x2, y2), false, color);
							dispose();
						}
							catch(Exception exception)
						{
							JOptionPane.showMessageDialog(null,  "Invalid character found!", "Error", JOptionPane.ERROR_MESSAGE );
						}
					}
				});
				okButton.setForeground(new Color(160, 82, 45));
				okButton.setFont(new Font("Javanese Text", Font.PLAIN, 12));
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

	public Line getLine()
	{
		return line;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	public void setLine (Line l)
	{
		txtStartX.setText("" + l.getStartPoint().getX());
		txtStartY.setText("" + l.getStartPoint().getY());
		txtEndX.setText("" + l.getEndPoint().getX());
		txtEndY.setText("" + l.getEndPoint().getY());
		color = l.getColor();
	}
	public void setStartPoint (Point p)
	{
		txtStartX.setText("" + p.getX());
		txtStartY.setText("" + p.getY());
	}
	public void setEndPoint (Point p)
	{
		txtEndX.setText("" + p.getX());
		txtEndY.setText("" + p.getY());
	}
	
}
