package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.Cell;
import com.js.spreadsheet.sheet.application.dto.Row;
import com.js.spreadsheet.sheet.application.dto.RowSearchDto;
import com.js.spreadsheet.sheet.domain.SheetData;
import com.js.spreadsheet.sheet.domain.SheetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.js.spreadsheet.sheet.application.utils.ByteObjectConverter.toObject;

@Service
@RequiredArgsConstructor
public class SheetDataSearchService {

    private final SheetDataRepository sheetDataRepository;

    public Object[][] searchRow(RowSearchDto rowsSearchDto){
        SheetData sheetData = sheetDataRepository.findById(rowsSearchDto.getSheetName())
                .orElse(null);

        Object[][] resultData = null;
        if(sheetData != null){
            List<Row> rows = (List) toObject.apply(sheetData.getRowData());

            OptionalInt maxRowIndex = rows.stream()
                    .mapToInt(row -> row.getIndex().intValue())
                    .max();

            OptionalInt maxColumnIndex = rows.stream()
                    .flatMap(row -> row.getCells().stream())
                    .mapToInt(cell -> cell.getIndex().intValue())
                    .max();

            resultData = new Object[maxRowIndex.getAsInt() + 1][maxColumnIndex.getAsInt() + 1];
            for(Row row : rows){
                int idx = row.getIndex().intValue();
                for(Cell cell : row.getCells()){
                    if(cell.getValue() != null){
                        resultData[idx][cell.getIndex().intValue()] = cell.getValue();
                    }
                }
            }
        }

        return resultData != null ? resultData : new Object[1][];

    }
}
