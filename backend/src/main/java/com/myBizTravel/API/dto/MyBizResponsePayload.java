package com.myBizTravel.API.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBizResponsePayload {

	private String redirectUrl;
	private String status;
	private int statusCode;
	private String responseCode;
	private String message;
	
}
