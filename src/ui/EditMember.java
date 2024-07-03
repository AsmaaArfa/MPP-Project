package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.Address;
import business.LibraryMember;
import business.SystemController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditMember extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final EditMember INSTANCE = new EditMember();
	private JPanel contentPane;
	private JTextField txtMemName;
	private JTextField txtMemId;
	private JTextField txtMemStreet;
	private JTextField txtMemCity;
	private JTextField txtMemState;
	private JTextField txtMemZip;
	private JTextField txtMemName2;
	private JTextField txtMemTel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditMember frame = new EditMember();
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
	public EditMember() {
		setTitle("Edit Existing Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 518, 619);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Member in the system");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(135, 10, 275, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblMemName1 = new JLabel("First Name");
		lblMemName1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemName1.setBounds(38, 130, 71, 33);
		contentPane.add(lblMemName1);
		
		JLabel lblMemId = new JLabel("Member ID");
		lblMemId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemId.setBounds(38, 87, 82, 33);
		contentPane.add(lblMemId);
		
		JLabel lblMemStreet = new JLabel("Street");
		lblMemStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemStreet.setBounds(38, 269, 71, 33);
		contentPane.add(lblMemStreet);
		
		JLabel lblMemCity = new JLabel("City");
		lblMemCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemCity.setBounds(38, 317, 58, 33);
		contentPane.add(lblMemCity);
		
		JLabel lblMemState = new JLabel("State");
		lblMemState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemState.setBounds(38, 362, 58, 33);
		contentPane.add(lblMemState);
		
		JLabel lblMemZip = new JLabel("Zip Code");
		lblMemZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemZip.setBounds(38, 418, 71, 33);
		contentPane.add(lblMemZip);
		
		JLabel lblMemName2 = new JLabel("Last Name");
		lblMemName2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemName2.setBounds(38, 173, 71, 33);
		contentPane.add(lblMemName2);
		
		txtMemName = new JTextField();
		txtMemName.setBounds(150, 132, 234, 33);
		contentPane.add(txtMemName);
		txtMemName.setColumns(10);
		
		txtMemId = new JTextField();
		txtMemId.setEditable(false);
		txtMemId.setColumns(10);
		txtMemId.setBounds(150, 85, 234, 33);
		contentPane.add(txtMemId);
		
		txtMemStreet = new JTextField();
		txtMemStreet.setColumns(10);
		txtMemStreet.setBounds(150, 271, 234, 33);
		contentPane.add(txtMemStreet);
		
		txtMemCity = new JTextField();
		txtMemCity.setColumns(10);
		txtMemCity.setBounds(150, 319, 234, 33);
		contentPane.add(txtMemCity);
		
		txtMemState = new JTextField();
		txtMemState.setColumns(10);
		txtMemState.setBounds(150, 364, 234, 33);
		contentPane.add(txtMemState);
		
		txtMemZip = new JTextField();
		txtMemZip.setColumns(10);
		txtMemZip.setBounds(150, 420, 234, 33);
		contentPane.add(txtMemZip);
		
		txtMemName2 = new JTextField();
		txtMemName2.setColumns(10);
		txtMemName2.setBounds(150, 175, 234, 33);
		contentPane.add(txtMemName2);
		
		JButton btnMemBack = new JButton("<<Back");
		btnMemBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminWindow.getInstance().setVisible(true);
				dispose();
			}
		});
		btnMemBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMemBack.setBounds(38, 517, 136, 33);
		contentPane.add(btnMemBack);
		
		JButton btnMemEdit = new JButton("Edit Member");
		btnMemEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SystemController sc=new SystemController();
				List<String> memberId = sc.allMemberIds();
				String fName = txtMemName.getText();
				String lName = txtMemName2.getText();
				String tel = txtMemTel.getText(); // we don't need to check isEmpty for this field
				String id = txtMemId.getText();
				String street = txtMemStreet.getText();
				String city = txtMemCity.getText();
				String state = txtMemState.getText();
				String zip = txtMemZip.getText();
				
				if(fName.isEmpty()||id.isEmpty()||street.isEmpty()||city.isEmpty()||state.isEmpty()||zip.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields");
					return;
				}
				if(!memberId.contains(id)) {
					JOptionPane.showMessageDialog(null, "You're trying to edit Member ID! You may add new member");
					return;
				}
				else {
					//List<String> memberId = sc.allMemberIds();
					Address adress = new Address(street, city, state, zip);
					LibraryMember member = new LibraryMember(id, fName, lName, tel, adress);
					sc.MemberController().saveNewMember(member);
					
					txtMemName.setText("");
					txtMemTel.setText("");
					txtMemId.setText("");
					txtMemStreet.setText("");
					txtMemCity.setText("");
					txtMemState.setText("");
					txtMemZip.setText("");
					txtMemName2.setText("");
					JOptionPane.showMessageDialog(null, id+" is updated in the system sucessfully");
				}
				
				
			}
		});
		btnMemEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMemEdit.setBounds(150, 463, 234, 33);
		contentPane.add(btnMemEdit);
		
		JLabel lblMemTel = new JLabel("Tel.");
		lblMemTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemTel.setBounds(38, 226, 71, 33);
		contentPane.add(lblMemTel);
		
		txtMemTel = new JTextField();
		txtMemTel.setColumns(10);
		txtMemTel.setBounds(150, 228, 234, 33);
		contentPane.add(txtMemTel);
	}

	public static EditMember getInstance() {
		if(INSTANCE!=null) return INSTANCE;
		return new EditMember();
	}

	public JTextField getTxtMmName() {
		return txtMemName;
	}
	public JTextField getTxtMemId() {
		return txtMemId;
	}
	public JTextField getTxtMemTel() {
		return txtMemTel;
	}
	public JTextField getTxtMemStreet() {
		return txtMemStreet;
	}
	public JTextField getTxtMemCity() {
		return txtMemCity;
	}
	public JTextField getTxtMemState() {
		return txtMemState;
	}
	public JTextField getTxtMemZip() {
		return txtMemZip;
	}
	public JTextField getTxtMemName2() {
		return txtMemName2;
	}

}
