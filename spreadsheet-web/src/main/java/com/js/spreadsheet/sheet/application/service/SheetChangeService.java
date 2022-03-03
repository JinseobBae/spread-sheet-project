package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.exception.DuplicationException;
import com.js.spreadsheet.sheet.application.dto.SheetDto;
import com.js.spreadsheet.sheet.domain.Sheet;
import com.js.spreadsheet.sheet.domain.SheetJpaRepository;
import com.js.spreadsheet.sheet.application.dto.ResponseDto;
import com.js.spreadsheet.sheet.legacy.application.dto.RowDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SheetChangeService {

    private final SheetJpaRepository sheetRepository;
    private final MessageSourceAccessor msa;

    private final Logger logger = LoggerFactory.getLogger(SheetChangeService.class);

    @Transactional
    public ResponseDto addSheet(SheetDto sheetDto) {

        Optional<Sheet> existSheet = sheetRepository.findBySheetName(sheetDto.getSheet());

        if(existSheet.isPresent()){ // sheet 가 존재하면 오류
            throw new DuplicationException("error.duplication.sheet");
        }

        ResponseDto result = new ResponseDto();
        Sheet newSheet = Sheet.builder()
                .category(sheetDto.getCategory())
                .sheetName(sheetDto.getSheet())
                .build();

        try{
            sheetRepository.save(newSheet);
            result.setMsg(msa.getMessage("save.success"));
            result.setCode("1");
        }catch(Exception e){
            result.setMsg(msa.getMessage("save.failed"));
            result.setCode("0");
            logger.error("save sheet failed cause : {}", e.getMessage());
        }

        return result;
    }
}
