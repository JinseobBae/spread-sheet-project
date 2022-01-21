package com.js.spreadsheet.sheet.legacy.application.service;

import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;
import com.js.spreadsheet.sheet.legacy.domain.SheetRow;
import com.js.spreadsheet.sheet.legacy.domain.SheetRowJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RowSearchServiceImpl implements RowSearchService{

    private final SheetRowJpaRepository sheetRowJpaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    RowSearchServiceImpl(SheetRowJpaRepository sheetRowJpaRepository){
        this.sheetRowJpaRepository = sheetRowJpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RowDto> integrationRowSearch(String searchValue) {
        List<SheetRow> allRows = sheetRowJpaRepository.findAll();

        return allRows.stream()
                .filter(row ->  checkHasValue(row, searchValue))
                .map(row ->
                    {
                        RowDto rowDto = modelMapper.map(row, RowDto.class);
                        rowDto.setCategory(row.getSheet().getCategory());
                        return rowDto;
                    }
                )
                .collect(Collectors.toList());
    }


    private boolean checkHasValue(SheetRow row, String searchValue){
        boolean result = false;
        Field[] fields = row.getClass().getDeclaredFields();

        for(Field field : fields){
           Object value = null;
           field.setAccessible(true);

           try{
               value = field.get(row);
           }catch(IllegalAccessException e){
               e.printStackTrace();
           }

           if(value != null && String.valueOf(value).matches(".*" + searchValue + ".*")){
               result = true;
               break;
           }
        }

        return result;
    }

}
