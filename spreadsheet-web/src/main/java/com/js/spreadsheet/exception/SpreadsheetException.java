package com.js.spreadsheet.exception;

import org.springframework.http.HttpStatus;

public class SpreadsheetException extends RuntimeException{

    final protected HttpStatus httpStatus;
    final protected String errMsg;

    public SpreadsheetException(String errMsg, HttpStatus httpStatus){
        super(errMsg);
        this.errMsg = errMsg;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
