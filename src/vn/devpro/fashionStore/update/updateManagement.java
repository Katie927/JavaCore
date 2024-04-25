package vn.devpro.fashionStore.update;

import java.util.Scanner;

import vn.devpro.fashionStore.update.category.CategoryManagement;
import vn.devpro.fashionStore.update.customer.CustomerManagement;
import vn.devpro.fashionStore.update.product.ProductManagement;

public class updateManagement {
	static Scanner sc = new Scanner(System.in);
	
	public static void execute() {
		
		do {
			System.out.println("\n__________CAP NHAT THONG TIN HE THONG____________");
			System.out.println("Chon chuc nang cap nhat : ");
			System.out.println("\t1 - Cap nhat thong tin loai hang");
			System.out.println("\t2 - Cap nhat danh sach khach hang");
			System.out.println("\t3 - Cap nhat danh sach san pham");
			System.out.println("\t0 - Quay lai");
			
			System.out.print("Nhap lua chon : ");
			
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1: {
				CategoryManagement.execute();
				break;
			}
			case 2: {
				CustomerManagement.execute();
				break;
			}
			case 3: {
				ProductManagement.execute();
				break;
			}
			case 0: {
				return ;
			}
			default:
				System.out.println("Lua chon khong hop le !");;
			}
		} while(true);
	}
}
