package com.js.spreadsheet.exception;

import org.springframework.http.HttpStatus;

public class NoSheetFoundException extends SpreadsheetException{

    public NoSheetFoundException(String errMsg){
        super(errMsg, HttpStatus.BAD_REQUEST);
    }
}
