package business;



public class LibraryMemberCheckoutRecordFactory {
	public static LibraryMember createLibraryMember(String memberId,String firstname, String lastname, String tel,Address add) {
		if(memberId == null) 
			throw new IllegalArgumentException("MemberId cannot be null");
		LibraryMember member = new LibraryMember(memberId, firstname, lastname, tel,add);
		
		//Library member set inside the checkout record
		CheckoutRecord record = new CheckoutRecord(member);
		
		//checkout set inside the Library member
		member.setCheckoutRecord(record);
		
		return member;
	}	
	
}
