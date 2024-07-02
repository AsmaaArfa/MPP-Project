package business;

import java.time.LocalDate;

public class Checkout {
    private BookCopy bookCopy;
    private LocalDate checkoutDt;
    private LocalDate dueDate;
    private LibraryMember libraryMember;

    public Checkout(BookCopy bookCopy, LocalDate checkoutDt, LocalDate dueDate, LibraryMember libraryMember){
        this.bookCopy = bookCopy;
        this.checkoutDt = checkoutDt;
        this.dueDate = dueDate;
        bookCopy.changeAvailability();
        this.libraryMember = libraryMember;
    }

    public BookCopy getBookCopy() {
        return this.bookCopy;
    }

    public LocalDate getCheckoutDt() {
        return this.checkoutDt;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }
    public LibraryMember getLibraryMember(){return this.libraryMember;}

}
