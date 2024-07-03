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
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddNewMember extends JFrame{
	public static final AddNewMember INSTANCE = new AddNewMember();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMemName1;
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
					//AddNewMember frame = new AddNewMember();
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
	private AddNewMember() {
		setTitle("Add New Member");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 518, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Add New Member in the system");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(49, 10, 279, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblMemName1 = new JLabel("First Name");
		lblMemName1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemName1.setBounds(49, 116, 91, 33);
		contentPane.add(lblMemName1);
		
		JLabel lblMemId = new JLabel("Member ID");
		lblMemId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemId.setBounds(49, 73, 82, 33);
		contentPane.add(lblMemId);
		
		JLabel lblMemStreet = new JLabel("Street");
		lblMemStreet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemStreet.setBounds(49, 259, 71, 33);
		contentPane.add(lblMemStreet);
		
		JLabel lblMemCity = new JLabel("City");
		lblMemCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemCity.setBounds(49, 302, 58, 33);
		contentPane.add(lblMemCity);
		
		JLabel lblMemState = new JLabel("State");
		lblMemState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemState.setBounds(49, 345, 71, 33);
		contentPane.add(lblMemState);
		
		JLabel lblMemZip = new JLabel("Zip Code");
		lblMemZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemZip.setBounds(38, 388, 71, 33);
		contentPane.add(lblMemZip);
		
		JLabel lblMemName2 = new JLabel("Last Name");
		lblMemName2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemName2.setBounds(49, 159, 71, 33);
		contentPane.add(lblMemName2);
		
		txtMemName1 = new JTextField();
		txtMemName1.setBounds(150, 118, 234, 33);
		contentPane.add(txtMemName1);
		txtMemName1.setColumns(10);
		
		txtMemId = new JTextField();
		txtMemId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMemId.setText(getMemberID());
			}
		});
		txtMemId.setEditable(false);
		txtMemId.setColumns(10);
		txtMemId.setBounds(150, 75, 234, 33);
		contentPane.add(txtMemId);
		
		txtMemStreet = new JTextField();
		txtMemStreet.setColumns(10);
		txtMemStreet.setBounds(150, 261, 234, 33);
		contentPane.add(txtMemStreet);
		
		txtMemCity = new JTextField();
		txtMemCity.setColumns(10);
		txtMemCity.setBounds(150, 304, 234, 33);
		contentPane.add(txtMemCity);
		
		txtMemState = new JTextField();
		txtMemState.setColumns(10);
		txtMemState.setBounds(150, 347, 234, 33);
		contentPane.add(txtMemState);
		
		txtMemZip = new JTextField();
		txtMemZip.setColumns(10);
		txtMemZip.setBounds(150, 390, 234, 33);
		contentPane.add(txtMemZip);
		
		txtMemName2 = new JTextField();
		txtMemName2.setColumns(10);
		txtMemName2.setBounds(150, 161, 234, 33);
		contentPane.add(txtMemName2);
		
		JButton btnMemBack = new JButton("<<Back");
		btnMemBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "You're supposed to be in Admin main window now");
				AdminWindow.getInstance().setVisible(true);
				dispose();
			}
		});
		btnMemBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMemBack.setBounds(49, 489, 125, 33);
		contentPane.add(btnMemBack);
		
		JButton btnMemAdd = new JButton("Add Member");
		btnMemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SystemController sc=new SystemController();
				//txtMemId.setText(getMemberID());
				String fName = txtMemName1.getText();
				String lName = txtMemName2.getText();
				String tel = txtMemTel.getText(); // we don't need to check isEmpty for this field
				String id = txtMemId.getText();
				String street = txtMemStreet.getText();
				String city = txtMemCity.getText();
				String state = txtMemState.getText();
				String zip = txtMemZip.getText();
				//String country = txtMemcountry.getText(); // we do not need country
				if(fName.isEmpty()||id.isEmpty()||street.isEmpty()||city.isEmpty()||state.isEmpty()||zip.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields");
				}
				else {
					List<String> memberId = sc.allMemberIds();
					Address adress = new Address(street, city, state, zip);
					LibraryMember member = new LibraryMember(id, fName, lName, tel, adress);
					if(!memberId.contains(id)){
						sc.MemberController().saveNewMember(member);
						JOptionPane.showMessageDialog(null, id+" is added in the system sucessfully");
						txtMemName1.setText("");
						txtMemTel.setText("");
						txtMemId.setText("");
						txtMemStreet.setText("");
						txtMemCity.setText("");
						txtMemState.setText("");
						txtMemZip.setText("");
						txtMemName2.setText("");
						AdminWindow admin = AdminWindow.getInstance();
//						//ystemController sc = new SystemController();
//						HashMap<String, LibraryMember> members = sc.MemberController().readMemberMap();
//						int r = 0;
//						for(String key: members.keySet()) {
//							admin.getTable().setValueAt(members.get(key).getMemberId(),r, 0);
//							admin.getTable().setValueAt(members.get(key).getFirstName(),r, 1);
//							admin.getTable().setValueAt(members.get(key).getLastName(),r, 2);
//							admin.getTable().setValueAt(members.get(key).getTelephone(),r, 3);
//							admin.getTable().setValueAt(members.get(key).getAddress().getStreet(),r, 4);
//							admin.getTable().setValueAt(members.get(key).getAddress().getCity(),r, 5);
//							admin.getTable().setValueAt(members.get(key).getAddress().getState(),r, 6);
//							admin.getTable().setValueAt(members.get(key).getAddress().getZip(),r, 7);
//							r++;
//						}
					}
					else {
						JOptionPane.showMessageDialog(null, id+" is already in the system");
					}
				}
				
			}
		});
		btnMemAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMemAdd.setBounds(150, 433, 234, 33);
		contentPane.add(btnMemAdd);
		
		JLabel lblMemTel = new JLabel("Tel");
		lblMemTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMemTel.setBounds(49, 202, 71, 33);
		contentPane.add(lblMemTel);
		
		txtMemTel = new JTextField();
		txtMemTel.setColumns(10);
		txtMemTel.setBounds(150, 204, 234, 33);
		contentPane.add(txtMemTel);
	}
	
	public String getMemberID() {
		int min = 1000;
		int max = 9999;
		int randomNum = min + (int)(Math.random() * ((max - min) + 1));
		return ""+randomNum;
	}
}
