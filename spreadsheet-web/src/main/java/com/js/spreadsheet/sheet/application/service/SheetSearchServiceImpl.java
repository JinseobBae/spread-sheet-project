package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.exception.NoSheetFoundException;
import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.domain.Sheet;
import com.js.spreadsheet.sheet.domain.SheetJpaRepository;
import com.js.spreadsheet.sheet.domain.SheetRow;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
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
}
