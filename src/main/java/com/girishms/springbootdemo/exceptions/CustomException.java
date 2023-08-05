package com.girishms.springbootdemo.exceptions;

public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    private String description;
    
    public CustomException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.description = description;
    }
    
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    
    public String getDescription() {
        return description;
    }
}
