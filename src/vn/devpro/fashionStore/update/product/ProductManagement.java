package vn.devpro.fashionStore.update.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.fashionStore.update.category.CategoryManagement;

public class ProductManagement {
	public static int autoId = 1;
	static Scanner sc = new Scanner(System.in);
	
	private static ArrayList<Product> products = new ArrayList<Product>();


	public static void init() {
		products.add(new Product(autoId++, 2, "ao somi nu", 33, 324441));	
		products.add(new Product(autoId++, 5, "ao khoac bong", 10, 420000));
		products.add(new Product(autoId++, 1, "ao vest", 21, 34122221));
		products.add(new Product(autoId++, 5, "Ao khoac nhe", 13, 132222));
		products.add(new Product(autoId++, 4, "ao phong", 33, 324441));
		products.add(new Product(autoId++, 1, "quan vest", 33, 324441));
		products.add(new Product(autoId++, 4, "Quan dui", 33, 324441));
	}
	
	public static void execute() {
		
		do {
			System.out.println("\n_________CAP NHAT THONG TIN HANG HOA____________");
			System.out.println("Chon chuc nang cap nhat : ");
			System.out.println("\t1 - Hien thi danh sach hang hoa");
			System.out.println("\t2 - Them moi hang hoa");
			System.out.println("\t3 - Sua thong tin hang hoa");
			System.out.println("\t4 - Xoa thong tin hang hoa");
			System.out.println("\t5 - Sap xep danh sach");
			System.out.println("\t6 - Tim kiem theo chung loai");
			System.out.println("\t7 - Tim kiem theo ten");
			System.out.println("\t0 - Quay lai");
			System.out.print("Lua chon cua ban: ");
			
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1: display();	break;
			case 2: add(); 		break;
			case 3: edit(); 	break;
			case 4: remove(); 	break;
			case 5: sort(); 	break;
			case 6: findByCategoryName(); 	break;
			case 7: findByName();			break;
			case 0:	return;
			
			default:
				System.out.println("Lua chon khong hop le !");
			}
		} while(true);
	}

	private static void findByName() {
		System.out.print("Nhnap ten hag can tim: ");
		String productName = sc.nextLine();
		
		for(Product i:products) {
			if(i.getName().toLowerCase().contains(productName.toLowerCase())){
				i.display();
			}
		}
		
	}

	private static void findByCategoryName() {
		System.out.print("Ten loai hang can tim: ");
		String categoryName = sc.nextLine();
		System.out.println("Danh sach hang thuoc loai : "+ categoryName +" : ");
		for(Product i:products){
			if(CategoryManagement.getCategoryById(i.getCategoryId()).getName().equalsIgnoreCase(categoryName)) {
				i.display();
			}
		}
		
	}

	private static void sort() {
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		System.out.println("\t Da sap xep theo ten hang ");
	}

	private static void remove() {
		// TODO Auto-generated method stub
		System.out.println("\n----------Xoa thong tin hang hoa------------");
		
		System.out.print("\tNhap ma hang hoa can xoa: ");
		int id = Integer.parseInt(sc.nextLine());
		
		//Tim xem co trong ds khong?
		int index = findById(id);
		if (index == -1) { //Khong co
			System.out.println("\tHang hoa khong co trong DS");
			return;
		}
		
		//  kiem tra lai thong tin can xoa
		products.get(index).display();
		while(true) {
			System.out.println("xoa? (y/n)");
			String c = sc.nextLine();
			if(c.compareToIgnoreCase("y") == 0) {//Co -> Xoa khoi danh sach
				products.remove(index);
				System.out.println("\t Xoa thanh cong!");
				return;
			}
			else if(c.compareToIgnoreCase("n") == 0) {
				return;
			}
		}
	}

	private static void edit() {
		System.out.println("\n----------Sua thong tin hang hoa------------");
		System.out.print("\tNhap ma hang hoa can sua: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findById(id);
		if (index == -1) { 
			System.out.println("\tHang hoa khong co trong DS");
			return;
		}
		products.get(index).edit();
		
		System.out.println("______________________________________________");
	}

	private static void add() {
		System.out.println("\n----------Them hang hoa moi vao danh sach------------");
		
		System.out.print("\tNhap (chon) ma LH (loai hang): ");
		int categoryId = Integer.parseInt(sc.nextLine());
		//Kiem tra categoryId vua nhap co trong ds loai hang khong?
		if (CategoryManagement.finById(categoryId) == -1) {
			System.out.println("\tLoai hang khong ton tai");
			return;
		}
		System.out.println(CategoryManagement.getCategoryById(categoryId).getName());
		
		System.out.print("\tNhap ten hang hoa moi: ");
		String name = sc.nextLine();
		//Kiem tra tinh hop le
		if (name.isEmpty()) {
			System.out.println("\tTen khong duoc de trong");
			return;
		}
		
		if (findByName(name) != -1) {//Ten da ton tai
			System.out.println("\tTen '" + name + "' da co trong danh sach");
			return;
		}
		System.out.print("\tNhap so luong: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if (quantity < 0) {
			System.out.println("\tSo luong khong duoc la so am");
			return;
		}
		
		System.out.print("\tNhap don gia: ");
		double price = Double.parseDouble(sc.nextLine());
		if (price < 0) {
			System.out.println("\tDon gia khong duoc la so am");
			return;
		}
		
		
		Product product = new Product(autoId++, categoryId, name, quantity, price);
		products.add(product);
		System.out.println("\tThem moi hang hoa thanh cong!");
		System.out.println("______________________________________________");
	}

	private static void display() {
		System.out.println("\n-------------DANH SACH HANG HOA-----------");
		
		System.out.printf("%5s %-20s %-30s %8s %-15s%n",
				"Ma HH", "Ten Loai hang", "Ten hang hoa", "So luong", "Don gia");
		for (Product product : products) {
			product.display();
		}	
		System.out.println("______________________________________________");
	}
	
	
	
	//Ham tim kiem ten loai hang da ton tai hay chua
	public static int findByName(String name) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}
	
	//Ham tim kiem LH theo id
	public static int findById(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public static Product getProductById(int id) {
		for(Product product : products) {
			if(product.getId() == id) {
				return product;
			}
		}
		return new Product();
	}
	
	
	public static ArrayList<Product> getProducts() {
		return products;
	}
	public static void setProducts(ArrayList<Product> products) {
		ProductManagement.products = products;
	}
}
