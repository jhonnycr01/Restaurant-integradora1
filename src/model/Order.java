package model;

import java.sql.Date;
import java.util.ArrayList;

public class Order {

	private String orderCode;
	private Date date;
	private Date time;
	private String clientCode;
	private String resturantNit;
	private String orderState;
	private ArrayList <String> productList;
	
	public Order(String orderCode, Date date, Date time, String clientCode, String resturantNit,
			ArrayList<String> productList) {
		this.orderCode = orderCode;
		this.date = date;
		this.time = time;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
