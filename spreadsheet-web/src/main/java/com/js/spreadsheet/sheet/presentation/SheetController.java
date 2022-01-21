package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.ResponseDto;
import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.application.dto.RowUpdateDto;
import com.js.spreadsheet.sheet.application.service.legacy.SheetUpdateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sheet/")
public class SheetController {

    private final SheetUpdateService sheetUpdateService;

    public SheetController(SheetUpdateService sheetUpdateService){
        this.sheetUpdateService = sheetUpdateService;
    }

    @PostMapping("sheet")
    ResponseDto addSheet(@RequestBody RowDto rowDto){
        return sheetUpdateService.addSheet(rowDto);
    }

    @DeleteMapping("sheet")
    ResponseDto deleteSheet(@RequestBody RowDto rowDto){
        return sheetUpdateService.removeSheet(rowDto);
    }

    @PostMapping("row")
    ResponseDto addRow(@RequestBody RowDto rowDto){
        return sheetUpdateService.addRow(rowDto);
    }

    @PutMapping("row")
    ResponseDto updateRow(@RequestBody RowUpdateDto updateDto){
        return sheetUpdateService.updateRow(updateDto);
    }

    @DeleteMapping("row")
    ResponseDto deleteRow(@RequestBody RowDto rowDto){
        return sheetUpdateService.removeRow(rowDto);
    }

}
