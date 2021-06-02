package com.js.spreadsheet.exception;

import org.springframework.http.HttpStatus;

public class DuplicationException extends SpreadsheetException{

    public DuplicationException(String errMsg) {
        super(errMsg, HttpStatus.BAD_REQUEST);
    }
}
