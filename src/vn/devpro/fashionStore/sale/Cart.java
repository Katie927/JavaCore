package vn.devpro.fashionStore.sale;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.fashionStore.update.customer.CustomerManagement;

public class Cart {
	private int id;
	private int customerId;
	private String code;
	private List<CartProduct> cartProducts = new ArrayList<CartProduct>();
	
	public void display() {
		System.out.println("\t Ma gio hang : "+ id);
		System.out.println("\t Ten khach hang : "+ 
					CustomerManagement.getCustomertById(customerId).getName()) ;
		System.out.println("Danh sach gio hang : ");
		
		System.out.printf("%-34s %8s %15s %15s\n", "Ten hang", 
				"So luong", "Don gia", "Thanh tien");
		for(CartProduct cartProduct:cartProducts) {
			cartProduct.display();
		}
		System.out.printf("\t\t Tong tien : %,.2f\n", this.totalCartMoney());
	}
	
	public double totalCartMoney() {
		double total = 0;
		for(CartProduct cartProduct:cartProducts) {
			total += cartProduct.total();
		}
		return total;
	}
	
	public int findCartProductById(int productId) {
		for(int i=0 ; i<cartProducts.size() ; i++) {
			if(cartProducts.get(i).getProductId() == productId) {
				return i;
			}
		}
		return -1;
	}
	
	public Cart() {
		super();
	}
	public Cart(int id, int customerId, String code, List<CartProduct> cartProducts) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.code = code;
		this.cartProducts = cartProducts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}
	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
}
