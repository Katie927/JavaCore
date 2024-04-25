package vn.devpro.fashionStore.statistic.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManagement {
	static Scanner sc = new Scanner(System.in);
	
	private static List<Order> orders = new ArrayList<Order>();
	
	public static void add(Order order) {
		orders.add(order);
	}

// hien thi danh sach hoa don da ban
	public static void display() {
		System.out.println("\t ____Danh sach hoa don da ban : ");
		System.out.printf("%7s %-4s %-13s %18s\n", "Ma HD", "Ma KH", "Code", "Tien");
		for(Order i:orders) {
			System.out.printf("%7d %-4d %-13s %18.2f\n", i.getId(), i.getCustomerId(), i.getCode(), i.getTotal());
		}
	}
	
// xoa  mot don hang khoi danh sach\
	public static void remove() {
		System.out.println("\t ___Xoa don hang khoi danh sach : ");
		System.out.print("\t Nhap ma don hang can xoa : ");
		int orderId = Integer.parseInt(sc.nextLine());
		int index = getOrderById(orderId);
		if(index == -1) {
			System.out.println("\t Don hang khong co trong danh sach!");
			return;
		}
		orders.remove(index);
		System.out.println("\t Xoa don hang thanh cong !");
		return;
	}
	
	public static int getOrderById(int orderId) {
		for(int i=0 ; i<orders.size() ; i++) {
			if(orders.get(i).getId() == orderId) {
				return i;
			}
		}
		return -1;
	}
	
// tinh tong doanh thu 	
	public static BigDecimal totalIncome() {
		BigDecimal totalIn = new BigDecimal(0);
		for(Order i:orders) {
			BigDecimal t = new BigDecimal(i.getTotal());
			totalIn = totalIn.add(t);
		}
		return totalIn;
	}

}
