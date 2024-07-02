package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private List<Checkout> checkoutRecord;
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		this.checkoutRecord = new ArrayList<>();
	}

	public String getMemberId() {
		return memberId;
	}
	public List<Checkout> getCheckoutRecord(){return checkoutRecord;}

	public void setCheckoutRecord(Checkout record){
		if (this.checkoutRecord == null) this.checkoutRecord = new ArrayList<>();
		this.checkoutRecord.add(record);
	}
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
