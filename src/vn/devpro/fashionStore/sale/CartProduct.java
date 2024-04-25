package vn.devpro.fashionStore.sale;

import vn.devpro.fashionStore.update.product.Product;
import vn.devpro.fashionStore.update.product.ProductManagement;

public class CartProduct {
	private int productId;
	private int quantity;
	
	public void display() {
		Product product = ProductManagement.getProductById(this.productId);
		System.out.printf("%-34s %,8d %,15.2f %,15.2f\n", product.getName(), 
				this.quantity, product.getPrice(), this.total());
	}
	

	public double total() {
		Product product = ProductManagement.getProductById(this.productId);
		return this.quantity * product.getPrice();
	}
	
	
	public CartProduct() {
		super();
	}
	public CartProduct(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
