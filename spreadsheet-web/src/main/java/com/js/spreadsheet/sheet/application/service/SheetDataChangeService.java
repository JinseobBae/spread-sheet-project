package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.sheet.application.dto.RowChangeDto;
import com.js.spreadsheet.sheet.domain.SheetData;
import com.js.spreadsheet.sheet.domain.SheetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.js.spreadsheet.sheet.application.utils.ByteObjectConverter.toByte;

@Service
@RequiredArgsConstructor
public class SheetDataChangeService {

    private final SheetDataRepository sheetDataRepository;
    private final CacheManager cacheManager;


    @CacheEvict(cacheNames = "sheet", key = "#rowChangeDto.getSheetName()")
    public void updateRowData(RowChangeDto rowChangeDto){
        SheetData sheetData = SheetData.builder()
                .sheetName(rowChangeDto.getSheetName())
                .frozenColumns(rowChangeDto.getFrozenColumns())
                .frozenRows(rowChangeDto.getFrozenRows())
                .rowData(toByte.apply(rowChangeDto.getRows()))
                .columnData(toByte.apply(rowChangeDto.getColumns()))
                .category(rowChangeDto.getCategory())
                .build();

        sheetDataRepository.save(sheetData);


        //전체검색에 대한 cache 삭제
        Optional.ofNullable(cacheManager.getCache("search")).ifPresent(Cache::invalidate);

    }

}
