package vn.devpro.fashionStore.update.product;

import java.math.BigDecimal;
import java.util.Scanner;

import vn.devpro.fashionStore.update.category.Category;
import vn.devpro.fashionStore.update.category.CategoryManagement;

public class Product {
	private int id;
	private int categoryId;
	private String name;
	private int quantity;
	private double price;
	
	private int soldQuantity = 0;
	private BigDecimal totalProductIncome = new BigDecimal(0);
	
	public void addSoldQuantity(int n) {
		soldQuantity += n;
	}
	public void addTotalIncome(BigDecimal t) {
		totalProductIncome = totalProductIncome.add(t);
	}
	public int getSoldQuantity() {
		return soldQuantity;
	}
	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
	public BigDecimal getTotalProductIncome() {
		return totalProductIncome;
	}
	public void setTotalProductIncome(BigDecimal totalProductIncome) {
		this.totalProductIncome = totalProductIncome;
	}
	
	private static BigDecimal totalproductIncome = new BigDecimal(0);

	public void display() {
		
		Category category = CategoryManagement.getCategoryById(this.categoryId);
		System.out.printf("%5d %-20s %-30s %8d %,15.2f%n",
				this.id, category.getName(), this.name, this.quantity, this.price);
	}
	
	Scanner sc = new Scanner(System.in);
	public void edit() {
		
		
		do {
			this.display();
			System.out.println("---------------SUA THONG TIN HANG HOA--------------");
			System.out.println("Chon thong tin can sua");
			System.out.println("\t1. Sua loai hang (chon lai)");
			System.out.println("\t2. Sua ten hang hoa");
			System.out.println("\t3. Sua so luong hang hoa");
			System.out.println("\t4. Sua don gia hang hoa");
			System.out.println("\t0. Quay lai");

			System.out.print("Lua chon cua ban: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1: editCategoryId(); break; 
			case 2: editName(); break; 
			case 3: editQuantity(); break; 
			case 4: editPrice(); break; 
			case 0: return;
			default:
				System.out.println("Lua chon khong hop le");
			}
		} while (true);
	}
	
	public void editPrice() {
		System.out.print("\tNhap don gia: ");
		double price = Double.parseDouble(sc.nextLine());
		if (price < 0) {
			System.out.println("\tDon gia khong duoc la so am");
			return;
		}
		this.setPrice(price);
	}
	
	public void editQuantity() {
		System.out.print("\tNhap so luong: ");
		int quantity = Integer.parseInt(sc.nextLine());
		if (quantity < 0) {
			System.out.println("\tSo luong khong duoc la so am");
			return;
		}
		this.setQuantity(quantity);
	}
	
	private void editName() {
		
		System.out.print("\tNhap ten hang hoa moi: ");
		String name = sc.nextLine();
		//Kiem tra tinh hop le
		if (name.isEmpty()) {
			System.out.println("\tTen khong duoc de trong");
			return;
		}

		//Kiem tra ten da ton tai trong danh sach
		if (ProductManagement.findByName(name) != -1) {//Ten da ton tai
			System.out.println("\tTen '" + name + "' da co trong danh sach");
			return;
		}
		this.setName(name);
	}

	private void editCategoryId() {
		System.out.print("\tNhap (chon) ma LH (loai hang) moi: ");
		int categoryId = Integer.parseInt(sc.nextLine());
		
		// ktra id vau nhap co trong danh sach loai hang hay khong
		if (CategoryManagement.finById(categoryId) == -1) {
			System.out.println("\tLoai hang khong ton tai");
			return;
		}
		this.setCategoryId(categoryId);
	}



	public Product() {
		super();
	}
	public Product(int id, int categoryId, String name, int quantity, double price) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public BigDecimal getTotalproductIncome() {
		return totalproductIncome;
	}
	public void setTotalproductIncome(BigDecimal totalproductIncome) {
		Product.totalproductIncome = totalproductIncome;
	}
}
