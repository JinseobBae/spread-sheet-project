package com.js.spreadsheet.exception;

import org.springframework.http.HttpStatus;

public class NoRowFoundException extends SpreadsheetException{

    public NoRowFoundException(String errMsg) {
        super(errMsg, HttpStatus.BAD_REQUEST);
    }
}
