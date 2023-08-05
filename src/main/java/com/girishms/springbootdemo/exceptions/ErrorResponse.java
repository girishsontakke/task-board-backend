package com.girishms.springbootdemo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String description;
    
    public ErrorResponse(int status, String message) {
    	this.status = status;
    	this.message = message;
    }
}
