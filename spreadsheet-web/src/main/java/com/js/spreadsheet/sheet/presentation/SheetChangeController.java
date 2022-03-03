package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.ResponseDto;
import com.js.spreadsheet.sheet.application.dto.RowChangeDto;
import com.js.spreadsheet.sheet.application.dto.SheetDto;
import com.js.spreadsheet.sheet.application.service.SheetChangeService;
import com.js.spreadsheet.sheet.application.service.SheetDataChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sheet/")
@RequiredArgsConstructor
public class SheetChangeController {

    private final SheetDataChangeService sheetDataChangeService;
    private final SheetChangeService sheetChangeService;

    @PostMapping("kendo-change")
    void updateData(@RequestBody RowChangeDto rowChangeDto){
        sheetDataChangeService.updateRowData(rowChangeDto);
    }

    @PostMapping("sheet")
    ResponseDto addSheet(@RequestBody SheetDto sheetDto){
        return sheetChangeService.addSheet(sheetDto);
    }
}
