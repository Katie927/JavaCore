package vn.devpro.fashionStore.sale;

import java.math.BigDecimal;
import java.util.Scanner;

import vn.devpro.fashionStore.statistic.order.Order;
import vn.devpro.fashionStore.statistic.order.OrderManagement;
import vn.devpro.fashionStore.statistic.productInOrder.ProductInOrder;
import vn.devpro.fashionStore.statistic.productInOrder.ProductInOrderManagement;
import vn.devpro.fashionStore.update.customer.Customer;
import vn.devpro.fashionStore.update.customer.CustomerManagement;
import vn.devpro.fashionStore.update.product.Product;
import vn.devpro.fashionStore.update.product.ProductManagement;

public class saleManagement {

	public static int autoId = 1;
	static Scanner sc = new Scanner(System.in);
	
	private static Cart cart = new Cart();
	
	public static void execute() {
		
		do {
			System.out.println("\n_____________QUAN LY GIO HANG____________");
			System.out.println("Chon chuc nang : ");
			System.out.println("\t1 - Hien thi gio hang");
			System.out.println("\t2 - Them moi san pham");
			System.out.println("\t3 - Thay doi so luong san pham");
			System.out.println("\t4 - Xoa san pham trong gio hang");
			System.out.println("\t5 - Huy gio hang");
			System.out.println("\t6 - Thanh toan");
			System.out.println("\t0 - Quay lai");
			
			System.out.println("Lua chon cua ban: ");
			
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				System.out.println("\n\t __GIO HANG CUA BAN__");
				if(cart.getCartProducts().size() <= 0 ) {
					System.out.println("\t GIo hang rong !");
				}
				else {
					cart.display();
				}
				break;
			case 2: 
				addToCart();
				break;
			case 3: 
				changeProductQunatity();
				break;
			case 4:
				deleteCartProduct();
				break;
			case 5: 
				cart = new Cart(); 	
				break;
			case 6: 
				payment(); 	
				break;
			case 0:	return;
			
			default:
				System.out.println("Lua chon khong hop le !");;
			}
		} while(true);
	}
	
	private static void payment() {
		System.out.println("\t __Thanh toan hoa don__");
		System.out.println("\t --- Thong tin khach hang : ");
		System.out.print("\t So dien thoai khach hang : ");
		String phoneNum = sc.nextLine();
		Customer customer = CustomerManagement.getCustomerByPhone(phoneNum);
	
		if(customer == null) {
			System.out.println("\t khach hang moi : ");
			System.out.print("\t Ten khach hang moi : ");
			String name = sc.nextLine();
			customer = new Customer(CustomerManagement.autoId++, "new", name, phoneNum);
			CustomerManagement.getCustomers().add(customer);
		}
		else {
			System.out.println("\t Khach hang da co trong danh sach: ");
			customer.display();
		}
		cart.display();
		// luu hoa don vua thanh toan
		Order order = new Order(Order.autoId++, customer.getId(), customer.getCode(), cart.totalCartMoney());
		OrderManagement.add(order);
		for(CartProduct cartProduct:cart.getCartProducts()) {
			// luu cac san pham da ban
			ProductInOrder inOrder = new ProductInOrder(ProductInOrder.autoId++ , cartProduct.getProductId(), order.getId(), cartProduct.getQuantity());
			int index = ProductManagement.findById(cartProduct.getProductId());
//			ProductInOrderManagement.getInOrders().get(index).addSoldQuantity(cartProduct.getQuantity());
			
			
			long price = (long) ProductManagement.getProductById(cartProduct.getProductId()).getPrice() * cartProduct.getQuantity();
			
			ProductManagement.getProducts().get(index).addSoldQuantity(cartProduct.getQuantity());
			ProductManagement.getProducts().get(index).addTotalIncome(new BigDecimal(price));
			
			ProductInOrderManagement.add(inOrder);
			
			// luu doanh thu cua cac mat hang vua ban
		}
		// luu doanh thu khach hang
		int cusIdx = CustomerManagement.finById(order.getCustomerId());
		CustomerManagement.getCustomers().get(cusIdx).addTotalCustomerIncome(new BigDecimal(cart.totalCartMoney()));
		
		
		// xoa gio hang cua khach vua thanh toan sau khi da luu hoa don
		cart = new Cart();
	}

// xoa san pham trong gio hang
	private static void deleteCartProduct() {
		System.out.println("__--Xoa san pham trong gio hang --__");
		System.out.println("\t nhap id san pham can xoa : ");
		int productId = Integer.parseInt(sc.nextLine());
		int index = cart.findCartProductById(productId);
		if(index == -1) {
			System.out.println("\t San [ham khong co trong gio hang !");
			return;
		}
		cart.getCartProducts().remove(index);
		return;
	}


	// chinh sua so luong
	private static void changeProductQunatity() {
		System.out.println("\t__Thay doi so luong san pham__");
		System.out.print("\t Nhap id san pham can thay doi	 : ");
		int productId = Integer.parseInt(sc.nextLine());
		// ktra
		int cartProductIndex = cart.findCartProductById(productId);
		if(cartProductIndex == -1) {
			System.out.println("\t san pham k co trong kho");
			return;
		}
		System.out.print("Nhap so luong can them :");
		int quantity = Integer.parseInt(sc.nextLine());
		quantity += cart.getCartProducts().get(cartProductIndex).getQuantity();
		
		if(quantity <= 0 || ProductManagement.getProductById(productId).getQuantity() < quantity) {
			System.out.println("so luong khong hop le !");
			return;
		}
		
		// cap nhat gio hang
		cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
		
		System.out.println("Da cap nhat sp!");
		return;
	}

// them vao gio hang
	private static void addToCart() {
		System.out.println("__Them san pham__");
		System.out.print("\t Nhap id san pham can them : ");
		int productId = Integer.parseInt(sc.nextLine());
		
		//ktra
		int productIndex = ProductManagement.findById(productId);
		if(productIndex == -1) {
			System.out.println("\t San pham khong co trong kho");
			return;
		}
		
		System.out.print("\t Nhap so luong can mua : ");
		int quantity = Integer.parseInt(sc.nextLine());
		if(quantity <= 0) {
			System.out.println("\t so luong khong hop le !");
			return;
		}
		
		int cartProductIndex = cart.findCartProductById(productId);
		if(cartProductIndex != -1) {
			quantity += cart.getCartProducts().get(cartProductIndex).getQuantity();
		}
		Product product = ProductManagement.getProductById(productId);
		
		if(quantity > product.getQuantity()) {
			System.out.println("\t Hang trong kho khong du !");
			return;
		}
		
		// cap nhat 
		
		if(cartProductIndex == -1) {
			cart.getCartProducts().add(new CartProduct(productId, quantity));
		}
		else {
			cart.getCartProducts().get(cartProductIndex).setQuantity(quantity);
		}
		System.out.println("Da them sp moi!");
		return;
		
	}
	
}
