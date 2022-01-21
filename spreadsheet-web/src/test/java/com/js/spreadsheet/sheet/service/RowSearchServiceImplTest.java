package com.js.spreadsheet.sheet.service;

import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;
import com.js.spreadsheet.sheet.legacy.application.service.RowSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RowSearchServiceImplTest {

    @Autowired
    RowSearchService rowSearchService;


    @Test
    void searchTest(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<RowDto> result = rowSearchService.integrationRowSearch("TEST");

        stopWatch.stop();

        stopWatch.prettyPrint();

        assertNotNull(result);

    }



}