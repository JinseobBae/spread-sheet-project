package com.js.spreadsheet.sheet.legacy.application.service;

import com.js.spreadsheet.exception.NoSheetFoundException;
import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;
import com.js.spreadsheet.sheet.legacy.domain.Sheet;
import com.js.spreadsheet.sheet.legacy.domain.SheetJpaRepository;
import com.js.spreadsheet.sheet.legacy.domain.SheetRow;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SheetSearchServiceImpl implements SheetSearchService{

    private final SheetJpaRepository sheetJpaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public SheetSearchServiceImpl(SheetJpaRepository sheetJpaRepository){
        this.sheetJpaRepository = sheetJpaRepository;
    }

    @Override
    public List<RowDto> findRowData(RowDto rowDto) throws RuntimeException{
        Sheet sheet = sheetJpaRepository.findBySheetName(rowDto.getSheetName())
                .orElseThrow( () -> new NoSheetFoundException("error.sheet.not.exist") );

        return sheet.getRows().stream()
                .sorted(Comparator.comparing(SheetRow::getRowSeq))
                .map( row -> modelMapper.map(row, RowDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RowDto findRowLabel(RowDto rowDto) {
        Sheet sheet = sheetJpaRepository.findBySheetName(rowDto.getSheetName())
                .orElseThrow( () -> new NoSheetFoundException("error.sheet.not.exist") );

        RowDto lable = sheet.getLabel() != null ? modelMapper.map(sheet.getLabel(), RowDto.class) : new RowDto();

        return lable;
    }

    @Override
    public Map<String, List<RowDto>> findSheetData(RowDto rowDto) {
        List<Sheet> sheetList = new ArrayList<>();
        if(rowDto.getSheetName() == null || rowDto.getSheetName().equals("")){
            sheetList = sheetJpaRepository.findAllByOrderByIdAsc();
        }else{
            Optional<Sheet> sheet =  sheetJpaRepository.findBySheetName(rowDto.getSheetName());
            if(sheet.isPresent()){
                sheetList.add(sheet.get());
            }
        }

        return sheetList.stream()
                .map( sheet -> RowDto.builder()
                        .sheetName(sheet.getSheetName())
                        .category(sheet.getCategory())
                        .build())
                .collect(Collectors.groupingBy(RowDto::getCategory, LinkedHashMap::new, Collectors.toList()));
    }

    @Override
    public List<Map<String, Object>> findRowDataKendo(RowDto rowDto) {
        Sheet sheet = sheetJpaRepository.findBySheetName(rowDto.getSheetName())
                .orElseThrow( () -> new NoSheetFoundException("error.sheet.not.exist") );

        RowDto lable = sheet.getLabel() != null ? modelMapper.map(sheet.getLabel(), RowDto.class) : new RowDto();
        List<SheetRow> rows = sheet.getRows();

        List<Map<String, Object>> resultList = new ArrayList<>();

        rows.forEach(row -> resultList.add(generateMap(lable,row)));

        return resultList;
    }

    LinkedHashMap<String, Object> generateMap(RowDto label, SheetRow row){
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        if(checkStringValue(label.getCol1()))
            result.put(label.getCol1(), defaultIfNull(row.getCol1()));
        if(checkStringValue(label.getCol2()))
            result.put(label.getCol2(), defaultIfNull(row.getCol2()));
        if(checkStringValue(label.getCol3()))
            result.put(label.getCol3(), defaultIfNull(row.getCol3()));
        if(checkStringValue(label.getCol4()))
            result.put(label.getCol4(), defaultIfNull(row.getCol4()));
        if(checkStringValue(label.getCol5()))
            result.put(label.getCol5(), defaultIfNull(row.getCol5()));
        if(checkStringValue(label.getCol6()))
            result.put(label.getCol6(), defaultIfNull(row.getCol6()));
        if(checkStringValue(label.getCol7()))
            result.put(label.getCol7(), defaultIfNull(row.getCol7()));
        if(checkStringValue(label.getCol8()))
            result.put(label.getCol8(), defaultIfNull(row.getCol8()));
        if(checkStringValue(label.getCol9()))
            result.put(label.getCol9(), defaultIfNull(row.getCol9()));
        if(checkStringValue(label.getCol10()))
            result.put(label.getCol10(), defaultIfNull(row.getCol10()));

        return result;
    }

    boolean checkStringValue(String str){
        return str != null && !str.equals("");
    }

    String defaultIfNull(String str){
        return str == null ? "" : str;
    }
}
