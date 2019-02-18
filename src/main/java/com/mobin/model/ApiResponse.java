package com.mobin.model;

public class ApiResponse<T> {

    private long result_code;
 
    private String result_msg;
    
    private Object result_data;
    
    
  

	public ApiResponse(long result_code, String result_msg, Object result_data) {
        this.result_code = result_code;
        this.result_msg = result_msg;
        this.result_data = result_data;
    }
    
    
	

	public long getResult_code() {
		return result_code;
	}


	public void setResult_code(long result_code) {
		this.result_code = result_code;
	}


	public String getResult_msg() {
		return result_msg;
	}




	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
	}




	public Object getResult_data() {
		return result_data;
	}




	public void setResult_data(Object result_data) {
		this.result_data = result_data;
	}
}
