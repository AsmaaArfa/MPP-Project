package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import business.LoginException;
import business.SystemController;
import dataaccess.Auth;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

	public static final LogIn INSTANCE = new LogIn();
	private JTextField textUserId;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				
				try {
					LogIn.INSTANCE.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Login Window");
		setBounds(100, 100, 464, 330);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setBounds(29, 54, 46, 14);
		getContentPane().add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(29, 85, 64, 14);
		getContentPane().add(lblPassword);
		
		textUserId = new JTextField();
		textUserId.setBounds(122, 51, 138, 20);
		getContentPane().add(textUserId);
		textUserId.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				/*
				 * 
				 * accept the userId and password from the GUI, 
				 * case1-> check if the input fields are not empty.
				 * case2-> check if the user credentials exist in the file
				 * 
				*/
				
				
				
//				String userId=textUserId.getText();
//				//String pass=passwordField.getText();
//				char[] password=passwordField.getPassword();
//				if(userId.isEmpty() || password.length==0 || password==null) {
//					JOptionPane.showMessageDialog(null, "please fill all fields! ");
//				}
				//String pass=new String(password);
				SystemController sc=new SystemController();
				
				// /********************Sahana's task***********************//
//				try {
//					sc.login(userId, pass);
//					
//				} catch (LoginException e1) {
//					
//				}
				
				
				
				if(textUserId.getText() == null || passwordField.getText() == null || textUserId.getText().isEmpty() || passwordField.getText().isEmpty()) {
    				JOptionPane.showMessageDialog(null, "Enter both username and password!");
    				textUserId.setText("");
    				passwordField.setText("");
					//LibrarySystem.hideAllWindows();
					LogIn.INSTANCE.setVisible(true);
					return;
    			}else {
    				//SystemController sc = new SystemController();
    			try {
					sc.login(textUserId.getText(), passwordField.getText());
					//LibrarySystem.hideAllWindows();
	    			//HomePage.INSTANCE.setVisible(true);
					
					Auth auth=SystemController.currentAuth;
					if (auth != null) {
			            switch (auth) {
			                case LIBRARIAN:
			                	dispose();
			                	LibrarianWindow.INSTANCE.setVisible(true);
			                	textUserId.setText("");
								passwordField.setText("");
			                   // System.out.println("Librarian logged in.");
			                    break;
			                case ADMIN:
			                	dispose();
			                	AdminWindow.getInstance().setVisible(true);
			                	textUserId.setText("");
								passwordField.setText("");
								
			                    //System.out.println("Administrator logged in.");
			                    break; 
			                case BOTH:
			                	dispose();
			                	AdminWindow.getInstance().setVisible(true);
			                	textUserId.setText("");
								passwordField.setText("");
			                    //System.out.println("User with both roles logged in.");
			                    break;
			            }
			        } else {  
			            System.out.println("Authentication failed.");
			        }
					
					
					
					
				}
    			catch (LoginException e1) {
					JOptionPane.showMessageDialog(null,"Incorrect username or password. Please try again.");
					textUserId.setText("");
					passwordField.setText("");
					//LibrarySystem.hideAllWindows();
					LogIn.INSTANCE.setVisible(true);
					}
    			}
			 
			}});
		btnLogin.setBounds(186, 172, 89, 23);
		getContentPane().add(btnLogin);
		
		JButton btnBackLogin = new JButton("Back");
		btnBackLogin.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent e) {
		setVisible(false);

		HomePage.INSTANCE.setVisible(true);
	}
});
		btnBackLogin.setBounds(66,172,89,23);getContentPane().add(btnBackLogin);

passwordField=new JPasswordField();passwordField.setBounds(122,82,138,20);getContentPane().add(passwordField);}}
