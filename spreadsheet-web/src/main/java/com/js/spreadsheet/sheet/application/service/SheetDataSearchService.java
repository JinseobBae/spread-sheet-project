package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.*;
import com.js.spreadsheet.sheet.domain.SheetData;
import com.js.spreadsheet.sheet.domain.SheetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

import static com.js.spreadsheet.sheet.application.utils.ByteObjectConverter.toObject;

@Service
@RequiredArgsConstructor
public class SheetDataSearchService {

    private final SheetDataRepository sheetDataRepository;

    @Cacheable(cacheNames = "sheet", key = "#sheetName")
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

    @Cacheable(cacheNames = "search", key = "#keyword")
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

                    TotalSearch resultData = TotalSearch.builder()
                            .category(sheetData.getCategory())
                            .sheet(sheetData.getSheetName())
                            .index(indexPrefix + (row.getIndex() + 1))
                            .rowIndex(row.getIndex() + 1)
                            .value(c.getValue())
                            .uuid(UUID.randomUUID().toString())
                            .build();

                    if(!haveSimilarData(resultRows, resultData)){
                        resultRows.add(resultData);
                    }

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

    /**
     * 결과값의 인덱스를 확인한다.
     * 동일한 값이 위아래로 100칸 내에 있으면 유사데이터로 판단해서 결과 목록에 추가하지 않는다.
     * @param resultList
     * @param resultData
     * @return
     */
    private boolean haveSimilarData(List<TotalSearch> resultList, TotalSearch resultData){

        Optional<TotalSearch> similarData = resultList.parallelStream()
                .filter(data -> data.getValue().equals(resultData.getValue()))
                .filter(data -> data.getRowIndex() - 100 <= resultData.getRowIndex() && resultData.getRowIndex() <= data.getRowIndex() + 100)
                .findAny();

        return similarData.isPresent();
    }
}
