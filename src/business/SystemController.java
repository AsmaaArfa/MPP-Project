package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import business.access.SystemAccess;
import librarysystem.LibrarySystem;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	DataAccessFacade dataAccessFacade = new DataAccessFacade();
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}

	//Asmaa Added
	public void AddMember(String memberId, String fname, String lname, String tel,Address add){

		dataAccessFacade.saveNewMember(new LibraryMember(memberId, fname, lname, tel, add));
		allMemberIds().add(memberId);
	}

	public void Checkout(){

	}

	public LibraryMember getMember(String memberID){
		HashMap<String, LibraryMember> libraryMembers = dataAccessFacade.readMemberMap();
		LibraryMember memberFound = libraryMembers.get(memberID);
		if (memberFound == null){
			return null;
		}
		return memberFound;
	}

	public boolean getBook(String isbn){
		HashMap<String,Book> books = dataAccessFacade.readBooksMap();
		Book bookFound = books.get(isbn);
		if (bookFound == null){
			return false;
		}

		if (!bookFound.isAvailable()){
			return false;
		}
		return true;
	}

	public void CheckoutBook(String isbn, LibraryMember member){
		Book book = dataAccessFacade.readBooksMap().get(isbn);
		if (book.getNextAvailableCopy() == null){
			book.addCopy();
		}
		Checkout checkout = new Checkout(book.getNextAvailableCopy(), LocalDate.now(),
				LocalDate.of(2024, 9, 12), member);
		member.setCheckoutRecord(checkout);
	}

	public List<Checkout> display(List<Checkout> checkoutBook){
		return checkoutBook;
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
	
	
}
