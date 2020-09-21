package com.neosoft.webflux.reactive.responseCode;

public class ResponseCode {
	
	private String message;
	private Integer statusCode;
	public ResponseCode(String message, Integer statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "ResponseCode [message=" + message + ", statusCode=" + statusCode + "]";
	}
	
	

}
