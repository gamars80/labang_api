package com.mobin.model;

public class ApiListResponse<T> {
	private int status;
    private int pageNo;
    
    private String message;
    
    private boolean more;
    
    private Object result;
    
    
 
    public ApiListResponse(int status, String message,int pageNo,boolean more, Object result) {
        this.status = status;
        this.message = message;
        this.pageNo = pageNo;
        this.more 	= more;
        this.result = result;
    }

    public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public boolean isMore() {
		return more;
	}

	public void setMore(boolean more) {
		this.more = more;
	}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
