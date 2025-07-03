package com.myBizTravel.API.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.myBizTravel.API.exception.all.MyBizApiException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MyBizApiException.class)
	public ResponseEntity<String> handleMyBizApiException(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
