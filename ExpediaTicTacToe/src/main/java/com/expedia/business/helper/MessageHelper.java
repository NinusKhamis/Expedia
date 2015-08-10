package com.expedia.business.helper;

public class MessageHelper {
    private String status;    
    
	public void setMessage(String status){
		this.status = status;
	}

    public String getMessage(){
    	if(this.status==null) return "";
    	else return this.status; 
	}    

    public void appendMessage(String status) {
		if(this.status == null && !status.isEmpty())
			this.status = status + "<br>" ;
		else if(!status.isEmpty())
			this.status += status + "<br>";
	}   
    
}