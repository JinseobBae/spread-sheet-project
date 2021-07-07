package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.RowDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SheetSearchService {


    List<RowDto> findRowData(RowDto rowDto);

    RowDto findRowLabel(RowDto rowDto);

    Map<String, List<RowDto>> findSheetData(RowDto rowDto);

}
