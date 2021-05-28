package com.js.spreadsheet.sheet.application.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RowDto {

    String sheetName;

    long rowSeq;

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

}
