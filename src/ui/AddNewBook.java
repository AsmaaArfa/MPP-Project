//private List<Author> authors = new ArrayList<Author>();

package ui;

import business.Author;

import java.util.ArrayList;
import java.util.List;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Address;
import business.Author;
import business.LibrarySystemException;
import business.LoginException;
import business.SystemController;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AddNewBook extends JFrame {

	public static final AddNewBook INSTANCE = new AddNewBook();
	private List<Author> authors = new ArrayList<Author>();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textAddNewBookTitle;
	private JTextField textAddNewBookISBN;
	private JTextArea textAreaAuthor;
	private JTextField textFieldCheckoutDur;
	private JTextField textFieldNumCopies;

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

	private AddNewBook() {
		initialize();
	}


	private void initialize() {
		setTitle("Add New Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddNewBookTitle = new JLabel("Title");
		lblAddNewBookTitle.setBounds(47, 29, 46, 14);
		contentPane.add(lblAddNewBookTitle);

		JLabel lblAddNewBookISBN = new JLabel("ISBN");
		lblAddNewBookISBN.setBounds(47, 67, 46, 14);
		contentPane.add(lblAddNewBookISBN);

		textAddNewBookTitle = new JTextField();
		textAddNewBookTitle.setBounds(122, 29, 156, 20);
		contentPane.add(textAddNewBookTitle);
		textAddNewBookTitle.setColumns(10);

		textAddNewBookISBN = new JTextField();
		textAddNewBookISBN.setBounds(122, 64, 156, 20);
		contentPane.add(textAddNewBookISBN);
		textAddNewBookISBN.setColumns(10);

		JLabel lblAddNewBookID = new JLabel("Author(s)");
		lblAddNewBookID.setBounds(47, 107, 54, 14);
		contentPane.add(lblAddNewBookID);

		JButton btnAddNewBookAddAuthor = new JButton("Add Author(s)");
		btnAddNewBookAddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAuthor.INSTANCE.setVisible(true);
			}
		});
		btnAddNewBookAddAuthor.setBounds(282, 122, 148, 23);
		contentPane.add(btnAddNewBookAddAuthor);

		JCheckBox chckbxAnonymousAuthor = new JCheckBox("Anonymous Author");
		chckbxAnonymousAuthor.setBounds(282, 100, 146, 23);
		contentPane.add(chckbxAnonymousAuthor);

		JButton btnAddNewBook = new JButton("Add book");

		btnAddNewBook.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 String bookTitle = textAddNewBookTitle.getText();
				 String bookISBN = textAddNewBookISBN.getText();
				 String checkoutDur = textFieldCheckoutDur.getText();
				 String numOfCopies = textFieldNumCopies.getText();

				 if(bookTitle == null || bookISBN == null || bookTitle.isEmpty() || bookISBN.isEmpty()) {
	    				JOptionPane.showMessageDialog(null, "Enter both title and ISBN for this book!");
	    			} else if(textAreaAuthor.getText().isEmpty() && !chckbxAnonymousAuthor.isSelected()) {
	    				JOptionPane.showMessageDialog(null, "Add author(s) or select \"anonymous author\".");
	    			}else if(checkoutDur.isBlank() || !checkoutDur.equals("7") && !checkoutDur.equals("21")) {
	    				JOptionPane.showMessageDialog(null, "Need to express a max checkout duration (in days) as either 7 or 21.");
	    			}else if(numOfCopies.isBlank() || numOfCopies.equals("0")|| !numOfCopies.matches("\\d+")) {
	    				JOptionPane.showMessageDialog(null, "Enter a valid number of copies.");
	    			}else if(!bookISBN.matches("\\d\\d-\\d\\d\\d\\d\\d")) {
	    				JOptionPane.showMessageDialog(null, "Enter a valid ISBN of format ##-#####.");
	    			}
	    			else {
	    				SystemController sc = new SystemController();
	    				int duration;
	    				if(checkoutDur == "7") {
	    					duration = 7;
	    				}else {
	    					duration = 21;
	    				}
	    				int copies = Integer.parseInt(numOfCopies);

	    			try {
	    				if(chckbxAnonymousAuthor.isSelected()) {
	    					Address anonAdd = new Address("", "", "", "");
	    					Author anon = new Author("Anonymous", "", "", anonAdd, "");
	    					authors.add(anon);
	    				}
	    				// implement the case that the new book can be added
						sc.addNewBook(bookTitle, bookISBN, duration, authors, copies);
						JOptionPane.showMessageDialog(null, "Successfully added " + copies + " copies of " + bookTitle + " with ISBN: " + bookISBN);
						//LibrarySystem.hideAllWindows();
		    			//HomePage.INSTANCE.setVisible(true);
	    			}
	    			catch (LibrarySystemException e1) {
						JOptionPane.showMessageDialog(null,"This book already exists in the library collection. "
								+ "Perhaps you'd rather add a copy of this book.");
						//LibrarySystem.hideAllWindows();
						}
	    			AddNewBook.INSTANCE.dispose();
	    			AdminWindow.getInstance().setVisible(true);
	    			}
			 }
		});
		btnAddNewBook.setBounds(187, 218, 89, 23);
		contentPane.add(btnAddNewBook);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 98, 156, 47);
		contentPane.add(scrollPane);

		textAreaAuthor = new JTextArea();
		textAreaAuthor.setEditable(false);
		scrollPane.setViewportView(textAreaAuthor);

		JLabel lblCheckOutDuration = new JLabel("Max Checkout Duration");
		lblCheckOutDuration.setBounds(44, 156, 156, 23);
		contentPane.add(lblCheckOutDuration);

		textFieldCheckoutDur = new JTextField();
		textFieldCheckoutDur.setBounds(232, 159, 46, 20);
		contentPane.add(textFieldCheckoutDur);
		textFieldCheckoutDur.setColumns(10);

		JLabel lblNumOfCopies = new JLabel("Number of copies");
		lblNumOfCopies.setBounds(44, 190, 116, 14);
		contentPane.add(lblNumOfCopies);

		textFieldNumCopies = new JTextField();
		textFieldNumCopies.setBounds(232, 187, 46, 20);
		contentPane.add(textFieldNumCopies);
		textFieldNumCopies.setColumns(10);

		JButton btnBackFromAddBook = new JButton("<<-- Back");
		btnBackFromAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminWindow.getInstance().setVisible(true);
				AddNewBook.INSTANCE.dispose();
			}
		});
		btnBackFromAddBook.setBounds(44, 218, 89, 23);
		contentPane.add(btnBackFromAddBook);
	}

	void setAuthorText(List<String> names) {
		String authors = "";
		for(String name: names) {
			authors += name + "\n";
		}
		textAreaAuthor.setText(authors);
	}
	List<Author> getAuthors(){
		return authors;
	}
}
