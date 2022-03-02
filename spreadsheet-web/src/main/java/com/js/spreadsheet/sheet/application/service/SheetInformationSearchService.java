package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.CategoryDto;
import com.js.spreadsheet.sheet.domain.Sheet;
import com.js.spreadsheet.sheet.domain.SheetJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SheetInformationSearchService {

    private final SheetJpaRepository sheetJpaRepository;

    public List<CategoryDto> findAllCategories(){

        List<Sheet> sheetList = sheetJpaRepository.findAll();

        List<String> categoryList = sheetList.stream()
                .map(Sheet::getCategory)
                .distinct()
                .collect(Collectors.toList());

        return categoryList.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
}
