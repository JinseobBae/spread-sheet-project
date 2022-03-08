package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.*;
import com.js.spreadsheet.sheet.domain.SheetData;
import com.js.spreadsheet.sheet.domain.SheetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

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

    public List<TotalSearch> searchFromAll(String keyword){
        List<SheetData> allList = sheetDataRepository.findAll();

        List<TotalSearch> resultRows = new ArrayList<>();

        allList.forEach(sheetData -> {
            List<Row> rows = (List) toObject.apply(sheetData.getRowData());
            rows.forEach(row -> {
                Optional<Cell> cell = row.getCells().stream()
                        .filter(checkHasValue(keyword))
                        .findAny();

                cell.ifPresent(c -> {
                    String indexPrefix;
                    if(c.getIndex() <= 25){
                        indexPrefix = String.valueOf((char) (c.getIndex() + 65));
                    }else{
                        indexPrefix = String.valueOf((char) (c.getIndex() - 25 + 65));
                    }

                    resultRows.add(TotalSearch.builder()
                            .category(sheetData.getCategory())
                            .sheet(sheetData.getSheetName())
                            .index(indexPrefix + (row.getIndex() + 1))
                            .value(c.getValue())
                            .uuid(UUID.randomUUID().toString())
                            .build());
                });
            });
        });

        return resultRows;
    }

    private Predicate<Cell> checkHasValue(String keyword){
        return cell -> {
            if(cell.getValue() == null){
                return false;
            }else{
                return cell.getValue().contains(keyword);
            }
        };
    }
}
