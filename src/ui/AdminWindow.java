package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.Book;
import business.BookCopy;
import business.LibraryMember;
import business.SystemController;
import dataaccess.Auth;
import dataaccess.DataAccessFacade;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import java.awt.Font;

public class AdminWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static AdminWindow INSTANCE;
	private JPanel contentPane;
	private JTable table;
	private JTable tableBook;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private int bookClicked = -1; 
	JButton btnNewButton = new JButton("Librarian");

	//private JscrollPane;//.setViewportView(table);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow frame = new AdminWindow();
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
	private AdminWindow() {
		if(SystemController.currentAuth.equals(Auth.ADMIN)) { 
			btnNewButton.setEnabled(false);
		}
		
		
		setTitle("Admin Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			SystemController sc = new SystemController();
			public void actionPerformed(ActionEvent e) {
				if(bookClicked == 0) {
				
				AddNewMember.INSTANCE.setVisible(true); //get the form to add member
				}
				else if(bookClicked == 1) {
					AddNewBook.INSTANCE.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Click either the \"Member\" button or the \"Book\" button first!");
				}
//				HashMap<String, LibraryMember> members = sc.MemberController().readMemberMap();
//				int r = 0;
//				for(String key: members.keySet()) {
//					table.setValueAt(members.get(key).getMemberId(),r, 0);
//					table.setValueAt(members.get(key).getFirstName(),r, 1);
//					table.setValueAt(members.get(key).getLastName(),r, 2);
//					table.setValueAt(members.get(key).getTelephone(),r, 3);
//					table.setValueAt(members.get(key).getAddress().getStreet(),r, 4);
//					table.setValueAt(members.get(key).getAddress().getCity(),r, 5);
//					table.setValueAt(members.get(key).getAddress().getState(),r, 6);
//					table.setValueAt(members.get(key).getAddress().getZip(),r, 7);
//					r++;
//				}
				
			}
		});
		add.setBounds(175, 58, 112, 54);
		contentPane.add(add);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditMember editForm = EditMember.getInstance();
				int selectedRow = table.getSelectedRow();
				if(selectedRow==-1) {
					JOptionPane.showMessageDialog(null, "Please select the members you want to update");
				}
				else {
					//String id = table.getValueAt(selectedRow, 0).toString();
					editForm.getTxtMemId().setText(table.getValueAt(selectedRow, 0).toString());
					editForm.getTxtMmName().setText(table.getValueAt(selectedRow, 1).toString());
					editForm.getTxtMemName2().setText(table.getValueAt(selectedRow, 2).toString());
					editForm.getTxtMemTel().setText(table.getValueAt(selectedRow, 3).toString());
					editForm.getTxtMemStreet().setText(table.getValueAt(selectedRow, 4).toString());
					editForm.getTxtMemCity().setText(table.getValueAt(selectedRow, 5).toString());
					editForm.getTxtMemState().setText(table.getValueAt(selectedRow, 6).toString());
					editForm.getTxtMemZip().setText(table.getValueAt(selectedRow, 7).toString());
					editForm.setVisible(true);
				}
			}
		});
		
		btnEdit.setBounds(297, 58, 92, 54);
		contentPane.add(btnEdit);
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SystemController sc=new SystemController();
				int selectedRow = table.getSelectedRow();
				if(selectedRow==-1) {
					JOptionPane.showMessageDialog(null, "Please select the members you want to delete");
					return;
				}
				String memberID = table.getValueAt(selectedRow, 0).toString();
				sc.MemberController().deleteMember(memberID);
				HashMap<String, LibraryMember> members = sc.MemberController().readMemberMap();
				int r = 0;
				for(String key: members.keySet()) {
					table.setValueAt(members.get(key).getMemberId(),r, 0);
					table.setValueAt(members.get(key).getFirstName(),r, 1);
					table.setValueAt(members.get(key).getLastName(),r, 2);
					table.setValueAt(members.get(key).getTelephone(),r, 3);
					table.setValueAt(members.get(key).getAddress().getStreet(),r, 4);
					table.setValueAt(members.get(key).getAddress().getCity(),r, 5);
					table.setValueAt(members.get(key).getAddress().getState(),r, 6);
					table.setValueAt(members.get(key).getAddress().getZip(),r, 7);
					r++;
				}
				table.clearSelection();
				JOptionPane.showMessageDialog(null, "Deleted?!");	
				
			}
		});
		btnDelete.setBounds(399, 58, 112, 54);
		contentPane.add(btnDelete);
		JButton btnMember = new JButton("Members");
		btnMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookClicked = 0;
				SystemController sc = new SystemController();
				HashMap<String, LibraryMember> members = sc.MemberController().readMemberMap();
				int r = 0;
				for(String key: members.keySet()) {
					table.setValueAt(members.get(key).getMemberId(),r, 0);
					table.setValueAt(members.get(key).getFirstName(),r, 1);
					table.setValueAt(members.get(key).getLastName(),r, 2);
					table.setValueAt(members.get(key).getTelephone(),r, 3);
					table.setValueAt(members.get(key).getAddress().getStreet(),r, 4);
					table.setValueAt(members.get(key).getAddress().getCity(),r, 5);
					table.setValueAt(members.get(key).getAddress().getState(),r, 6);
					table.setValueAt(members.get(key).getAddress().getZip(),r, 7);
					r++;
				}
				
			}
		});
		btnMember.setBounds(28, 122, 122, 180);
		contentPane.add(btnMember);
		
		JButton btnBook = new JButton("Books");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookClicked = 1;
//				tableBook.setVisible(true);
//				scrollPane_1.setVisible(true);
//				table.setVisible(false);
//				scrollPane.setVisible(false);
				SystemController sc = new SystemController();
				DataAccessFacade da = sc.BookController();
				HashMap<String, Book> books = da.readBooksMap();
				int r = 0;
				int numAvailableCopies = 0;
				Book thisBook = null;
				BookCopy[] bc = null;
				for(String isbn: books.keySet()) {
					thisBook = books.get(isbn);
					 bc = thisBook.getCopies();
					numAvailableCopies = 0;
					for(int i = 0; i<bc.length; i++) {
						if(bc[i].isAvailable()) numAvailableCopies++;
					}
					tableBook.setValueAt(thisBook.getNumCopies(),r, 3);
					tableBook.setValueAt(thisBook.getTitle(),r, 0);
					tableBook.setValueAt(thisBook.getIsbn(),r, 1);
					tableBook.setValueAt(thisBook.getAuthors().toString(),r, 2);
					tableBook.setValueAt(thisBook.getNumCopies(),r, 3);
					tableBook.setValueAt(numAvailableCopies,r, 4);
					tableBook.setValueAt(thisBook.getMaxCheckoutLength(),r, 5);
					r++;
				}
			}
		});
		btnBook.setBounds(28, 334, 122, 85);
		contentPane.add(btnBook);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage.INSTANCE.setVisible(true);
			    dispose();
			}
		});
		btnLogout.setBounds(534, 6, 99, 54);
		contentPane.add(btnLogout);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(177, 122, 456, 180);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
					"id", "fName", "lName", "tel", "street", "city", "state", "Zip"
			}
		));
		
		table.setVisible(true);
		scrollPane.setVisible(true);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(177, 334, 456, 192);
		contentPane.add(scrollPane_1);
		
		tableBook = new JTable();
		scrollPane_1.setViewportView(tableBook);
		tableBook.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Title", "ISBN", "Authors", "Copies", "available copies", "maxLen"
			}
			
		));
		tableBook.setVisible(true);
		scrollPane_1.setVisible(true);
		 
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LibrarianWindow.INSTANCE.setVisible(true);
				//dispose();
				
				
				
			}
		});
		btnNewButton.setBounds(28, 58, 122, 54);
		contentPane.add(btnNewButton);
		
		JButton btnAddBookCopy = new JButton("Add Book Copy");
		btnAddBookCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewBookCopy.INSTANCE.setVisible(true);
			}
		});
		btnAddBookCopy.setBounds(28, 427, 122, 99);
		contentPane.add(btnAddBookCopy);
		
		JLabel lblNewLabel = new JLabel("LMS Admin Window");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(175, 10, 299, 38);
		contentPane.add(lblNewLabel);
	}
	public static AdminWindow getInstance() {
		if(INSTANCE!=null) return INSTANCE;
		return new AdminWindow();
	}
	
	public JTable getTable() {
		return table;
	}
}
