package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.LibrarySystemException;
import business.SystemController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNewBookCopy extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final AddNewBookCopy INSTANCE = new AddNewBookCopy();
	private JPanel contentPane;
	private JTextField textFieldISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					INSTANCE.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private AddNewBookCopy() {
		setTitle("Add new book copy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblisbn = new JLabel("ISBN");
		lblisbn.setBounds(93, 93, 49, 14);
		contentPane.add(lblisbn);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(187, 90, 135, 20);
		contentPane.add(textFieldISBN);
		textFieldISBN.setColumns(10);
		
		JButton btnAddBookCopy = new JButton("Add a copy");
		btnAddBookCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldISBN.getText().isBlank() || !textFieldISBN.getText().matches("\\d\\d-\\d\\d\\d\\d\\d")) {
					JOptionPane.showMessageDialog(null, "Enter a valid ISBN.");
				}else {
				try {
					SystemController sc = new SystemController();
					sc.addNewBookCopy(textFieldISBN.getText());
					JOptionPane.showMessageDialog(null, "Successfully added a new copy.");
					
				}catch(LibrarySystemException l1) {
					JOptionPane.showMessageDialog(null, "The ISBN you're looking for is not in the collection. Add as a new book instead.");
				}
				}
				
				
			}
		});
		btnAddBookCopy.setBounds(130, 142, 168, 23);
		contentPane.add(btnAddBookCopy);
		
		JButton btnBack = new JButton("<<-- Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminWindow.getInstance().setVisible(true);
				AddNewBookCopy.INSTANCE.dispose();
				
				
			}
		});
		btnBack.setBounds(39, 209, 89, 23);
		contentPane.add(btnBack);
	}

}
