package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.Row;
import com.js.spreadsheet.sheet.application.dto.RowResponseDto;
import com.js.spreadsheet.sheet.application.service.SheetDataSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sheet/")
@RequiredArgsConstructor
public class SheetDataSearchController {

    private final SheetDataSearchService sheetDataSearchService;

    @GetMapping("search/{sheetName}")
    RowResponseDto findRowStyle(@PathVariable String sheetName){
        return sheetDataSearchService.searchRow(sheetName);
    }

    @GetMapping("searchFromAll")
    List<Row> findRowFromAll(@RequestParam("keyword") String keyword){
        return sheetDataSearchService.searchFromAll(keyword);
    }

}
