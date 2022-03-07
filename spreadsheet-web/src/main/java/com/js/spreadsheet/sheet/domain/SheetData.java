package com.js.spreadsheet.sheet.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Blob;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SheetData {

    @Id
    String sheetName;

    @Lob
    byte[] rowData;

    @Lob
    byte[] columnData;

    Long frozenColumns;

    Long frozenRows;

    String category;
}
