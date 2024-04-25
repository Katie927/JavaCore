package vn.devpro.fashionStore.update.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CategoryManagement {
	
	static Scanner sc = new Scanner(System.in);
	public static int autoId = 1;
	private static ArrayList<Category> categories = new ArrayList<Category>();
	
	public static void init() {
		categories.add(new Category(autoId++, "CS", "Cong so nam"));
		categories.add(new Category(autoId++, "CS", "Cong so nu"));
		categories.add(new Category(autoId++, "TT", "The thao"));
		categories.add(new Category(autoId++, "HN", "Hang ngay"));
		categories.add(new Category(autoId++, "MD", "Mua dong"));
		categories.add(new Category(autoId++, "MH", "Mua he"));
	}
	
	public static void execute() {
		System.out.println("\n___________CAP NHAT THONG TIN LOAI HANG____________");
		System.out.println("Chon chuc nang cap nhat : ");
		System.out.println("\t1 - Hien thi danh sach loai hang");
		System.out.println("\t2 - Them moi loai hang hoa");
		System.out.println("\t3 - Sua thong tin loai hang");
		System.out.println("\t4 - Xoa thong tin loai hang");
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
			case 0: return ;
			
			default:
				System.out.println("Lua chon khong hop le !");;
			}
		} while(true);
	}

	private static void sort() {
		Collections.sort(categories, new Comparator<Category>() {
			@Override
			public int compare(Category o1, Category o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		
		System.out.println("\t Da sap xep danh sach loai hang theo ten");
		System.out.println("____________________________");
	}

	private static void remove() {
		System.out.println("\n________Xoa loai hang_________");
		
		System.out.print("\t Nhap ma loai hang can xoa :");
		int id = Integer.parseInt(sc.nextLine());
		
		
		// kiem tra thong tin can xoa
		int index = finById(id);
		if(index == -1) {
			System.out.println("\tLoai hang khong co trong danh sach");
			return;
		}
		
		//  kiem tra lai thong tin can xoa
		System.out.println("Loai hang can xoa :");
		categories.get(index).display();
		while(true) {
			System.out.println("xoa? (y/n)");
			String c = sc.nextLine();
			if(c.compareToIgnoreCase("y") == 0) { //Co -> Xoa khoi danh sach
				categories.remove(index);
				System.out.println("\t Xoa thanh cong!");
				System.out.println("____________________________");
				return;
			}
			else if(c.compareToIgnoreCase("n") == 0) {
				return;
			}
		}
		
	}

	private static void edit() {
		System.out.println("\n ----------Sua thong tin loai hang -----------");
		System.out.print("\t Nhap ma loai hang can sua:");
		int id = Integer.parseInt(sc.nextLine());
		
		// kiem tra hang can sua co trong danh sach khong?
		int index = finById(id);
		if(index == -1) {
			System.out.println("\t loai hang khong co trong danh sach!");
			return;
		}
		
		System.out.println("\tNhap ten loai hang moi : ");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("\tTen khong duoc de trong!");
		}
		if(findByName(name) != -1 && findByName(name) != index) {
			System.out.println("\tTen '"+name+"' da co trong danh sach!");
			return;
		}
		
		categories.get(index).setName(name);
		categories.get(index).setCode(creatCode(name));
		
		System.out.println("\t Sua loai hang thanh cong");
		System.out.println("____________________________");
	}

	private static void add() {
		System.out.println("___--Them loai hang moi vao danh sach---___");
		System.out.print("\t\tNhap ten loai hang moi :");
		String name = sc.nextLine();
		if(name.isEmpty()) {
			System.out.println("\t Ten khong duoc de trong !");
			return;
		}
		if(findByName(name) != -1) {
			System.out.println("\tTen '"+name+"' da co trong danh sach!");
			return;
		}
		Category newCategory = new Category(autoId++, creatCode(name), name);
		categories.add(newCategory);
		System.out.println("\tThem loai hang moi thanh cong");
		System.out.println("____________________________________");
	}

	private static void display() {
		System.out.println("__________Danh sach loai hang_________");
		System.out.printf("%5s %-6s %-30s\n", "Id", "Code", "Ten loai");
		for(int i=0 ; i<categories.size() ; i++) {
			categories.get(i).display();
		}
		System.out.println("______________________________________");
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
	
	// tim kiem loai hang theo ten
	public static int findByName(String name) {
		for(int i=0 ; i<categories.size() ; i++) {
			if(name.equals(categories.get(i).getName())) {
				return i;
			}
		}
		return -1;
	}
	
	// tim loai hang theo id
	public static int finById(int id) {
		for(int i=0 ; i<categories.size() ; i++) {
			if(id == categories.get(i).getId()) {
				return i;
			}
		}
		return -1;
	}
	
	public static Category getCategoryById(int id) {
		for(Category i:categories) {
			if(i.getId() == id) {
				return i;
			}
		}
		return new Category();
	}
}
