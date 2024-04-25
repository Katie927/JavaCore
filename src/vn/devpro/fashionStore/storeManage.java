package vn.devpro.fashionStore;

import java.util.Scanner;

import vn.devpro.fashionStore.sale.saleManagement;
import vn.devpro.fashionStore.statistic.StatisticManagement;
import vn.devpro.fashionStore.update.updateManagement;
import vn.devpro.fashionStore.update.category.CategoryManagement;
import vn.devpro.fashionStore.update.customer.CustomerManagement;
import vn.devpro.fashionStore.update.product.ProductManagement;

public class storeManage {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		CategoryManagement.init();
		CustomerManagement.init();
		ProductManagement.init();
		
		do {
			System.out.println("__________________________________________________");
			System.out.println("----------------QUAN LY BAN HANG------------------");
			System.out.println("chon chuc nang : ");
			System.out.println("\t1 - Cap nhat thong tin he thong \n"
							+  "\t2 - Quan ly giao dich khach hang \n"
							+  "\t3 - Thong ke don hang va doanh thu \n");
			System.out.print("Lua chon : ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1: {
				updateManagement.execute();
				break;//cap nhat thong tin he thong
			}
			case 2: {
				saleManagement.execute();
				break;//quan ly ban hang
			}
			case 3: {
				StatisticManagement.execute();
				break;//thong ke
			}
			case 0: {
				System.exit(0);
			}
			default:
				System.out.println("lua chon khong hop le!");
			}
		}while(true);
	}
}
