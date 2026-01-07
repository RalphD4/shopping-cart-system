
public class ShoppingCart {
	//List of Line items
	private LineItem[] shopping_list;
	private int num_items;
	private int cart_length;
	
	
	public ShoppingCart() {
		this.shopping_list = new LineItem[5];
		this.num_items = 0;
		this.cart_length = 5;
		
	}
	
	//getters
	public int get_num_items(){
		return this.num_items;
	}
	
	
	
	//Finding LineItem with product name
	private int find_item(LineItem item) {
		for(int i = 0; i < num_items; i++) {
			if(shopping_list[i].get_product_name().equals(item.get_product_name())) {
				return i;
			}
		}
		return -1;  //return index position if found or -1 if not found
	}
	
	//Multiply shopping cart space by 2
	private void add_space() {
		LineItem[] new_list = new LineItem[num_items * 2]; //create new empty list with double the space
		cart_length *= 2;
		
		for(int i = 0; i < num_items; i++) {               //copy shopping list to new list
			new_list[i] = shopping_list[i];
		}
		
		shopping_list = new_list;                         //reassign shopping_list 
	}
	
	
	//delete all items in shopping cart, to use after checking out 
	public void clear_items() {
		shopping_list = new LineItem[cart_length];
		num_items = 0;
	}
	
	
	public void add_item(LineItem item){
		//check if item exists
		int exists_at = this.find_item(item); 
		
		//check if cart full, adjust space if needed
		if (num_items == cart_length) {  
			add_space();
		}
		
		//add item at next available slot if it does not exists, otherwise increase quantity of the item
		if(exists_at == -1){
			shopping_list[num_items] = item;
			num_items++;
			
		}
		else {
			shopping_list[exists_at].set_quantity(shopping_list[exists_at].get_quantity() + item.get_quantity());
		}
		
	}
	
	public void adjust_quantity(LineItem item, int quantity) {
		//check if item exists
		int exists_at = this.find_item(item);
		
		if(exists_at != -1){
			//adjust quantity
			shopping_list[exists_at].set_quantity(quantity);
		}
		
		
		else {
			System.out.println("Item does not exists");
		}	
		
	}
	
	public void remove_item(LineItem item){
		
		//check if item exists
		int exists_at = this.find_item(item);
		
		if(exists_at != -1){
			for(int i = exists_at; i < num_items - 1;i++) {
				shopping_list[i] = shopping_list[i+1];
				
			}
			//set duplicate last item to null and reduce num items
			shopping_list[num_items - 1] = null;
			num_items--;
			
			
		}
		
		else {
			System.out.println("Item does not exists");
		}	
	}
	
	//the price you'll have to pay
	public int get_total_price() {
		int shopping_price = 0;
		
		for(int i = 0; i < num_items; i++) {
			shopping_price += shopping_list[i].get_total();
		}
		
		return shopping_price;
	}
	
	
	
	//shows you the list of Line Items in the cart as a toString
	public void view_cart(){
		if(num_items == 0) {
			System.out.println("Cart is empty");
		}
		
		else {
			for(int i = 0; i < num_items; i++){
				System.out.println(shopping_list[i].toString());
			}
		}
		
		
	}
	
	public LineItem[] save_list() {
		LineItem[] saved_record = new LineItem[num_items];
		for(int i = 0; i < num_items; i++) {
			saved_record[i] = new LineItem(shopping_list[i]);
		}
		return saved_record;
	}
	
	//show the shopping list
	
	
	

}
