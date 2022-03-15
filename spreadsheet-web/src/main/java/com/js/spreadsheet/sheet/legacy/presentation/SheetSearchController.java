package com.js.spreadsheet.sheet.legacy.presentation;

import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;
import com.js.spreadsheet.sheet.legacy.application.service.RowSearchService;
import com.js.spreadsheet.sheet.legacy.application.service.SheetSearchService;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sheet/")
public class SheetSearchController {

    private final SheetSearchService sheetSearchService;
    private final RowSearchService rowSearchService;

    public SheetSearchController(SheetSearchService sheetSearchService, RowSearchService rowSearchService){
        this.sheetSearchService = sheetSearchService;
        this.rowSearchService = rowSearchService;
    }

    @GetMapping("data")
    List<RowDto> findSheetData(RowDto rowDto){
        return sheetSearchService.findRowData(rowDto);
    }

    @GetMapping("sheet")
    Map<String, List<RowDto>> findAllSheetList(){
        return sheetSearchService.findSheetData(new RowDto());
    }

    @GetMapping("search")
    HashMap<String, Object> searchRows(@RequestParam("searchValue") String searchValue){
        HashMap<String, Object> result = new HashMap<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<RowDto> searchResultList = rowSearchService.integrationRowSearch(searchValue);
        stopWatch.stop();
        result.put("count", searchResultList.size());
        result.put("list", searchResultList);
        result.put("time", stopWatch.getTotalTimeMillis());
        return result;
    }

}
