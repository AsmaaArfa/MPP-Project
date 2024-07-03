package business;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CheckoutRecord {
	private List<CheckoutEntry> checkoutEntries=new ArrayList<CheckoutEntry>();
	// this is the owner
	private LibraryMember libraryMember;
	
	// needs to be package level
	CheckoutRecord(LibraryMember libraryMember) {
		this.libraryMember=libraryMember;
	}
	
	public void addCheckoutList(CheckoutEntry checkoutEntry) {
		checkoutEntries.add(checkoutEntry);
	}
	
	public List<CheckoutEntry> getCheckoutEntry() {
		return this.checkoutEntries;
	}
	public LibraryMember getOwner() {
		return this.libraryMember;
	}
	
	//public CheckoutEntry(BookCopy bookCopy,LocalDate checkoutDate, LocalDate dueDate,CheckoutRecord checkoutRecord) {
	       
	public void addCheckoutEntry(BookCopy bookCopy,LocalDate checkoutDate, LocalDate dueDate) {
		CheckoutEntry entry = new CheckoutEntry(bookCopy, dueDate, dueDate, this);
		checkoutEntries.add(entry);
	}
	
	@Override
	public String toString() {
		return checkoutEntries.toString();
	}
	
}




