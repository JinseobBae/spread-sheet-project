package com.js.spreadsheet.sheet.presentation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.js.spreadsheet.BaseTestController;
import com.js.spreadsheet.sheet.application.dto.CategoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class SheetInformationSearchControllerTest extends BaseTestController {

    @Test
    protected void findAllCategoriesTest() throws Exception{
        ResultActions result = requestGet("/sheet/categories");
        String body = result.andReturn().getResponse().getContentAsString();
        List<CategoryDto> resultList = objectMapper.readValue(body, new TypeReference<List<CategoryDto>>() {});

        Assertions.assertNotNull(resultList);
        Assertions.assertTrue(resultList.size() > 0);
    }
}
