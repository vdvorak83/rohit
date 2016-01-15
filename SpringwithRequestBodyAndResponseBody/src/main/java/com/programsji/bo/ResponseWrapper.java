package com.programsji.bo;

public class ResponseWrapper {

	String statuscode;
	String message;
	String description;

	public ResponseWrapper(String statuscode, String message, String description) {
		this.statuscode = statuscode;
		this.message = message;
		this.description = description;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ResponseWrapper [statuscode=" + statuscode + ", message="
				+ message + ", description=" + description + "]";
	}
}
