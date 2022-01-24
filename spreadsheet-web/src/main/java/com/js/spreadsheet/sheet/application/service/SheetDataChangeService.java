package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.RowChangeDto;
import com.js.spreadsheet.sheet.domain.SheetData;
import com.js.spreadsheet.sheet.domain.SheetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.js.spreadsheet.sheet.application.utils.ByteObjectConverter.toByte;

@Service
@RequiredArgsConstructor
public class SheetDataChangeService {

    private final SheetDataRepository sheetDataRepository;

    public void updateRowData(RowChangeDto rowChangeDto){
        SheetData sheetData = SheetData.builder()
                .sheetName(rowChangeDto.getSheetName())
                .frozenColumns(rowChangeDto.getFrozenColumns())
                .frozenRows(rowChangeDto.getFrozenRows())
                .rowData(toByte.apply(rowChangeDto.getRows()))
                .columnData(toByte.apply(rowChangeDto.getColumns()))
                .build();

        sheetDataRepository.save(sheetData);
    }

}
