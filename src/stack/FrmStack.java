package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private Deque <String> stack = new ArrayDeque <String>();
	private DefaultListModel<String> dlm = new DefaultListModel<>();
	private JList lstStack = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setTitle("Milica Pelemis, IT61/2021");
		setBackground(new Color(245, 245, 220));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 181));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0};
		gbl_pnlCenter.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 228, 181));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlCenter.add(scrollPane, gbc_scrollPane);
		
		lstStack = new JList();
		lstStack.setFont(new Font("Javanese Text", Font.PLAIN, 13));
		lstStack.setBackground(new Color(250, 250, 210));
		scrollPane.setViewportView(lstStack);
		lstStack.setModel(dlm);


		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(new Color(255, 228, 181));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlSouth = new GridBagLayout();
		gbl_pnlSouth.columnWidths = new int[]{117, 52, 0, 72, 0, 0};
		gbl_pnlSouth.rowHeights = new int[]{0, 0};
		gbl_pnlSouth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlSouth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlSouth.setLayout(gbl_pnlSouth);
		
		//ADD BUTTON
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(160, 82, 45));
		btnAdd.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true);
				
				if (dlgStack.isOk==true)
				{
					dlm.addElement("Center: (" + dlgStack.txtX.getText() + ", " + dlgStack.txtY.getText() + ") Radius: " + dlgStack.txtRadius.getText());
				}
				String circle = new String();
				circle = "Center: (" + dlgStack.txtX.getText() + ", " + dlgStack.txtY.getText() + ") Radius: " + dlgStack.txtRadius.getText();
				stack.addFirst(circle);
				
				
			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 0;
		pnlSouth.add(btnAdd, gbc_btnAdd);
		
		//DELETE BUTTON
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(160, 82, 45));
		btnDelete.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(dlm.isEmpty()==false && dlm!=null)
				{
					dlm.remove(0);
					stack.pop();
					
				}
				else {
				JOptionPane.showMessageDialog(null,"Stack is empty!","ERROR", JOptionPane.ERROR_MESSAGE);
				return;
				}	
				
				
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 0;
		pnlSouth.add(btnDelete, gbc_btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setForeground(new Color(160, 82, 45));
		btnClose.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.gridx = 4;
		gbc_btnClose.gridy = 0;
		pnlSouth.add(btnClose, gbc_btnClose);
	}

}
