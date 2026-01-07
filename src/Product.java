
public class Product {
	
	private String id;
	private String name;
	private String supplier;
	
	public Product (String product_id, String product_name, String product_supplier) {
		this.id = product_id;
		this.name = product_name;
		this.supplier = product_supplier;
	}
	
	
	public String get_name() {
		return this.name;
	}
	
	public String get_id() {
		return this.id;
	}
	
	public String get_supplier() {
		return this.supplier;
	}

}
