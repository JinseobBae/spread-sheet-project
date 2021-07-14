package com.js.spreadsheet.sheet.service;

import com.js.spreadsheet.exception.DuplicationException;
import com.js.spreadsheet.exception.NoSheetFoundException;
import com.js.spreadsheet.sheet.application.dto.ResponseDto;
import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.application.dto.RowUpdateDto;
import com.js.spreadsheet.sheet.application.service.SheetUpdateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SheetUpdateServiceImplTest {

    @Autowired
    SheetUpdateService sheetUpdateService;


    @Test
    @Transactional
    void addSheetTest(){
        RowDto sheet = new RowDto();
        sheet.setSheetName("T1E2ST");
        ResponseDto result = sheetUpdateService.addSheet(sheet);

        assert result.getCode().equals("1");

    }

    @Test
    void addSheetDupTest(){
        RowDto sheet = new RowDto();
        sheet.setSheetName("TEST_SHEET");

        assertThrows(DuplicationException.class, () -> sheetUpdateService.addSheet(sheet));

    }

    @Test
    @Transactional
    void addRowTest(){
        RowDto sheet = new RowDto();
        sheet.setSheetName("TEMP");
        sheetUpdateService.addSheet(sheet);

        RowDto rowDto = RowDto.builder()
                .sheetName("TEMP")
                .col1("col1")
                .col2("col2")
                .col3("col3")
                .col4("col4")
                .col5("col5")
                .col6("col6")
                .col7("col7")
                .col8("col8")
                .col9("col9")
                .col10("col10")
                .rowSeq(1)
                .build();

        ResponseDto result = sheetUpdateService.addRow(rowDto);

        assert result.getCode().equals("1");

    }

    @Test
    void addRowNoSheetTest(){
        RowDto rowDto = RowDto.builder()
                .sheetName("NOSHEET")
                .col1("col1")
                .col2("col2")
                .col3("col3")
                .col4("col4")
                .col5("col5")
                .col6("col6")
                .col7("col7")
                .col8("col8")
                .col9("col9")
                .col10("col10")
                .rowSeq(1)
                .build();

         assertThrows(NoSheetFoundException.class, () -> sheetUpdateService.addRow(rowDto));
    }

    @Test
    @Transactional
    void updateRowTest(){
        RowDto sheet = new RowDto();
        sheet.setSheetName("UPDATE_TEST");
        sheetUpdateService.addSheet(sheet);

        RowDto rowDto = RowDto.builder()
                .sheetName(sheet.getSheetName())
                .col1("col1")
                .col2("col2")
                .col3("col3")
                .col4("col4")
                .col5("col5")
                .col6("col6")
                .col7("col7")
                .col8("col8")
                .col9("col9")
                .col10("col10")
                .rowSeq(1)
                .build();
         sheetUpdateService.addRow(rowDto);

        RowUpdateDto updateRow = new RowUpdateDto();
        updateRow.setSheetName(sheet.getSheetName());
        updateRow.setUpdatedCol("col1");
        updateRow.setCol1("changed!!");
        updateRow.setRowSeq(1);

        ResponseDto updateResult = sheetUpdateService.updateRow(updateRow);

        assert updateResult.getCode().equals("1");

    }

}