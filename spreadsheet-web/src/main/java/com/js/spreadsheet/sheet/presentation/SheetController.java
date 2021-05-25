package com.js.spreadsheet.sheet.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sheet/")
public class SheetController {



    @GetMapping("data")
    void findSheetData(){

    }

}
