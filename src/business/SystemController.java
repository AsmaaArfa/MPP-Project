package business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

//import javax.swing.JOptionPane;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	
	List<CheckoutEntry> check=new ArrayList<CheckoutEntry>();
	double overDueFine;
	
	public static Auth currentAuth = null;
	List<String> report = new ArrayList<>();
	boolean isCheckout = false;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();

//		//  local storage
//		HashMap<String, String> localmap=new HashMap<>();
//		localmap.put("name", "123");
//		if(!localmap.containsKey(id)) {
//			throw new LoginException("ID " + id + " not found");
//		}
//		String passwordFound = localmap.get(id);
//		if(!passwordFound.equals(password)) {
//			throw new LoginException("Password incorrect");
//		}
//			
	}

	public void addNewBook(String title, String isbn, int maxCheckOutLength, List<Author> authors, int numOfCopies)
			throws LibrarySystemException {
		DataAccessFacade da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		if (map.containsKey(isbn)) {
			throw new LibrarySystemException();
		}
		Book b;
		if (numOfCopies == 1) {
			b = new Book(isbn, title, maxCheckOutLength, authors);
		} else {
			b = new Book(isbn, title, maxCheckOutLength, authors, numOfCopies);
		}
		da.saveNewBook(b);
	}

	public void addNewBookCopy(String isbn) throws LibrarySystemException {
		DataAccessFacade da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		if (!map.containsKey(isbn)) {
			throw new LibrarySystemException();
		}
		da.saveNewBookCopy(isbn);
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	public DataAccessFacade MemberController() {
		DataAccessFacade da = new DataAccessFacade();
		return da;
	}
	public DataAccessFacade BookController() {
		DataAccessFacade da = new DataAccessFacade();
		return da;
	}
	

	public void chekOut(String memberId, String bookISBN) {
		// Retrieve all the members and books in the storage
		DataAccessFacade da = new DataAccessFacade();
		HashMap<String, LibraryMember> membermap = da.readMemberMap();
		HashMap<String, Book> bookMap = da.readBooksMap();

//		// check if the member exists in the storage
//		if (!membermap.containsKey(memberId)) {
//			String message = "Member Id" + memberId + " not found";
//			JOptionPane.showMessageDialog(null, message);
//			return;
//		}
//		// check if the book exists in the storage
//		if (!bookMap.containsKey(bookISBN)) {
//		
		// }
		// getting the member with the given ID, and the the book with the given ISBN
		LibraryMember member = membermap.get(memberId);
		Book book = bookMap.get(bookISBN);
		// System.out.print("controller!");
		Address address=member.getAddress();
		if (member != null && book != null) {
			BookCopy bookCopy = book.getNextAvailableCopy();
			if (bookCopy != null) {
				LocalDate today = LocalDate.now();
				int checkoutLength = book.getMaxCheckoutLength();
				LocalDate dueDate = today.plusDays(checkoutLength);
				
				LibraryMember mem=LibraryMemberCheckoutRecordFactory.createLibraryMember(memberId, member.getFirstName(), member.getLastName(), member.getTelephone(), address);
				mem.getCheckoutRecord().addCheckoutEntry(bookCopy, today, dueDate);
				CheckoutRecord checkoutRecord=mem.getCheckoutRecord();
				CheckoutEntry checkoutEntry = new CheckoutEntry(bookCopy, today, dueDate, checkoutRecord);
				check.add(checkoutEntry);
				//member.getCheckoutRecord().addCheckoutEntry(bookCopy, today, dueDate);
				//member.setCheckoutRecord(checkoutRecord);
				
//				
//				CheckoutEntry checkoutEntry = new CheckoutEntry(bookCopy, today, dueDate);
//				CheckoutRecord checkoutRecord = new CheckoutRecord();
//				checkoutRecord.addCheckoutEntry(checkoutEntry);
//				member.setCheckoutRecord(checkoutRecord);
				bookCopy.changeAvailability();
				da.saveNewBook(book);
				

				// System.out.println(bookCopy.isAvailable());

//				System.out.println("Book Copy: " + bookCopy.getCopyNum());
//				System.out.println("Check out date: " + checkoutEntry.getCheckoutDate());
//				System.out.println("Due date: " + checkoutEntry.getDueDate());
//				
//				report.clear();
//				isCheckout = true;
//				HashMap<String, LibraryMember> members = da.readMemberMap();
//				LibraryMember m = members.get(memberId);
//				String memberName = m.getFirstName() + m.getLastName();
//				report.add("Member Details:");
//				report.add("      ->ID: " + memberId);
//				report.add("      ->Name: " + memberName);
//				report.add("Book ISBN: " + bookISBN);
//				report.add("Book Title: " + book.getTitle());
//				report.add("Book Copy Number: " + Integer.valueOf(bookCopy.getCopyNum()).toString());
//
//				report.add("Checkout Date: " + checkoutEntry.getDueDate().toString());
//				report.add("Due Date: " + checkoutEntry.getCheckoutDate().toString());
//
//				System.out.println("-------------------------------");
//				report.forEach(r -> {
//					System.out.println(r);
//				});
				
				//"Member Id", "Book Title", "ISBN","Copy Num", "Check Out Date", "Due Date","Over Due"
				// another solution would be defining the below code inside the bookcopy class
				LocalDate now=LocalDate.of(2023, 11, 20);
				long overDue=0;
				int difference=checkoutEntry.getDueDate().compareTo(now); // 2023-11-07
				//if(difference<0) {
					overDue=ChronoUnit.DAYS.between(checkoutEntry.getDueDate(),now );
				//}
				
				report.clear();
				report.add(memberId);
				report.add(book.getTitle());
				report.add(bookISBN);
				report.add(Integer.valueOf(bookCopy.getCopyNum()).toString());
				report.add(checkoutEntry.getCheckoutDate().toString());
				report.add(checkoutEntry.getDueDate().toString());
				report.add(Long.valueOf(overDue).toString());
		
				da.saveNewBook(book);


			} else {
				JOptionPane.showMessageDialog(null, "No copies available for checkout at this time.");
			}

		} else if (book == null) {
			String message1 = "Book ISBN " + bookISBN + " not found";
			JOptionPane.showMessageDialog(null, message1);
			return;
		} else if (member == null) {
			String message1 = "Member Id " + memberId + " not found";
			JOptionPane.showMessageDialog(null, message1);
			return;
		}
	}

	public void returnBook1(String bookISBN,String copyNum) {
		// Retrieve all the members and books in the storage
		DataAccessFacade da = new DataAccessFacade();
		HashMap<String, LibraryMember> membermap = da.readMemberMap();
		HashMap<String, Book> bookMap = da.readBooksMap();
		Book book = bookMap.get(bookISBN);
		//Book book = bookMap.get(bookISBN);
		//BookCopy copy=book.getCopy(0)
		BookCopy[] copies=book.getCopies();
		
		//boolean wasCheckedOut=false;
		BookCopy copy=null;
		copyNum="0";
		for(int i=0;i<copies.length;i++) {
			if(Integer.valueOf(copies[i].getCopyNum()).toString().equals(copyNum)) {
				copies[i].changeAvailability();
				copy=copies[i];
			}
		}
		
		String tempComp="";
		LibraryMember member=membermap.get("1001");
		CheckoutRecord checkOutRecord=member.getCheckoutRecord();
		List<CheckoutEntry> entries=checkOutRecord.getCheckoutEntry();
		for(CheckoutEntry e: entries) {
			 if(bookISBN.equals(e.getBookCopy().getBook().getIsbn())) {
				 e.getBookCopy().changeAvailability();
				 break;
			 }
		}
			
		// 
		
		//member.getCheckoutRecord().getCheckoutEntries().get
		
		
		
	}

	public List<String> getReport() {
		return report;
	}

	public boolean getIsChechout() {
		return isCheckout;
	}

	public static boolean isNumeric(String input) {
		// Define a regular expression pattern that matches numbers
		Pattern pattern = Pattern.compile("^[0-9]+$");
		Matcher matcher = pattern.matcher(input);

		// Check if the input matches the pattern
		return matcher.matches();
	}
	
	
	
	public void retBook(String bookISBN, int copyNum) {
		DataAccessFacade da = new DataAccessFacade();
	    HashMap<String, LibraryMember> membermap = da.readMemberMap();
	    HashMap<String, Book> bookMap = da.readBooksMap();
	    Book book = bookMap.get(bookISBN); // repalce this with bookISBN

	   BookCopy cop= book.getCopy(copyNum);
	   CheckoutEntry ce=new CheckoutEntry(cop, null, null, null);
	   
	   
	   
	    
	    CheckoutRecord checkoutRecord=new CheckoutRecord(null);
	}
	
	
	
	
	
	public void returnBookSimple(String bookISBN,String copyNum) {
		DataAccessFacade da = new DataAccessFacade();
	    HashMap<String, LibraryMember> membermap = da.readMemberMap();
	    HashMap<String, Book> bookMap = da.readBooksMap();
	    Book book = bookMap.get(bookISBN); // repalce this with bookISBN

	    if (book != null) {
	        for (BookCopy copy : book.getCopies()) {
	        	//System.out.println(copy.getCopyNum()+":"+copy.isAvailable());
	            if (!copy.isAvailable()) {
	            	if(Integer.valueOf(copy.getCopyNum()).toString().equals(copyNum)) {
	            		copy.changeAvailability();
	            		
	            		//System.out.println(copyNum + " removed!");
	            		da.saveNewBook(copy.getBook());
	            		break;
	            	}
	            }
	        }
	    }
	}
	
	
	
	
	public void returnBook(String bookISBN,String copyNum) {
		DataAccessFacade da = new DataAccessFacade();
	    HashMap<String, LibraryMember> membermap = da.readMemberMap();
	    HashMap<String, Book> bookMap = da.readBooksMap();
	    Book book = bookMap.get(bookISBN); // repalce this with bookISBN

	    if (book != null) {
	        for (BookCopy copy : book.getCopies()) {
	        	System.out.println(copy.getCopyNum()+":"+copy.isAvailable());
	            if (!copy.isAvailable()) {
	            	if(Integer.valueOf(copy.getCopyNum()).toString().equals(copyNum)) {
	            		copy.changeAvailability();
	            		
	            		break;
	            	}
	            	System.out.println("Are you in?"+ copy.isAvailable());
	            	// Find the member who checked out the book
//	            	copy.ge
//	            	CheckoutRecord rec=new CheckoutRecord();
		            String memberId = "1001"; // Replace with the actual member ID
		            LibraryMember member = membermap.get(memberId);

		            if (member != null) {
		            	
		            	Address address=member.getAddress();
		            	LibraryMember mem=LibraryMemberCheckoutRecordFactory.createLibraryMember(memberId, member.getFirstName(), member.getLastName(), member.getTelephone(), address);
						mem.getCheckoutRecord();
						CheckoutRecord checkoutRecord=mem.getCheckoutRecord();
		            	if(mem.getCheckoutRecord()!=null) {
		            		var entries = checkoutRecord.getCheckoutEntry();
		            		for(int i=0;i<check.size();i++) {
		            			checkoutRecord.addCheckoutEntry(check.get(i).getBookCopy(), check.get(i).getCheckoutDate(), check.get(i).getDueDate());
		            		}
		          
		            		//System.out.println("checkout record:"+ mem.getFirstName() + " " + entries.toString());
			                for (CheckoutEntry entry : entries) {
			                	if(Integer.valueOf(copy.getCopyNum()).toString().equals(Integer.valueOf(entry.getBookCopy().getCopyNum()).toString())) {
			                  //  if (bookISBN.equals(entry.getBookCopy().getBook().getIsbn())) {
			                    	copy.changeAvailability();
			        	            da.saveNewBook(copy.getBook());
			        	            //entry.getBookCopy().
			        	            entries.remove(entry);
			        	            
			        	            System.out.println(entry.getBookCopy().getCopyNum() + "has been removed!");
			        	           // System.out.println("checkout record:"+ mem.getFirstName() + " " + entries.toString());
			                        break;
			                    }
			                    else {
			                    	break;
			                    }
			                }
		            	}else{
		            		JOptionPane.showMessageDialog(null, "member checkout record is null!");
		            		break;
		            	}
		            } else {
		                String message = "Member Id " + memberId + " not found";
		                JOptionPane.showMessageDialog(null, message);
		            }
	            }else {
			        JOptionPane.showMessageDialog(null, "No copies available for return.");
			        break;
			    }
	        }
	    }
	        else {
    	        String message1 = "Book ISBN " + bookISBN + " not found";
    	        JOptionPane.showMessageDialog(null, message1);
    	        return;
    	    }
	            
	    }

//	public void print() {
//		for()
//		
//	}
	
	
//	public static void main(String[] args) {
//		CheckoutRecord cr=new CheckoutRecord();
//		cr.getCheckoutEntries();
//		System.out.print(cr.getCheckoutEntries());
//	}
	
	
}
