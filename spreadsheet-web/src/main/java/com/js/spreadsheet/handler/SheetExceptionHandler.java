package com.js.spreadsheet.handler;

import com.js.spreadsheet.exception.SpreadsheetException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class SheetExceptionHandler {

    private final MessageSourceAccessor messageSourceAccessor;

    SheetExceptionHandler(MessageSourceAccessor messageSourceAccessor){
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(value = {SpreadsheetException.class})
    public ResponseEntity<HashMap<String,String>> spreadSheetExceptionHandler(SpreadsheetException sse){
        return new ResponseEntity<>(generateResponse(sse), sse.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<HashMap<String,String>> commonExceptionHandler(Exception e){
        return new ResponseEntity<>(generateResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HashMap<String, String> generateResponse(Exception exception){
        HashMap<String,String> result = new HashMap<>();
        if(exception instanceof SpreadsheetException){
            String errMsg = ((SpreadsheetException) exception).getErrMsg();
            result.put("message", messageSourceAccessor.getMessage(errMsg, errMsg, Locale.getDefault()));
        }else{
            result.put("message", "Internal error. cause : " + exception.getMessage());
        }

        return result;
    }


}
