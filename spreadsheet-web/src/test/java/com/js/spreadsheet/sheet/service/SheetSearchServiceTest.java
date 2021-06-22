package com.js.spreadsheet.sheet.service;

import com.js.spreadsheet.exception.NoSheetFoundException;
import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.application.service.SheetSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SheetSearchServiceTest {

    @Autowired
    SheetSearchService sheetSearchService;

    @Test
    void rowSearchTest(){
        RowDto rowDto = RowDto.builder()
                .sheetName("TEMP")
                .build();
        List<RowDto> result =  sheetSearchService.findRowData(rowDto);

        assert result != null;
    }

    @Test
    void rowSearchExceptionTest(){
        RowDto rowDto = RowDto.builder()
                .sheetName("NO_SHEET")
                .build();
        assertThrows(NoSheetFoundException.class, () -> sheetSearchService.findRowData(rowDto) );
    }
}