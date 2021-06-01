package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.application.service.SheetSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sheet/")
public class SheetController {


    private final SheetSearchService sheetSearchService;

    public SheetController(SheetSearchService sheetSearchService){
        this.sheetSearchService = sheetSearchService;
    }


    @GetMapping("data")
    List<RowDto> findSheetData(RowDto rowDto){
        return sheetSearchService.findRowData(rowDto);
    }

}
