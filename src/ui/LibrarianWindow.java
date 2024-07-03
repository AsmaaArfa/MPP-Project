package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import business.SystemController;
import dataaccess.Auth;

public class LibrarianWindow extends JFrame {

	public static final LibrarianWindow INSTANCE = new LibrarianWindow();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textLibraryWindowMemberId;
	private JTextField textLibraryWindowISBN;
	JComboBox<String> comboBox = new JComboBox<>();
	private JTextField textBookCopyNum;
	private JTable table;
	private List<String> data;
	SystemController sc = new SystemController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianWindow frame = new LibrarianWindow();
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
	public LibrarianWindow() {
		setTitle("Librarian Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 847, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(193, 125, 512, 143);
		contentPane.add(scrollPane);

		table = new JTable();
		;
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Member Id", "Book Title", "ISBN",
				"Copy Num", "Check Out Date", "Due Date", "Over Due" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(82);
		table.getColumnModel().getColumn(0).setMinWidth(18);
		table.getColumnModel().getColumn(1).setPreferredWidth(94);
		table.getColumnModel().getColumn(2).setPreferredWidth(81);
		table.getColumnModel().getColumn(3).setPreferredWidth(89);
		table.getColumnModel().getColumn(4).setPreferredWidth(87);
		table.getColumnModel().getColumn(5).setPreferredWidth(83);
		table.getColumnModel().getColumn(6).setPreferredWidth(86);

		JLabel lblSelectOpretion = new JLabel("Select Operation");
		lblSelectOpretion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectOpretion.setBounds(40, 52, 143, 35);
		contentPane.add(lblSelectOpretion);

		JLabel lblLibrarianWindowMemberId = new JLabel("Member Id");
		lblLibrarianWindowMemberId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLibrarianWindowMemberId.setBounds(193, 50, 80, 34);
		contentPane.add(lblLibrarianWindowMemberId);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIsbn.setBounds(344, 50, 80, 34);
		contentPane.add(lblIsbn);

		textLibraryWindowMemberId = new JTextField();

		textLibraryWindowMemberId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String com = (String) comboBox.getSelectedItem();
				if (com.equals("Return")) {
					JOptionPane.showMessageDialog(null, "You don't need to have a member ID to return a book!");
					return;
				} else if (com.equals("Check Out")) {
					// JOptionPane.showMessageDialog(null, "You don't need to have a member ID to
					// return a book!");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "You need to select operation!");
					return;
				}
			}
		});
		textLibraryWindowMemberId.setBounds(193, 81, 133, 29);
		contentPane.add(textLibraryWindowMemberId);
		textLibraryWindowMemberId.setColumns(10);

		textLibraryWindowISBN = new JTextField();

		textLibraryWindowISBN.setColumns(10);
		textLibraryWindowISBN.setBounds(344, 81, 133, 29);
		contentPane.add(textLibraryWindowISBN);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		JButton btnLibraryWindowCheckOut = new JButton("Check Out");
		btnLibraryWindowCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!SystemController.isNumeric(textLibraryWindowMemberId.getText())) {
					JOptionPane.showMessageDialog(null, "Please enter a valid \"memberId\"");
					return;
				}

				if (textLibraryWindowMemberId.getText() == null || textLibraryWindowISBN.getText() == null
						|| textLibraryWindowMemberId.getText().isEmpty() || textLibraryWindowISBN.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter both member Id and ISBN!");
					textLibraryWindowMemberId.setText("");
					textLibraryWindowISBN.setText("");
				} else {
					sc.chekOut(textLibraryWindowMemberId.getText(), textLibraryWindowISBN.getText());
					textLibraryWindowMemberId.setText("");
					textLibraryWindowISBN.setText("");
					List<String> report = sc.getReport();

					if (!report.isEmpty()) {
						model.addRow(new Object[] { report.get(0), report.get(1), report.get(2), report.get(3),
								report.get(4), report.get(5), report.get(6), });

						report.clear();
					}

				}

			}
		});
		btnLibraryWindowCheckOut.setBounds(40, 151, 143, 53);
		contentPane.add(btnLibraryWindowCheckOut);

		JButton btnLibraryWindowBack = new JButton("Back");
		btnLibraryWindowBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SystemController.currentAuth.equals(Auth.BOTH)) {
					// LibrarianWindow.
					dispose();
					AdminWindow.getInstance().setVisible(true);
					return;
				}
				LogIn.INSTANCE.setVisible(true);
				dispose();
				textLibraryWindowMemberId.setText("");
				textLibraryWindowISBN.setText("");
			}
		});
		btnLibraryWindowBack.setBounds(10, 286, 117, 35);
		contentPane.add(btnLibraryWindowBack);

		JLabel lblNewLabel = new JLabel("Check Out and Return a Book");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(201, 11, 207, 29);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn.INSTANCE.setVisible(true);
				dispose();
				textLibraryWindowMemberId.setText("");
				textLibraryWindowISBN.setText("");
			}
		});
		btnNewButton.setBounds(715, 41, 99, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get ISBN from the form
				if (!textLibraryWindowISBN.getText().matches("\\d\\d-\\d\\d\\d\\d\\d")) {
					JOptionPane.showMessageDialog(null, "Enter a valid ISBN of format ##-#####.");
					return;
				}
				List<String> report = sc.getReport();
				String isbn = textLibraryWindowISBN.getText();
				String copyNum = textBookCopyNum.getText();
				sc.returnBookSimple(isbn, copyNum); 

				for (int i = 0; i < model.getRowCount(); i++) {
					String foundIsbn = model.getValueAt(i, 2).toString();
					String foundCopyNum = (String) model.getValueAt(i, 3);
					if (isbn.equals(foundIsbn) && copyNum.equals(foundCopyNum)) {
						model.removeRow(i);
						break;
					}
				}

			}
		});
		btnNewButton_1.setBounds(40, 223, 143, 45);
		contentPane.add(btnNewButton_1);

		comboBox.addItem("Select Operation");
		comboBox.addItem("Check Out");
		comboBox.addItem("Return");
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));

		if (comboBox.getSelectedItem().equals("Select Operation")) {
			textLibraryWindowMemberId.setEnabled(false);

			btnLibraryWindowCheckOut.setEnabled(false);
			btnNewButton_1.setEnabled(false);
			textLibraryWindowISBN.setEnabled(false);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBox.getSelectedItem().equals("Return")) {
					textLibraryWindowMemberId.setEnabled(false);
					btnLibraryWindowCheckOut.setEnabled(false);
					btnNewButton_1.setEnabled(true);

					return;
				} else if (comboBox.getSelectedItem().equals("Check Out")) {
					btnNewButton_1.setEnabled(false);
					textLibraryWindowMemberId.setEnabled(true);
					btnLibraryWindowCheckOut.setEnabled(true);
					textLibraryWindowISBN.setEnabled(true);
					return;
				} else {
					textLibraryWindowMemberId.setEnabled(false);
					btnLibraryWindowCheckOut.setEnabled(false);
					btnNewButton_1.setEnabled(false);
					textLibraryWindowISBN.setEnabled(false);
					return;
				}

			}
		});
		comboBox.setBounds(40, 80, 143, 51);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("Book Copy Num");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(487, 50, 117, 34);
		contentPane.add(lblNewLabel_1);

		textBookCopyNum = new JTextField();
		textBookCopyNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		JTextField jTextField = new JTextField();

		textBookCopyNum.setBounds(487, 81, 123, 29);
		contentPane.add(textBookCopyNum);
		textBookCopyNum.setColumns(10);

		// Printing checkout record
		JButton btnNewButton_2 = new JButton("Print");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println();
				System.out.println(
						"MemberId\t" + "Title\t\t  " + "ISBN\t   " + "  CopyNum\t" + "  Checkout Date\t" + " Due Date");
				for (int row = 0; row < model.getRowCount(); row++) {
					String memberId = model.getValueAt(row, 0).toString();

					if (model.getValueAt(row, 0).equals(textLibraryWindowMemberId.getText())) {

						System.out.println();
						System.out.print(textLibraryWindowMemberId.getText() + "\t\t");
						System.out.print(model.getValueAt(row, 1) + "\t ");
						System.out.print(model.getValueAt(row, 2) + "\t ");
						System.out.print(model.getValueAt(row, 3) + "\t ");
						System.out.print(model.getValueAt(row, 4) + "\t ");
						System.out.print(model.getValueAt(row, 5) + "\t");
						System.out.println();
						System.out.println();
					}
				}
				System.out.println("======================We love our customers=================================");

			}
		});
		btnNewButton_2.setBounds(715, 81, 99, 29);
		contentPane.add(btnNewButton_2);

		// searching by memberID
		textLibraryWindowMemberId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				List<String> report = sc.getReport();
				data = new ArrayList<String>();

				for (String s : report) {
					data.add(s);
				}

				while (!report.isEmpty()) {
					model.addRow(new Object[] { report.get(0), report.get(1), report.get(2), report.get(3),
							report.get(4), report.get(5), report.get(6), });
				}

				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(textLibraryWindowMemberId.getText()));

			}
		});

		// searching by ISBN
		textLibraryWindowISBN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				List<String> report = sc.getReport();
				data = new ArrayList<String>();

				for (String s : report) {
					data.add(s);
				}

				while (!report.isEmpty()) {
					model.addRow(new Object[] { report.get(0), report.get(1), report.get(2), report.get(3),
							report.get(4), report.get(5), report.get(6), });
				}

				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(textLibraryWindowISBN.getText()));

			}
		});
	}
}
