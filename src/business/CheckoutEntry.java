package business;

import java.time.LocalDate;

import business.BookCopy;

public class CheckoutEntry {
	private CheckoutRecord checkoutRecord;
	
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    private double fines;

    public CheckoutEntry(BookCopy bookCopy,LocalDate checkoutDate, LocalDate dueDate,CheckoutRecord checkoutRecord) {
        // Parse the date strings and assign them to the LocalDate fields
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        this.checkoutRecord=checkoutRecord;
    }
    
    
 // Add getters and setters for the fields
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }
    
    public double getFines() {
        return this.fines;
    }
    
    @Override
    public String toString() {
    	return "Check Out date: " + checkoutDate + " Due date: " + dueDate;
    }

}

