package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;

import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	DefaultListModel<Circle> dlm = new DefaultListModel<Circle>();
	ArrayList <Circle> circles = new ArrayList<Circle>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
		setTitle("Milica Pelemis, IT61/2021");
		setBackground(new Color(250, 250, 210));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 181));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(new Color(250, 250, 210));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0};
		gbl_pnlCenter.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(250, 250, 210));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlCenter.add(scrollPane, gbc_scrollPane);
		
		JList list = new JList();
		list.setBackground(new Color(250, 250, 210));
		list.setForeground(new Color(160, 82, 45));
		list.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		scrollPane.setViewportView(list);
		list.setModel(dlm); 
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 228, 181));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlSouth = new GridBagLayout();
		gbl_pnlSouth.columnWidths = new int[]{169, 83, 174, 0};
		gbl_pnlSouth.rowHeights = new int[]{0, 0};
		gbl_pnlSouth.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlSouth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlSouth.setLayout(gbl_pnlSouth);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		btnAdd.setForeground(new Color(160, 82, 45));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgSort dlgSort = new DlgSort ();
				dlgSort.setVisible(true);
				if (dlgSort.getCircle() != null) 
				{
					circles.add(dlgSort.getCircle()); 
					circles.sort(null); 
					dlm.clear(); 
					for (int i=0; i<circles.size(); i++)
					{
						dlm.add(i, circles.get(i)); 
					}
					
				}
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		pnlSouth.add(btnAdd, gbc_btnAdd);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		btnClose.setForeground(new Color(160, 82, 45));
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.gridx = 2;
		gbc_btnClose.gridy = 0;
		pnlSouth.add(btnClose, gbc_btnClose);
	}

}
