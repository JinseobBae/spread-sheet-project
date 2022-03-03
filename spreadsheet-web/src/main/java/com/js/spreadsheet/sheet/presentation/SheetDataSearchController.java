package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.RowResponseDto;
import com.js.spreadsheet.sheet.application.service.SheetDataSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sheet/")
@RequiredArgsConstructor
public class SheetDataSearchController {

    private final SheetDataSearchService sheetDataSearchService;

    @GetMapping("search/{sheetName}")
    RowResponseDto findRowStyle(@PathVariable String sheetName){
        return sheetDataSearchService.searchRow(sheetName);
    }


}
