package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.application.service.SheetSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sheet/")
public class SheetSearchController {

    private final SheetSearchService sheetSearchService;

    public SheetSearchController(SheetSearchService sheetSearchService){
        this.sheetSearchService = sheetSearchService;
    }

    @GetMapping("data")
    List<RowDto> findSheetData(RowDto rowDto){
        return sheetSearchService.findRowData(rowDto);
    }

    @GetMapping("label")
    RowDto findSheetLabel(RowDto rowDto){
        return sheetSearchService.findRowLabel(rowDto);
    }

    @GetMapping("sheet")
    Map<String, List<RowDto>> findAllSheetList(){
        return sheetSearchService.findSheetData(new RowDto());
    }

}
