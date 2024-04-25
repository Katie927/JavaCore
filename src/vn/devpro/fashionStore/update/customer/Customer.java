package vn.devpro.fashionStore.update.customer;

import java.math.BigDecimal;

public class Customer {
	private int id;
	private String code;
	private String name;
	private String phoneNumber;
	private BigDecimal totalCustometIncome;
	
	public void display() {
		System.out.printf("%5d %-7s %-30s %11s\n", this.id, this.code, this.name, this.phoneNumber);
	}
	public void addTotalCustomerIncome(BigDecimal v) {
		totalCustometIncome = totalCustometIncome.add(v);
	}
	
	public Customer() {
		super();
	}
	public Customer(int id, String code, String name, String phoneNumber) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.totalCustometIncome = new BigDecimal(0);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public BigDecimal getTotalCustometIncome() {
		return totalCustometIncome;
	}
	public void setTotalCustometIncome(BigDecimal totalCustometIncome) {
		this.totalCustometIncome = totalCustometIncome;
	}

}
