package com.mobin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SellerPushVO {
	private Long push_id;
	private Long sid;
	@JsonIgnore
	private Long uid;
	
	private String push_message;
	private String push_flag;
	
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getPush_id() {
		return push_id;
	}
	public void setPush_id(Long push_id) {
		this.push_id = push_id;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getPush_message() {
		return push_message;
	}
	public void setPush_message(String push_message) {
		this.push_message = push_message;
	}
	public String getPush_flag() {
		return push_flag;
	}
	public void setPush_flag(String push_flag) {
		this.push_flag = push_flag;
	}

}
