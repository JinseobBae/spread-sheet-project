package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.RowDto;

import java.util.HashMap;
import java.util.List;

public interface SheetSearchService {


    List<RowDto> findRowData(RowDto rowDto);

    RowDto findRowLabel(RowDto rowDto);

    HashMap<String, List<RowDto>> findSheetData(RowDto rowDto);

}
