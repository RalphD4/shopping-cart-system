
public class Customer {
	
	//class attributes
	private String id;
	private String address;
	private int phone;
	private String email;
	
	//create 1 account object for the customer
	private Account account;
	
	
	
	//constructor method setting class attributes
	public Customer(String c_id, String c_address, int c_phone, String c_email, Account c_account){
		this.id = c_id;
		this.address = c_address;
		this.phone = c_phone;
		this.email = c_email;
		this.account = c_account;
	}
	
	//setters
	public void set_address(String new_address) {
		this.address = new_address;
	}
	
	public void set_phone(int new_phone) {
		this.phone = new_phone;
	}
	
	public void set_email(String new_email) {
		this.email = new_email;
		
	}
	
	//Create the account for customer 
	public void create_account(Account x) {
		this.account = x;
	}
	
	//getters
	
	public Account get_Account() {
		return this.account;
	}
	public String get_id() {
		return this.id;
	}
	
	public String get_address() {
		return this.address;
	}
	
	public int get_phone() {
		return this.phone;
	}
	
	public String get_email() {
		return this.email;
	}
	
	//toString method
	public String toString() {
		String info = this.id + ", " + this.address + ", " + this.phone + ", " + this.email;
		return info;
	}
	

	
	
	
	

}


