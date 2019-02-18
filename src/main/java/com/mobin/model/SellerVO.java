package com.mobin.model;

public class SellerVO {
	private Long uid;
	private String id;
	private String passwd;
	private String seller_flag;
	private String app_passwd;
	private String nickname;
	private String app_token;
	
	public String getApp_token() {
		return app_token;
	}
	public void setApp_token(String app_token) {
		this.app_token = app_token;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getApp_passwd() {
		return app_passwd;
	}
	public void setApp_passwd(String app_passwd) {
		this.app_passwd = app_passwd;
	}
	public String getSeller_flag() {
		return seller_flag;
	}
	public void setSeller_flag(String seller_flag) {
		this.seller_flag = seller_flag;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
 
}
