package com.js.spreadsheet.sheet.presentation;

import com.js.spreadsheet.sheet.application.dto.CategoryDto;
import com.js.spreadsheet.sheet.application.service.SheetInformationSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sheet/")
@RequiredArgsConstructor
public class SheetInformationSearchController {

    private final SheetInformationSearchService sheetInformationSearchService;

    @GetMapping("categories")
    List<CategoryDto> findAllCategories(){
        return sheetInformationSearchService.findAllCategories();
    }
}
