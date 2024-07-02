package librarysystem;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import business.Checkout;


public class CheckoutTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Book Title", "ISBN", "Copy Number", "Checkout Date", "Due Date"};
    private final List<Checkout> checkouts;

    public CheckoutTableModel(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }

    @Override
    public int getRowCount() {
        return checkouts.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Checkout checkout = checkouts.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return checkout.getBookCopy().getBook().getTitle();
            case 1:
                return checkout.getBookCopy().getBook().getIsbn();
            case 2:
                return checkout.getBookCopy().getCopyNum();
            case 3:
                return checkout.getCheckoutDt();
            case 4:
                return checkout.getDueDate();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Making all cells read-only
    }
}

