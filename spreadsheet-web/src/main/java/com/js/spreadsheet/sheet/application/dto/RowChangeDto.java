package com.js.spreadsheet.sheet.application.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RowChangeDto {

    List<Column> columns;
    List<Row> rows;
    String sheetName;
    String category;
    Long frozenColumns;
    Long frozenRows;
}
