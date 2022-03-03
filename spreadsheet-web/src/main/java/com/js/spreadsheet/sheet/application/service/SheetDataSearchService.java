package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.Column;
import com.js.spreadsheet.sheet.application.dto.Row;
import com.js.spreadsheet.sheet.application.dto.RowResponseDto;
import com.js.spreadsheet.sheet.domain.SheetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.js.spreadsheet.sheet.application.utils.ByteObjectConverter.toObject;

@Service
@RequiredArgsConstructor
public class SheetDataSearchService {

    private final SheetDataRepository sheetDataRepository;

    public RowResponseDto searchRow(String sheetName){

        RowResponseDto response = new RowResponseDto();

        sheetDataRepository.findById(sheetName).ifPresent(sheetData -> {
            List<Row> rows = (List) toObject.apply(sheetData.getRowData());
            rows.forEach(row -> {
                if(row.getHeight() == null){
                    row.setHeight(20L);
                }
            });


            List<Column> columns = (List) toObject.apply(sheetData.getColumnData());

            columns.forEach(column -> {
                if(column.getWidth() == null){
                    column.setWidth(70L);
                }
            });

            response.copy(RowResponseDto.builder()
                    .columns(columns)
                    .rows(rows)
                    .frozenColumns(sheetData.getFrozenColumns())
                    .frozenRows(sheetData.getFrozenRows())
                    .build());
        });

        return response;
    }
}
