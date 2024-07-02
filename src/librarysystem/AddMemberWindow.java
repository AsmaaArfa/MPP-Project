package librarysystem;

import business.Address;
import business.SystemController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberWindow extends JPanel implements LibWindow {
    public static final AddMemberWindow INSTANCE = new AddMemberWindow();

    private JTextField memberIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField telephoneField;
    private JButton submitButton;
    private boolean isInitialized = false;

    private AddMemberWindow() {}

    private String getMemberId(){return memberIdField.getText();}
    private String getFirstName(){return firstNameField.getText();}
    private String getLastName(){return lastNameField.getText();}
    private String getStreet() {return streetField.getText();}
    private String getCity() {return cityField.getText();}
    private String getState() {return stateField.getText();}
    private String getZipCode() {return zipField.getText();}
    private String getTelephone() {return telephoneField.getText();}

    private void AddMemberButtonListener(JButton btn){
        btn.addActionListener(evn ->{
            // Handle the submit action here
            SystemController systemController = new SystemController();
            systemController.AddMember(getMemberId(), getFirstName(), getLastName(), getTelephone(),
                    new Address(getStreet(), getCity(), getState(), getZipCode()));
            JOptionPane.showMessageDialog(this,"Member added successfully");
    });
}
    private void definePanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel memberIdLabel = new JLabel("Member ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(memberIdLabel, gbc);

        memberIdField = new JTextField(20);
        gbc.gridx = 1;
        add(memberIdField, gbc);

        JLabel firstNameLabel = new JLabel("First Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(firstNameLabel, gbc);

        firstNameField = new JTextField(20);
        gbc.gridx = 1;
        add(firstNameField, gbc);

        JLabel lastNameLabel = new JLabel("Last Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lastNameLabel, gbc);

        lastNameField = new JTextField(20);
        gbc.gridx = 1;
        add(lastNameField, gbc);

        JLabel streetLabel = new JLabel("Street:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(streetLabel, gbc);

        streetField = new JTextField(20);
        gbc.gridx = 1;
        add(streetField, gbc);

        JLabel cityLabel = new JLabel("City:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(cityLabel, gbc);

        cityField = new JTextField(20);
        gbc.gridx = 1;
        add(cityField, gbc);

        JLabel stateLabel = new JLabel("State:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(stateLabel, gbc);

        stateField = new JTextField(20);
        gbc.gridx = 1;
        add(stateField, gbc);

        JLabel zipLabel = new JLabel("Zip:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(zipLabel, gbc);

        zipField = new JTextField(20);
        gbc.gridx = 1;
        add(zipField, gbc);

        JLabel telephoneLabel = new JLabel("Telephone:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(telephoneLabel, gbc);

        telephoneField = new JTextField(20);
        gbc.gridx = 1;
        add(telephoneField, gbc);

        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        AddMemberButtonListener(submitButton);
    }
    @Override
    public void init() {
        JFrame frame = new JFrame("Add Member Form");
        definePanel();
        frame.add(this);
        frame.pack();
        frame.setMinimumSize(new Dimension(400, 400)); // Set a minimum size for the frame
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(new AddMemberWindow());
        isInitialized(true);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }
}

