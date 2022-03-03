package com.js.spreadsheet.sheet.application.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RowResponseDto {

    List<Column> columns;
    List<Row> rows;
    Long frozenColumns;
    Long frozenRows;

    public void copy(RowResponseDto rowResponseDto){
        this.columns = rowResponseDto.getColumns();
        this.rows = rowResponseDto.getRows();
        this.frozenColumns = rowResponseDto.getFrozenColumns();
        this.frozenRows = rowResponseDto.frozenRows;
    }
}
