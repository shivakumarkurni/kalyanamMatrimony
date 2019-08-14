package com.hcl.matrimony.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class ErrorResponse {
	
	private String message;
	private int statusCode;

}