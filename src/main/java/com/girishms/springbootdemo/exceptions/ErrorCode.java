package com.girishms.springbootdemo.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	Entity_Not_Found(HttpStatus.NOT_FOUND.value(), "Entity not found", HttpStatus.NOT_FOUND),
    USER_NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "User Not Authorized", HttpStatus.UNAUTHORIZED),
	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad Request", HttpStatus.BAD_REQUEST),
	USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), "User already exist", HttpStatus.BAD_REQUEST),
	SOMETHING_WENT_WRONG(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR),
	JSON_MAPPING_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Json mapping exception", HttpStatus.INTERNAL_SERVER_ERROR),
	S3_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception while fetching file from S3", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), "Expired or invalid access token is provided", HttpStatus.BAD_REQUEST);
    private int code;
    private String message;
    private HttpStatus status;
    
    ErrorCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
}
