package vn.devpro.fashionStore.statistic.productInOrder;


public class ProductInOrder {
	public static int autoId = 1;
	private int id;
	private int productId;
	private int orderId;
	private int quantity;
	

	
	
	public ProductInOrder() {
		super();
	}

	public ProductInOrder(int id, int productId, int orderId, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.orderId = orderId;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}