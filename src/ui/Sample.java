package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Sample extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sample frame = new Sample();
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
	public Sample() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		textField.setBounds(37, 66, 128, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		model = new DefaultTableModel();
		table=new JTable();
		
		populateTable();
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(54, 295, 89, 23);
		contentPane.add(btnNewButton);
	}
	

	public void populateTable() {
		String columns[]= {"id","Name","age"};
		String data[][]= {{"101","Alley","2000"},{"102","Jo","2000"},{"103","Bob","2000"},{"104","Alley","2000"}};
		DefaultTableModel model=new DefaultTableModel(data,columns);
		table.setModel(model);
	}
	
	public void filter(String query) {
		TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(query));
	}
	
	
	
}
