package com.mobin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthToken {
	
	private Long sid;
	private String token;
    private String seller_flag;
    
    @JsonIgnore
    private String passwd;
    private String nickname;
    

	



   


	public AuthToken(String token, String seller_flag,Long sid){
    	this.sid = sid;
        this.token = token;
        this.seller_flag = seller_flag;
    }



	public AuthToken(){

    }
    
	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
    public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
    public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSeller_flag() {
		return seller_flag;
	}

	public void setSeller_flag(String seller_flag) {
		this.seller_flag = seller_flag;
	}
	
    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
