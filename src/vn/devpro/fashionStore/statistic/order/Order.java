package vn.devpro.fashionStore.statistic.order;

import java.util.Scanner;

public class Order {
	static Scanner sc = new Scanner(System.in);
	
	public static int autoId = 1;
	private int id;
	private int customerId;
	private String Code;
	private double total;
	
	public Order() {
		super();
	}
	public Order(int id, int customerId, String code, double total) {
		super();
		this.id = id;
		this.customerId = customerId;
		Code = code;
		this.total = total;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
