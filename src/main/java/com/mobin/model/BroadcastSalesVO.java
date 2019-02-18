package com.mobin.model;

public class BroadcastSalesVO {
	private Long bid;
	private Long pid;
	
	private String product_name;
	private String product_img;
	private String broadcast_start;
	private String broadcast_end;
	private String startymd;
	private String endymd;	
	private String total_price;
	private String order_product;
	
	
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public String getBroadcast_start() {
		return broadcast_start;
	}
	public void setBroadcast_start(String broadcast_start) {
		this.broadcast_start = broadcast_start;
	}
	public String getBroadcast_end() {
		return broadcast_end;
	}
	public void setBroadcast_end(String broadcast_end) {
		this.broadcast_end = broadcast_end;
	}
	public String getStartymd() {
		return startymd;
	}
	public void setStartymd(String startymd) {
		this.startymd = startymd;
	}
	public String getEndymd() {
		return endymd;
	}
	public void setEndymd(String endymd) {
		this.endymd = endymd;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getOrder_product() {
		return order_product;
	}
	public void setOrder_product(String order_product) {
		this.order_product = order_product;
	}
}
