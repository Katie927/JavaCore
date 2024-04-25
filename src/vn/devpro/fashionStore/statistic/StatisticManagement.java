package vn.devpro.fashionStore.statistic;

import java.util.Scanner;

import vn.devpro.fashionStore.statistic.order.OrderManagement;
import vn.devpro.fashionStore.statistic.productInOrder.ProductInOrderManagement;
import vn.devpro.fashionStore.update.customer.CustomerManagement;

public class StatisticManagement {
	static Scanner sc = new Scanner(System.in);
	
	public static void execute() {
		System.out.println("\n_____________THONG KE BAN HANG____________");
		System.out.println("Chon chuc nang : ");
		System.out.println("\t1 - Hien thi danh sach hoa don");
		System.out.println("\t2 - Xoa mot don hang khoi danh sach");
		System.out.println("\t3 - Tong doang thu tat ca hoa don");
		System.out.println("\t4 - Tong so tien thu duoc theo khach hang");
		System.out.println("\t5 - Tong so tien thu duoc theo san pham da ban");
		System.out.println("\t0 - Quay lai");
		
		do {
			
			System.out.println("Lua chon cua ban: ");
			
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				OrderManagement.display();
				break;
			case 2: 
				OrderManagement.remove();
				break;
			case 3: 
				System.out.print("\t __ Tong doang thu ban hang : ");
				System.out.println(OrderManagement.totalIncome().toString());
				break;
			case 4:
				displayCustomerIncome();
				break;
			case 5: 
				ProductInOrderManagement.display();
				break;
			case 0:	return;
			
			default:
				System.out.println("Lua chon khong hop le !");
			}
		} while(true);
	}
	
	// hien thi doanh thu theo san pham
	public static void displayCustomerIncome() {
		System.out.println("____Tong so tien thu duoc theo tung khach hang ");
		System.out.printf("%3s %29s %18s\n", "STT","Ten khach hang", "Doanh thu");
		for(int i=0 ; i<CustomerManagement.getCustomers().size() ; i++) {
			System.out.printf("%3d %29s %18s\n", i+1, 
					CustomerManagement.getCustomers().get(i).getName(),
					CustomerManagement.getCustomers().get(i).getTotalCustometIncome().toString());
		}
	}
	
	
	
}
