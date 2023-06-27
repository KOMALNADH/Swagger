package com.pack.swagger.exception;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String description;
	public ErrorDetails(Date timestamp, String message, String description) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}
	
}
