package vn.devpro.fashionStore.update.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class CustomerManagement {

	static Scanner sc = new Scanner(System.in);
	public static int autoId = 1;
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	
	public static void init() {
		customers.add(new Customer(autoId++, "DTD", "Dang Tien Dung", "0912333333"));
		customers.add(new Customer(autoId++, "VHG", "Vu Huong Giang", "0912333334"));
		customers.add(new Customer(autoId++, "ADV", "A du Vjp", "0912333335"));
	}
	
	public static void execute() {
		
		System.out.println("\n_________CAP NHAT THONG TIN KHACH HANG____________");
		System.out.println("Chon chuc nang cap nhat : ");
		System.out.println("\t1 - Hien thi danh sach khach hang");
		System.out.println("\t2 - Them moi khach hang");
		System.out.println("\t3 - Sua thong tin khach hang");
		System.out.println("\t4 - Xoa thong tin khach hang");
		System.out.println("\t5 - Sap xep danh sach");
		System.out.println("\t0 - Quay lai");
		
		do {
			
			System.out.print("Lua chon cua ban: ");
			
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1: display();	break;
			case 2: add(); 		break;
			case 3: edit(); 	break;
			case 4: remove(); 	break;
			case 5: sort(); 	break;
			case 0:	return;
			
			default:
				System.out.println("Lua chon khong hop le !");;
			}
		} while(true);
		
	}
	
	
	public static ArrayList<Customer> getCustomers() {
		return customers;
	}
	

	private static void display() {
		System.out.println("________Danh sach khach hang_________");
		System.out.printf("%5s %-7s %-30s %11s\n", "ID", "Code", "Ten khach hang", "So dien thoai");
		for(int i=0 ; i<customers.size() ; i++) {
			customers.get(i).display();
		}
		System.out.println("______________________________________");
	}
	
	
	// them khach hang mopi
	private static void add() {
		System.out.println("\n ---------Them khach hang hang moi vao danh sach -----------");
		System.out.print("\t Nhap so dien thoai khach : ");
		
		// kiem tra so dien thoai khach
		String phoneNum = sc.nextLine();
		// xoa tat ca khoang trang trong so dien thoai 
		phoneNum.replaceAll(" ", "");
		if(checkPhoneNum(phoneNum) != -1) {
			System.out.println("\t Khach hang da co trong danh sach : ");
			customers.get(checkPhoneNum(phoneNum)).display();
			return;
		}
		
		
		System.out.print("\tNhap ten khach hang moi : ");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("\tTen khong duoc de trong!");
			return;
		}
		
		Customer newCustomer = new Customer(autoId++, creatCode(name), name, phoneNum);
		customers.add(newCustomer);
		System.out.println("\tThem loai hang moi thanh cong");
		System.out.println("______________________________________");
	}
	
	// sua thong tin khach hang
	private static void edit() {
		System.out.println("\n ---------Sua thong tin khach hang hang -----------");
		System.out.print("\t Nhap ma khach hang hang can sua:");
		int id = Integer.parseInt(sc.nextLine());
		
		int index = finById(id);
		if(index == -1) {
			System.out.println("\t khach hang khong co trong danh sach!");
			return;
		}
		
		System.out.println("\tNhap ten khach hang moi : ");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("\tTen khong duoc de trong!");
		}
		if(findByName(name) != -1 && findByName(name) != index) {
			System.out.println("\tKhach hang '"+name+"' da co trong danh sach!");
			return;
		}
		
		customers.get(index).setName(name);
		System.out.println("\tThem khach hang moi thanh cong");
	}

	private static void remove() {
		System.out.println("\n________Xoa thong tin khach hang hang_________");
		
		System.out.print("\t Nhap so dien thoai khach hang can xoa :");
		String phoneNum = sc.nextLine();
		
		int index = checkPhoneNum(phoneNum);
		if(index == -1) {
			System.out.println("\tKhach hang khong co trong danh sach");
			return;
		}
		
		//  kiem tra lai thong tin can xoa
		System.out.println("Thong tin can xoa :");
		customers.get(index).display();
		while(true) {
			System.out.println("xoa? (y/n)");
			String c = sc.nextLine();
			if(c.compareToIgnoreCase("y") == 0) { //Co -> Xoa khoi danh sach
				customers.remove(index);
				System.out.println("\t Xoa thanh cong!");
				System.out.println("____________________________");
				return;
			}
			else if(c.compareToIgnoreCase("n") == 0) {
				return;
			}
		}
		
	}

	private static void sort() {
		Collections.sort(customers, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		
		System.out.println("\t Da sap xep danh sach theo ten khach hang!");
	}
	
	
	
	
	//tim theo ten
	public static int findByName(String name) {
		for(int i=0 ; i<customers.size() ; i++) {
			if(name.equals(customers.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}
	
	// tim theo id
	public static int finById(int id) {
		for(int i=0 ; i<customers.size() ; i++) {
			if(id == customers.get(i).getId()) {
				return i;
			}
		}
		return -1;
	}
	
	// tim kiem theo ten
	public static Customer getCustomertById(int id) {
		for(Customer customer : customers) {
			if(customer.getId() == id) {
				return customer;
			}
		}
		return new Customer();
	}
	
	//lay cac ky tu dau cua ten lam code
	public static String creatCode(String name) {
		String[] words = name.split(" ");
	    String initials = "";
        for (String word : words) {
        	initials += word.charAt(0);
        }
        return initials.toUpperCase();
	}
	
	// tim theo sdt
	public static int checkPhoneNum(String phoneNum) {
		phoneNum.replaceAll(" ", "");
		for(int i=0 ; i<customers.size() ; i++) {
			if(phoneNum.equals(customers.get(i).getPhoneNumber())) {
				return i;
			}
		}
		return -1;
	}
	
	// lay thong tin khach hang bang sdt
	public static Customer getCustomerByPhone(String phoneNum) {
		for(Customer customer : customers) {
			if(customer.getPhoneNumber().equals(phoneNum)) {
				return customer;
			}
		}
		return null;
	}
}
