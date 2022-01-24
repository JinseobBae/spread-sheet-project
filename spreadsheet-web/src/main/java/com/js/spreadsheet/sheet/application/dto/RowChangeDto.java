package com.js.spreadsheet.sheet.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RowChangeDto {

    List<Column> columns;
    List<Row> rows;
    String sheetName;
    Long frozenColumns;
    Long frozenRows;
}
