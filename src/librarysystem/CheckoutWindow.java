package librarysystem;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import business.BookCopy;
import business.Checkout;
import business.LibraryMember;
import business.SystemController;
import dataaccess.Auth;

public class CheckoutWindow extends JPanel implements LibWindow{
    public static final CheckoutWindow INSTANCE = new CheckoutWindow();

    private JTextField memberIdField;
    private JTextField isbnField;
    private JButton checkOutButton;

    private CheckoutWindow() {}

    private void preparePanel(){
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

        JLabel isbnLabel = new JLabel("ISBN:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(isbnLabel, gbc);

        isbnField = new JTextField(20);
        gbc.gridx = 1;
        add(isbnField, gbc);

        checkOutButton = new JButton("Check Availability");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(checkOutButton, gbc);

        addCheckOutButtonListener();
    }
    private String getMemberId(){return memberIdField.getText();}
    private String getIsbn(){return isbnField.getText();}

    private void addCheckOutButtonListener() {
        checkOutButton.addActionListener(e -> {
            // Handle the check availability action here
            SystemController systemController = new SystemController();
            LibraryMember member = systemController.getMember(getMemberId());

            if (member == null){
                JOptionPane.showMessageDialog(this, "Member ID is not exist");
            }
            else if (!systemController.getBook(getIsbn())){
                JOptionPane.showMessageDialog(this, "The book is not available.");
            }
            else {
                systemController.CheckoutBook(getIsbn(), member);
                List<Checkout> checkouts = systemController.display(member.getCheckoutRecord());
                CheckoutUI checkoutUI = new CheckoutUI(checkouts);
                checkoutUI.display();
            }

           // SystemController systemController1
           // boolean isAvailable = systemController.checkAvailability(getMemberId(), getIsbn());
//            if (isAvailable) {
//                JOptionPane.showMessageDialog(this, "The book is available for checkout.");
//            } else {
//                JOptionPane.showMessageDialog(this, "The book is not available for checkout.");
//            }
        });
    }

    public void init() {
        JFrame frame = new JFrame("Check Out Form");
        preparePanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);  // Add the current instance of CheckOutPanel to the frame
        frame.pack();  // Ensure components are at their preferred size
        frame.setMinimumSize(new Dimension(400, 200)); // Set a minimum size for the frame
        frame.setLocationRelativeTo(null);
        isInitialized(true);
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


