package com.js.spreadsheet.sheet.application.service.legacy;

import com.js.spreadsheet.sheet.application.dto.RowDto;

import java.util.List;

public interface RowSearchService {

    List<RowDto> integrationRowSearch(String searchValue);
}
