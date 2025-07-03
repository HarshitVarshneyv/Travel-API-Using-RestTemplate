package com.myBizTravel.API.exception.all;

public class MyBizApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MyBizApiException(String msg) {
		super(msg);
	}
	
	// This pattern, known as exception chaining, is crucial for debugging and understanding the root cause.
	public MyBizApiException(String msg , Throwable cause) {
		super(msg,cause);
	}
}
