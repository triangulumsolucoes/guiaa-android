package triangulum.com.guiaa.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Error implements Serializable{

	private String error;
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}
