package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public void AddMember(String memberId, String fname, String lname, String tel,Address add);
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
}
