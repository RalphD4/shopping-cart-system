
public class LineItem {
	private int quantity;
	private int price;    //for item of quantity 1
	
	private Product product;
	
	//constructor
	public LineItem(int q, int p, Product product_item) {
		this.quantity = q;
		this.price = p;
		this.product = product_item;
	}
	
	//used for copying
	public LineItem(LineItem other) {
		this.quantity = other.quantity;
		this.price = other.price;
		this.product = other.product;
	}
	
	
	//setters
	public void set_quantity(int num) {
		this.quantity = num;
	}

	public void set_price(int num) {
		this.price = num;
	}
	
	public int get_quantity() {
		return this.quantity;
	}
	
	
	//used to get line item product name
	public String get_product_name() {
		return this.product.get_name();
	}
	
	
	
	//get total for the line item
	public int get_total() {
		int total = quantity*price;
		return total;
	}
	
	//return line item's product name , quantity, and price
	public String toString() {
		String info = this.get_product_name() + ", " + "quantity: " + this.quantity + ", " +"price: " + this.price;
		return info;
	}
	
	


}
