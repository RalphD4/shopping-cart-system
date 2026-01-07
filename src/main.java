import java.time.LocalDate;
import java.util.Scanner;

public class main {
	
	

	public static void main(String[] args) {
		
		//Array of customers
		Customer[] customers = new Customer[50]; // initial size
		int numCustomers = 0;
		
		
		
		
		
		//Products
		Product water = new Product("111", "water", "Winco");
		Product milk = new Product("112", "milk", "Winco");
		Product banana = new Product("113", "banana", "Winco");
		Product apple_phone = new Product("114", "apple", "Walmart");
		Product galaxy_phone = new Product("115", "samsung", "Walmart");
		Product vacuum = new Product("116", "vacuum", "Walmart");
		Product fifa = new Product("117", "fifa", "Playstation");
		Product storm4 = new Product("118", "storm4", "Playstation");
		Product marvel = new Product("119", "marvel", "Playstation");
		
		//List of products
		Product[] items_list = {water, milk, banana, apple_phone, galaxy_phone, vacuum, fifa, storm4, marvel};
		
		
		
		
		//Menu
		Scanner sc = new Scanner(System.in);
		
		//keeping the system running
		boolean system_running = true;
		boolean logged_in = false;
		Customer active_customer = null;
		
		while(system_running) {
			//commands options
			System.out.println("WELCOME TO THE SHOPPING SYSTEM");
			System.out.println("Here are the number options for the system");
			System.out.println("1. Create Customer & Account info");
			System.out.println("2. Log in by ID");
			System.out.println("0. Exit");
			
			System.out.println();
			
			//user choice
			System.out.println("Choose an option: ");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				numCustomers = customer_creation(sc, customers, numCustomers);
				break;
				
			case 2: 
			    active_customer = finding_customer(sc, customers, numCustomers);
			    if(active_customer != null) {
			    	logged_in = true;
			    	System.out.println();
			    }
			    break;
				
			case 0:
				system_running = false;
				break;
			
			default:
				System.out.println("Invalid option selected");
				
			}
			
			while(logged_in) {
				System.out.println("You are now logged in");
				System.out.println("Select the next option based on what you'd like to do.");
				
				System.out.println("3. Add to shopping cart");
				System.out.println("4. Remove from shopping cart");
				System.out.println("5. Adjust item quantity");
				System.out.println("6. View cart");
				System.out.println("7. Checkout");
				System.out.println("8. View orders");
				System.out.println("9. Log out");
				
				//user choice
				System.out.println("Choose an option: ");
				
				int operation = sc.nextInt();
				sc.nextLine();
				
				switch(operation) {
				
				case 3:
					System.out.println("What item would you like to add to the cart? Enter the product name.");
					add_to_cart(sc, active_customer, items_list);
					break;
				
				case 4:
					remove_from_cart(sc, active_customer);
					break;
				
				case 5:
					adjust_item(sc, active_customer);
					break;
				
				case 6:
					view_cart(active_customer);
					break;
				
				case 7:
					checkout(sc, active_customer);
					break;
				
				case 8:
					viewOrders(active_customer);
					break;
					
				case 9:
					logged_in = false;
					System.out.println("You are now logged out");
					break;
				
				default:
					System.out.println("Invalid option selected");
					
				}
			}
		}
		
		
		view_customers(customers, numCustomers);
	}
	
	
	
	
	
	
	
	
	
	///-----------------------  SYSTEM FUNCTIONS -----------------------------//////
	
	
	private static int customer_creation(Scanner sc, Customer[] customers, int numCustomers) {
	    System.out.println("Creating a new customer...");
	    
	    //Customer Personal information
	    System.out.print("Enter customer ID: ");
	    String id = sc.next();
	    
	 // Check if ID already exists
	    boolean exists = true;
	    while (exists) {
	        exists = false; // assume it's unique
	        for (int i = 0; i < numCustomers; i++) {
	            if (customers[i] != null && customers[i].get_id().equals(id)) {
	                System.out.println("This customer ID already exists. Enter a different ID: ");
	                id = sc.next();
	                exists = true; // found duplicate, need to check again
	                break; // stop checking current array
	            }
	        }
	    }

	    System.out.print("Enter customer address: ");
	    sc.nextLine(); 
	    String address = sc.nextLine();

	    System.out.print("Enter customer phone number: ");
	    int phone = sc.nextInt();
	    sc.nextLine();

	    System.out.print("Enter customer email: ");
	    String email = sc.next();
	    sc.nextLine();
	    
	    
	    //Account information
	    System.out.print("Enter billing address: ");
	    String billing_address = sc.nextLine();
	    

	    // create 1 shopping cart and account for the customer
	    ShoppingCart cart = new ShoppingCart();
	    Account account = new Account("B" + id, billing_address, false, LocalDate.now(), null, cart);

	    //create customer
	    Customer newCustomer = new Customer("A" + id, address, phone, email, account);

	    // add the customer to the  array
	    customers[numCustomers] = newCustomer;
	    numCustomers++; // increment the counter

	    System.out.println("Customer created successfully!\n");
	    
	    //saved updated customer count
	    return numCustomers;
	}

	
	//takes in the active customer, you want to add to their cart
	private static void add_to_cart(Scanner sc, Customer x, Product[] items) {
		ShoppingCart customer_cart = x.get_Account().get_ShoppingCart(); //retrieve the cart for this customer account
		
		System.out.println("Here is the list of products");
		for(int i = 0; i < 9; i++) {
			System.out.println((items[i].get_name()));
		}
		System.out.println();
		
		System.out.println("Which product would you like to add in the cart? ");
		String product_input = sc.next();
		
		//Product finder
		int location = find_product(product_input, items);
		
		if (location != -1) {
			System.out.println("Enter the price: ");
			int product_price = sc.nextInt();
			
			System.out.println("Enter the quantity: ");
			int quantity = sc.nextInt();
			sc.nextLine();
			
			//Create it as a line item
			LineItem new_item = new LineItem(quantity, product_price, items[location]);
			
			//Add this item to the cart
			customer_cart.add_item(new_item);
			
			
		}
		
	}
	
	private static void remove_from_cart(Scanner sc, Customer x) {
		ShoppingCart customer_cart = x.get_Account().get_ShoppingCart(); //retrieve the cart for this customer account
		
		LineItem[] items = customer_cart.save_list(); // get current cart LineItems list
		int numItems = customer_cart.get_num_items();
		
		if (items.length == 0) {
	        System.out.println("Your cart is empty.");
	        return;
	    } 
		
		//ask user what to remove
		System.out.println("Which product would you like to remove from the cart? ");
		String product_input = sc.next();
		 
	    //check where the lineitem is based on the name
	    for(int i = 0; i < numItems; i++) {
	    	if(items[i].get_product_name().equalsIgnoreCase(product_input)) {
	    		customer_cart.remove_item(items[i]);
	    		System.out.println("Item has been removed");
	    		break;
	    	}
	    }
	
	    
	    
	}
	
	private static void adjust_item(Scanner sc, Customer x) {
		ShoppingCart customer_cart = x.get_Account().get_ShoppingCart(); //retrieve the cart for this customer account
		
		LineItem[] items = customer_cart.save_list(); // get current cart LineItems list
		int numItems = customer_cart.get_num_items();
		
		if (items.length == 0) {
	        System.out.println("Your cart is empty.");
	        return;
	    } 
		
		System.out.println("Which product would you like to adjust the quantity from? ");
		String product_input = sc.next();
		 
	    
	    
	    for(int i = 0; i < numItems; i++) {
	    	if(items[i].get_product_name().equalsIgnoreCase(product_input)) {
	    		LineItem item = items[i];
	    		System.out.println("Enter the quantity: ");
	    		int quantity = sc.nextInt();
	    		sc.nextLine();
	    		customer_cart.adjust_quantity(item, quantity);
	    		break;
	    	}
	    }
	}
	
	private static void view_cart(Customer x) {
		ShoppingCart customer_cart = x.get_Account().get_ShoppingCart(); //retrieve the cart for this customer account
		customer_cart.view_cart();
	}
	
	private static void checkout(Scanner sc, Customer x) {
		//retrive the customer account and cart
		Account customer_account = x.get_Account();
		ShoppingCart customer_cart = customer_account.get_ShoppingCart();
		//retrive total price of itmes in cart
		int cart_total = customer_cart.get_total_price();
		
		//asking for payment
		 System.out.print("Enter available funds to pay: ");
		 int balance = sc.nextInt();
		 sc.nextLine();
		 
		Payment payment = new Payment("P" + customer_account.get_numOrders(), LocalDate.now(), cart_total, balance);
		customer_account.setPayment(payment);
		
		Order new_order = customer_account.check_out(String.valueOf(customer_account.get_numOrders()),x.get_address());
		
		if (new_order != null) {
	        System.out.println("Checkout successful! Order created:");
	    } else {
	        System.out.println("Payment failed. Checkout canceled.");
	    }
		
		
	}
	
	


	private static void viewOrders(Customer x) {
		Account customer_account = x.get_Account();
		customer_account.get_orders();
	}
	
	//Customer list showing all customers by name and email
	private static void view_customers(Customer[] customers, int numCustomers) {
	    System.out.println("List of Customers:");
	    for (int i = 0; i < numCustomers; i++) {
	        System.out.println((i + 1) + ". " + customers[i].get_id() + " - " + customers[i].get_email());
	    }
	}
	
	
	
	
	
	////------------------------ Helpers---------------------////
	
	
	//Finding a customer by their id
	//Returns the Customer
	private static Customer finding_customer(Scanner sc, Customer[] customers, int numCustomers) {
	    System.out.print("Enter 'A' followed by your customer ID number: ");
	    String id = sc.nextLine().toUpperCase();

	    for (int i = 0; i < numCustomers; i++) {
	        if (customers[i].get_id().equals(id)) {
	            System.out.println("Welcome, " + customers[i].get_email());
	            return customers[i];
	        }
	    }

	    System.out.println("Customer ID not found. Create one.");
	    System.out.println();
	    return null;
	}
	
	// Finding a product in product list
	//return location index or -1 if not found
	private static int find_product(String product_name, Product[] items) {
	    for (int i = 0; i < items.length; i++) {
	        if (items[i] != null && items[i].get_name().equalsIgnoreCase(product_name)) {
	            return i;
	        }
	    }
	    return -1; 
	}




}


