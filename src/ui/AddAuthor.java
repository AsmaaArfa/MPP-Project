package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Address;
import business.Author;
//import business.LibrarySystemException;
import business.SystemController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AddAuthor extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final AddAuthor INSTANCE = new AddAuthor();
	// private static final AddNewBook BOOK_INSTANCE = new AddNewBook();
	private JPanel contentPane;
	private JTextField textAddAuthorLName;
	private JTextField textAddAuthorPhoneNum;
	private JTextField textAddAuthorFName;
	private List<String> authorNames;
	private JTextArea textAreaAuthorBio;
	private JTextField textFieldStreetAddress;
	private JTextField textFieldCity;
	private JTextField textFieldState;
	private JTextField textFieldZip;
	private JLabel lblCityStateZip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAuthor frame = new AddAuthor();
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
	private AddAuthor() {
		setTitle("Add Author");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 534, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAddAuthorFName = new JLabel("First Name");
		lblAddAuthorFName.setBounds(54, 56, 79, 14);
		contentPane.add(lblAddAuthorFName);

		textAddAuthorFName = new JTextField();
		textAddAuthorFName.setColumns(10);
		textAddAuthorFName.setBounds(202, 51, 192, 25);
		contentPane.add(textAddAuthorFName);

		JLabel lblAddAuthorLName = new JLabel("Last Name");
		lblAddAuthorLName.setBounds(54, 87, 79, 14);
		contentPane.add(lblAddAuthorLName);

		textAddAuthorLName = new JTextField();
		textAddAuthorLName.setBounds(202, 82, 192, 25);
		contentPane.add(textAddAuthorLName);
		textAddAuthorLName.setColumns(10);

		JLabel lblAddAuthorPhoneNum = new JLabel("Phone Number");
		lblAddAuthorPhoneNum.setBounds(54, 118, 115, 14);
		contentPane.add(lblAddAuthorPhoneNum);

		textAddAuthorPhoneNum = new JTextField();
		textAddAuthorPhoneNum.setColumns(10);
		textAddAuthorPhoneNum.setBounds(202, 113, 192, 25);
		contentPane.add(textAddAuthorPhoneNum);

		JLabel lblAddAuthorStreetAddress = new JLabel("Street Address");
		lblAddAuthorStreetAddress.setBounds(54, 149, 87, 14);
		contentPane.add(lblAddAuthorStreetAddress);

		JLabel lblAddAuthorBio = new JLabel("Bio");
		lblAddAuthorBio.setBounds(54, 215, 46, 14);
		contentPane.add(lblAddAuthorBio);

		JButton btnAddAuthor = new JButton("Add");
		authorNames = new ArrayList<String>();
		btnAddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = textAddAuthorFName.getText();
				String lname = textAddAuthorLName.getText();
				String phoneNum = textAddAuthorPhoneNum.getText();
				String street = textFieldStreetAddress.getText();
				String city = textFieldCity.getText();
				String state = textFieldState.getText();
				String zip = textFieldZip.getText();
				String bio = textAreaAuthorBio.getText();

				if (fname.isBlank() || lname.isBlank() || phoneNum.isBlank() || street.isBlank() || city.isBlank()
						|| state.isBlank() || zip.isBlank() || bio.isBlank()) {
					JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
				} else if (!phoneNum.matches("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid phone number.");
				} else if (state.length() != 2 || !state.matches("[A-Z][A-Z]")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid state.");
				} else if (!zip.matches("\\d+")) {
					JOptionPane.showMessageDialog(null, "Please enter a valid zip code.");
				} else {
					Address add = new Address(street, city, state, zip);
					Author a = new Author(fname, lname, phoneNum, add, bio);
					authorNames.add(fname + " " + lname);
					AddNewBook.INSTANCE.getAuthors().add(a);
					AddNewBook.INSTANCE.setAuthorText(authorNames);

					textAddAuthorFName.setText("");
					textAddAuthorLName.setText("");
					textAddAuthorPhoneNum.setText("");
					textFieldStreetAddress.setText("");
					textFieldCity.setText("");
					textFieldState.setText("");
					textFieldZip.setText("");
					textAreaAuthorBio.setText("");
				}
			}
		});
		btnAddAuthor.setBounds(202, 263, 115, 33);
		contentPane.add(btnAddAuthor);

		textFieldStreetAddress = new JTextField();
		textFieldStreetAddress.setBounds(202, 146, 192, 20);
		contentPane.add(textFieldStreetAddress);
		textFieldStreetAddress.setColumns(10);

		textFieldCity = new JTextField();
		textFieldCity.setBounds(202, 178, 96, 20);
		contentPane.add(textFieldCity);
		textFieldCity.setColumns(10);

		textFieldState = new JTextField();
		textFieldState.setBounds(308, 177, 40, 20);
		contentPane.add(textFieldState);
		textFieldState.setColumns(10);
		textFieldZip = new JTextField();
		textFieldZip.setBounds(354, 178, 40, 20);
		contentPane.add(textFieldZip);
		textFieldZip.setColumns(10);

		lblCityStateZip = new JLabel("City, State, Zipcode");
		lblCityStateZip.setBounds(54, 181, 126, 14);
		contentPane.add(lblCityStateZip);

		textAreaAuthorBio = new JTextArea();
		textAreaAuthorBio.setBounds(202, 210, 118, 22);
		contentPane.add(textAreaAuthorBio);
	}

}
