import java.time.LocalDate;

public class Account {
	
	private String id;
	private String billing_address;
	private boolean is_closed = true;
	private LocalDate opened_date;
	private LocalDate closed_date;
	
	//owns 1 shopping cart
	private ShoppingCart shopping_cart;
	
	//and a list or orders 
	private Order[] orders_list; 
	private int num_orders;
	private int orders_space;
	
	//Payment
	private Payment payment;
	
	
	public Account(String acc_id, String acc_address, boolean closed, LocalDate date1, LocalDate date2, ShoppingCart cart) {
		this.id = acc_id;
		this.billing_address = acc_address;
		this.is_closed = closed;
		this.opened_date = date1;
		this.closed_date = date2;
		this.shopping_cart = cart;
		
		this.orders_space = 10;
		this.orders_list = new Order[orders_space];
		this.num_orders = 0;

		
	}
	
	private void adjust_size() {
		Order[] newOrders = new Order[orders_space * 2];
		orders_space *= 2;
		for(int i = 0; i < num_orders; i++) {
			newOrders[i] = orders_list[i];
		}
		orders_list = newOrders;
	}
	
	public Order check_out(String order_number, String ship_to){
		//copy items from cart and get total
		LineItem[] ordering = shopping_cart.save_list();
		int total = shopping_cart.get_total_price();
		
		boolean payed = payment.pay_items(total);
		
		//if payment was successful
		if(payed) {
			//Create the order here
			Order new_order = new Order(order_number, LocalDate.now(), null, ship_to, OrderStatus.NEW, ordering, total);
			
			//add it to the list
			if (num_orders == orders_space) {adjust_size();}
			orders_list[num_orders] = new_order;
			num_orders++;
			
			
			shopping_cart.clear_items();
			
			
			return new_order;
		}
		
		return null;
		
		
	}
	
	//order getters
	public void get_orders() {
		for(int i = 0; i < num_orders; i++) {
			System.out.println(orders_list[i].view_order_details());
			orders_list[i].view_order_items();
			System.out.println("-----------------------------------");
			
		}
	}
	
	public void setPayment(Payment p) { 
		this.payment = p; }
	
	//return order list
	public Order[] get_order_list() {
		return orders_list;
	}
	
	public int get_numOrders() {
		return this.num_orders;
		
	}
	
	//main setters
	public void set_billing_address(String address) {
		this.billing_address = address;
	}
	public void set_status(boolean x) {
		this.is_closed = x;
	}
	
	//main getters
	
	public ShoppingCart get_ShoppingCart() {
		return this.shopping_cart;
	}
	public String get_id() {
		return this.id;
	}
	
	public String get_address() {
		return this.billing_address;
	}
	
	public boolean get_status() {
		return this.is_closed;
	}
	
	public LocalDate get_open() {
		return this.opened_date;
	}
	
	public LocalDate get_closed() {
		return this.closed_date;
	}
	
	public String toString() {
		String info = this.id + " ," + this.billing_address + ", " + this.is_closed + ", " + this.opened_date + ", " + this.closed_date;
		return info;
	}

}
