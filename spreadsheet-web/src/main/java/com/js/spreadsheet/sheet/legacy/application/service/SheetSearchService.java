package com.js.spreadsheet.sheet.legacy.application.service;

import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;

import java.util.List;
import java.util.Map;

public interface SheetSearchService {


    List<RowDto> findRowData(RowDto rowDto);

    List<Map<String, Object>> findRowDataKendo(RowDto rowDto);

    RowDto findRowLabel(RowDto rowDto);

    Map<String, List<RowDto>> findSheetData(RowDto rowDto);

}
