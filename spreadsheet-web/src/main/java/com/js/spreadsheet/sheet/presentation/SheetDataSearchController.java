package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.RowChangeDto;
import com.js.spreadsheet.sheet.application.dto.RowSearchDto;
import com.js.spreadsheet.sheet.application.service.SheetDataChangeService;
import com.js.spreadsheet.sheet.application.service.SheetDataSearchService;
import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;
import com.js.spreadsheet.sheet.legacy.application.service.RowSearchService;
import com.js.spreadsheet.sheet.legacy.application.service.SheetSearchService;
import com.js.spreadsheet.sheet.domain.SheetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sheet/")
@RequiredArgsConstructor
public class SheetDataSearchController {

    private final SheetSearchService sheetSearchService;
    private final RowSearchService rowSearchService;
    private final SheetDataSearchService sheetDataSearchService;
    private final SheetDataChangeService sheetDataChangeService;

//    @GetMapping("search-kendo")
//    List<Map<String, Object>> findDataForKendo(RowDto rowDto){
//        return sheetSearchService.findRowDataKendo(rowDto);
//    }


    @GetMapping("search-kendo")
    Object[][] findDataForKendo(RowSearchDto rowDto){
        return sheetDataSearchService.searchRow(rowDto);
    }

    @PostMapping("kendo-change")
    void updateData(@RequestBody RowChangeDto rowChangeDto){
        sheetDataChangeService.updateRowData(rowChangeDto);
    }


}
