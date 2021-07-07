package com.js.spreadsheet.sheet.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.js.spreadsheet.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SheetControllerTest extends BaseControllerTest {


    @Test
    protected void findDataTest() throws Exception{
        ResultActions result = requestGet("/sheet/sheet");
        String body = result.andReturn().getResponse().getContentAsString();
        HashMap map = objectMapper.readValue(body, new TypeReference<HashMap>() {});
        
        assert map.size() > 0;
    }

}