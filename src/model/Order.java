package model;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Order {

	private String orderCode;
	private LocalDateTime date;
	private String clientCode;
	private String resturantNit;
	private String orderState;
	private ArrayList <String> productList;
	
	public Order(String orderCode, String clientCode, String resturantNit,
			ArrayList<String> productList) {
		this.orderCode = orderCode;
		this.date = LocalDateTime.now();
		this.clientCode = clientCode;
		this.resturantNit = resturantNit;
		this.productList = productList;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getResturantNit() {
		return resturantNit;
	}

	public void setResturantNit(String resturantNit) {
		this.resturantNit = resturantNit;
	}

	public ArrayList<String> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
}