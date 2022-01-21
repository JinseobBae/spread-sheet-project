package com.js.spreadsheet.sheet.legacy.application.service;

import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;

import java.util.List;

public interface RowSearchService {

    List<RowDto> integrationRowSearch(String searchValue);
}
