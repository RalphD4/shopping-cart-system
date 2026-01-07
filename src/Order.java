import java.time.LocalDate;

public class Order {
	private String number;
	private LocalDate date_ordered;
	private LocalDate date_shipped;
	private String ship_to;
	private OrderStatus status;
	private int total;
	
	
	//array of LineItem containing what was bought at what quantity
	private LineItem[] ordered_items;
	
	
	public Order(String n, LocalDate order_date, LocalDate ship_date, String ship_location, OrderStatus x, LineItem[] list, int num) {
		this.number = n;
		this.date_ordered = order_date;
		this.date_shipped = null;
		this.ship_to = ship_location;
		this.status = x;
		this.total= num;
		this.ordered_items = list;
		
	}
	
	public void set_ship_location(String location) {
		ship_to = location;
	}
	
	//Order details
	public String view_order_details() {
		String info = "order number: " + number + "\n" +
					  "order date: " + date_ordered + "\n" +
					  "ship date: " + date_shipped + "\n" +
					  "ship location: " + ship_to + "\n" +
					  "order status: " + status + "\n" +
					  "total paid: " + total;
		return info;
	
	}
	
	//Item details
	public void view_order_items(){
		for (LineItem item: ordered_items) {
			System.out.println(item.toString());
		}
		System.out.println("total paid: " + total);
		
	}
	
	//Changing order status
	public void setStatus(OrderStatus new_status) {
        status = new_status;
        
        // If status changes to SHIPPED, set ship date
        if (new_status == OrderStatus.SHIPPED) {
            date_shipped = LocalDate.now();
        }
        
        if (new_status == OrderStatus.DELIVERED) {
            closeOrder(); // close the order if delivered
        }
    }
	
	private void closeOrder(){
		status = OrderStatus.CLOSED;
	}
	
	public OrderStatus get_status(){
		return status;
	}
	
	
	
	

}
