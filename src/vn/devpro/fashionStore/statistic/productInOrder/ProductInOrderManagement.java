package vn.devpro.fashionStore.statistic.productInOrder;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.fashionStore.update.product.Product;
import vn.devpro.fashionStore.update.product.ProductManagement;

	public class ProductInOrderManagement {
	private static List<ProductInOrder> inOrders = new ArrayList<ProductInOrder>();
	
	public static void add(ProductInOrder inOrder) {
		inOrders.add(inOrder);
	}
	
// hien thi danh sach hoa don
	public static void display() {
		System.out.println("\t Danh sach mat hang da ban : ");
		System.out.printf("%4s %-29s %-13s %18s\n", "STT", "Ten san pham", "Da ban", "Doanh thu");
		int i=1;
		for(Product in:ProductManagement.getProducts()) {
			System.out.printf("%4d %-29s %-13d %18s\n", i++,
								in.getId(),
								in.getSoldQuantity(), in.getTotalProductIncome().toString());
		}
	}
	
	public static List<ProductInOrder> getInOrders() {
		return inOrders;
	}
}
