package com.mobin.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PageVO implements Serializable{
	public int start_p;	
	public int end_p;
	public int count;
	
	public String ymd;
	
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStart_p() {
		return start_p;
	}
	public void setStart_p(int start_p) {
		this.start_p = start_p;
	}
	public int getEnd_p() {
		return end_p;
	}
	public void setEnd_p(int end_p) {
		this.end_p = end_p;
	}	
}
 