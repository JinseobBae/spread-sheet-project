package com.js.spreadsheet.handler;

import com.js.spreadsheet.BaseTestController;
import com.js.spreadsheet.exception.DuplicationException;
import com.js.spreadsheet.exception.NoRowFoundException;
import com.js.spreadsheet.exception.NoSheetFoundException;
import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionHandlerTest extends BaseTestController {


    private boolean assertException(MvcResult mvcResult, Class<?> exception){
        if(mvcResult.getResolvedException() != null){
            return mvcResult.getResolvedException().getClass().isAssignableFrom(exception);
        }else{
            return false;
        }

    }

    @Test
    protected void noSheetFoundExceptionTest() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sheetName", "no_sheet");
        MvcResult mvcResult = requestGet("/sheet/label", params)
                .andExpect(result -> assertException(result, NoSheetFoundException.class))
                .andExpect(result -> assertTrue(result.getResponse().getStatus() != HttpStatus.OK.value()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();

        HashMap result = objectMapper.readValue(body, HashMap.class);
        assertNotNull(result);
        assertNotNull(result.get("message"));
    }

    @Test
    protected void noRowFoundExceptionTest() throws Exception {
        RowDto rowDto = RowDto.builder()
                .sheetName("TEST_SHEET")
                .rowSeq(-1)
                .build();

        MvcResult mvcResult = requestPut("/sheet/row", rowDto)
                .andExpect(result -> assertException(result, NoRowFoundException.class))
                .andExpect(result -> assertTrue(result.getResponse().getStatus() != HttpStatus.OK.value()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();

        HashMap result = objectMapper.readValue(body, HashMap.class);
        assertNotNull(result);
        assertNotNull(result.get("message"));

    }

    @Test
    protected void duplicationExceptionTest() throws Exception {
        RowDto rowDto = RowDto.builder()
                .sheetName("TEST_SHEET")
                .build();

        MvcResult mvcResult = requestPost("/sheet/sheet", rowDto)
                .andExpect(result -> assertException(result, DuplicationException.class))
                .andExpect(result -> assertTrue(result.getResponse().getStatus() != HttpStatus.OK.value()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();

        HashMap result = objectMapper.readValue(body, HashMap.class);
        assertNotNull(result);
        assertNotNull(result.get("message"));
    }
}
