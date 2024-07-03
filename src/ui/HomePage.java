//package ui;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//
//import ui.AboutPage;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class HomePage extends JFrame {
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//	public static final HomePage INSTANCE= new HomePage();
//
//	//JFrame frame;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					//HomePage homepage = new HomePage();
//
//					HomePage.INSTANCE.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	private HomePage() {
//		//JFrame frame = new JFrame();
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//
//
//
//
//
//
//
//		setBounds(100, 100, 450, 300);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(700, 500);
//		setTitle("Home page");
//
//		JMenuBar menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//
//		JMenu mnNewMenu = new JMenu("Options");
//		menuBar.add(mnNewMenu);
//
//		JMenuItem mntmNewMenuItem = new JMenuItem("About");
//		mntmNewMenuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				AboutPage.INSTANCE.setVisible(true);
//				dispose();
//			}
//		});
//		mnNewMenu.add(mntmNewMenuItem);
//
//		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
//		mntmNewMenuItem_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		mnNewMenu.add(mntmNewMenuItem_1);
//		getContentPane().setLayout(null);
//
//		JLabel lblNewLabel = new JLabel("Library Management System");
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
//		lblNewLabel.setBounds(212, 106, 211, 37);
//		getContentPane().add(lblNewLabel);
//
//		JButton btnNewButton = new JButton("Login");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				LogIn.INSTANCE.setVisible(true);
//
//				dispose();
//			}
//		});
//		btnNewButton.setBounds(543, 21, 89, 37);
//		getContentPane().add(btnNewButton);
//
//		JLabel lblGroupLibraryManagement = new JLabel("Library Management System");
//		lblGroupLibraryManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
//		lblGroupLibraryManagement.setBounds(147, 47, 367, 37);
//		getContentPane().add(lblGroupLibraryManagement);
//
//		JLabel lblImage = new JLabel("");
//		lblImage.setBounds(171, 154, 489, 274);
//
//
//
//
//		// Load the image
//        Image imageIcon = new ImageIcon( this.getClass().getResource("image.jpeg")).getImage(); // Replace with your image path
//
//        // Create a JLabel to display the image
//       // JLabel imageLabel = new JLabel(imageIcon);
//
//        // Add the label to the frame
//       // frame.add(imageLabel);
//
//        lblImage.setIcon(new ImageIcon( imageIcon));
//        // Center the image on the label (optional)
//        lblImage.setHorizontalAlignment(JLabel.CENTER);
//        lblImage.setVerticalAlignment(JLabel.CENTER);
//
//
//
//
//
//		getContentPane().add(lblImage);
//
//	}
//}



//------------

package ui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class HomePage extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final HomePage INSTANCE = new HomePage();
	private Image backgroundImage;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage.INSTANCE.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private HomePage() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setTitle("Home page");

		backgroundImage = new ImageIcon(this.getClass().getResource("image.jpeg")).getImage();

		CustomPanel backgroundPanel = new CustomPanel();
		backgroundPanel.setLayout(null);
		setContentPane(backgroundPanel);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Options");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("About");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutPage.INSTANCE.setVisible(true);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JLabel lblGroupLibraryManagement = new JLabel("Library Management System");
		lblGroupLibraryManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGroupLibraryManagement.setBounds(147, 47, 367, 37);
		backgroundPanel.add(lblGroupLibraryManagement);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn.INSTANCE.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(543, 21, 89, 37);
		backgroundPanel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(212, 106, 211, 37);
		backgroundPanel.add(lblNewLabel);
	}

	private class CustomPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
