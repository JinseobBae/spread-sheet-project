package com.js.spreadsheet.sheet.domain;

import com.js.spreadsheet.sheet.application.dto.RowDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SheetRow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long rowId;

    Long rowSeq;

     String col1;
     String col2;
     String col3;
     String col4;
     String col5;
     String col6;
     String col7;
     String col8;
     String col9;
     String col10;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "sheet_id")
     Sheet sheet;

     public void updateCol(RowDto rowDto){
         col1 = rowDto.getCol1();
         col2 = rowDto.getCol2();
         col3 = rowDto.getCol3();
         col4 = rowDto.getCol4();
         col5 = rowDto.getCol5();
         col6 = rowDto.getCol6();
         col7 = rowDto.getCol7();
         col8 = rowDto.getCol8();
         col9 = rowDto.getCol9();
         col10 = rowDto.getCol10();
     }

     public void increaseSeq(long num){
         this.rowSeq += num;
     }

     public void decreaseSeq(long num){
         this.rowSeq -= num;
     }


}

