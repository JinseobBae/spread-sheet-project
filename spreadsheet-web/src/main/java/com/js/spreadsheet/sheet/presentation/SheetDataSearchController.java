package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.RowResponseDto;
import com.js.spreadsheet.sheet.application.dto.RowChangeDto;
import com.js.spreadsheet.sheet.application.service.SheetDataChangeService;
import com.js.spreadsheet.sheet.application.service.SheetDataSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sheet/")
@RequiredArgsConstructor
public class SheetDataSearchController {

    private final SheetDataSearchService sheetDataSearchService;
    private final SheetDataChangeService sheetDataChangeService;

    @GetMapping("search/{sheetName}")
    RowResponseDto findRowStyle(@PathVariable String sheetName){
        return sheetDataSearchService.searchRow(sheetName);
    }

    @PostMapping("kendo-change")
    void updateData(@RequestBody RowChangeDto rowChangeDto){
        sheetDataChangeService.updateRowData(rowChangeDto);
    }


}
