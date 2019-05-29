package cn.enilu.flash.bean.exception;

import java.util.List;

public class SlConnectException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private List<String> details;
	
	public SlConnectException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
