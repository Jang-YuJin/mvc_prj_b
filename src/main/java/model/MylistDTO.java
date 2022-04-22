package model;

import java.sql.Date;

public class MylistDTO {
	private String mylist_id;
	private String id;
	private String code;
	private Date order_date;
	private int sort;
	private String sort_name;
	private String product_name;
	private int price;

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSort_name() {
		return sort_name;
	}
	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public MylistDTO() {
	}
	public String getMylist_id() {
		return mylist_id;
	}
	public void setMylist_id(String mylist_id) {
		this.mylist_id = mylist_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}
