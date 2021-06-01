package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.exception.NoSheetFoundException;
import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.domain.Sheet;
import com.js.spreadsheet.sheet.domain.SheetJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .orElseThrow( () -> new NoSheetFoundException("There is no sheet") );

        return sheet.getRows().stream()
                .map( row -> modelMapper.map(row, RowDto.class))
                .collect(Collectors.toList());
    }
}
