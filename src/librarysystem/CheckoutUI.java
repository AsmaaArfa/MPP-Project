package librarysystem;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import business.Author;
import business.Checkout;
import business.Book;
import business.BookCopy;

public class CheckoutUI {
    private JFrame frame;
    private JTable table;
    private CheckoutTableModel tableModel;

    public CheckoutUI(List<Checkout> checkouts) {
        tableModel = new CheckoutTableModel(checkouts);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        // Set up the frame
        frame = new JFrame("Checkout Records");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // Add the table to a scroll pane (for scrolling)
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    public void display() {
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

}

