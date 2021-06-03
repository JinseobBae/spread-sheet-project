package com.js.spreadsheet.sheet.application.service;

import com.js.spreadsheet.exception.DuplicationException;
import com.js.spreadsheet.exception.NoSheetFoundException;
import com.js.spreadsheet.sheet.application.dto.ResponseDto;
import com.js.spreadsheet.sheet.application.dto.RowDto;
import com.js.spreadsheet.sheet.domain.Sheet;
import com.js.spreadsheet.sheet.domain.SheetJpaRepository;
import com.js.spreadsheet.sheet.domain.SheetRow;
import com.js.spreadsheet.sheet.domain.SheetRowJpaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class SheetUpdateServiceImpl implements SheetUpdateService{

    private final Logger logger = LoggerFactory.getLogger(SheetUpdateServiceImpl.class);
    private final SheetJpaRepository sheetRepository;
    private final SheetRowJpaRepository rowRepository;
    private final MessageSourceAccessor msa;
    private final ModelMapper modelMapper = new ModelMapper();


    SheetUpdateServiceImpl(SheetJpaRepository sheetRepository, SheetRowJpaRepository rowRepository, MessageSourceAccessor messageSourceAccessor){
        this.sheetRepository = sheetRepository;
        this.rowRepository = rowRepository;
        this.msa = messageSourceAccessor;
    }

    @Override
    @Transactional
    public ResponseDto addSheet(RowDto rowDto) {

        Optional<Sheet> existSheet = sheetRepository.findBySheetName(rowDto.getSheetName());

        if(existSheet.isPresent()){ // sheet 가 존재하면 오류
            throw new DuplicationException("error.duplication.sheet");
        }

        ResponseDto result = new ResponseDto();
        Sheet newSheet = Sheet.builder()
                .sheetName(rowDto.getSheetName())
                .build();

        try{
            sheetRepository.save(newSheet);
            result.setMsg(msa.getMessage("save.success"));
            result.setCode("1");
        }catch(Exception e){
            result.setMsg(msa.getMessage("save.failed"));
            result.setCode("0");
            logger.error("save sheet failed cause : " + e.getMessage() );
        }

        return result;
    }

    @Override
    @Transactional
    public ResponseDto addRow(RowDto rowDto) {

        Sheet sheet = sheetRepository.findBySheetName(rowDto.getSheetName()).orElseThrow(
                () -> new NoSheetFoundException("error.sheet.not.exist")
        );

        SheetRow row = SheetRow.builder()
                .rowSeq(rowDto.getRowSeq())
                .col1(rowDto.getCol1())
                .col2(rowDto.getCol2())
                .col3(rowDto.getCol3())
                .col4(rowDto.getCol4())
                .col5(rowDto.getCol5())
                .col6(rowDto.getCol6())
                .col7(rowDto.getCol7())
                .col8(rowDto.getCol8())
                .col9(rowDto.getCol9())
                .col10(rowDto.getCol10())
                .sheet(sheet)
                .build();

        ResponseDto result = new ResponseDto();

        try{
            rowRepository.save(row);
            result.setMsg(msa.getMessage("save.success"));
            result.setCode("1");
        }catch (Exception e){
            result.setMsg(msa.getMessage("save.failed"));
            result.setCode("0");
            logger.error("save row failed cause : " + e.getMessage() );
        }
        return result;
    }

    @Override
    public ResponseDto updateRow(RowDto rowDto) {
        return null;
    }

    @Override
    public ResponseDto removeSheet(RowDto rowDto) {
        return null;
    }

    @Override
    public ResponseDto removeRow(RowDto rowDto) {
        return null;
    }
}
