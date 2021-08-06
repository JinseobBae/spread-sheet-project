package com.js.spreadsheet.sheet.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.js.spreadsheet.BaseTestController;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SheetControllerTest extends BaseTestController {


    @Test
    protected void findDataTest() throws Exception{
        ResultActions result = requestGet("/sheet/sheet");
        String body = result.andReturn().getResponse().getContentAsString();
        HashMap map = objectMapper.readValue(body, new TypeReference<HashMap>() {});
        
        assert map.size() > 0;
    }


    @Test
    protected void findDataTestKendo() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sheetName" , "코드정의");
        ResultActions result = requestGet("/sheet/search-kendo", params);

        String body = result.andReturn().getResponse().getContentAsString();
        HashMap map = objectMapper.readValue(body, new TypeReference<HashMap>() {});

        assert map.size() > 0;

        System.out.println(map);

    }
}