import java.time.LocalDate;

public class Payment {
	private String id;
	private LocalDate paid_date;
	private int total_to_pay;
	private String details;
	private int balance;
	
	public Payment(String payment_id, LocalDate payment_date, int payment_total, int bal) {
		this.id = payment_id;
		this.paid_date = payment_date;
		this.total_to_pay = payment_total;
		this.details = null;
		this.balance = bal;
	}
	
	public boolean pay_items(int total) {
		
		if(this.balance >= this.total_to_pay) {
			return true;
		}
		
		return false;
	}
	
	

}
