package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.ResponseDto;
import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.application.dto.RowUpdateDto;

public interface SheetUpdateService {

    ResponseDto addSheet(RowDto rowDto);

    ResponseDto addRow(RowDto rowDto);

    ResponseDto updateRow(RowUpdateDto rowUpdateDto);

    ResponseDto removeSheet(RowDto rowDto);

    ResponseDto removeRow(RowDto rowDto);

}
