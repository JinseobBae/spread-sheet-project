package com.js.spreadsheet.sheet.legacy.application.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RowUpdateDto extends RowDto{

    String updatedCol;
    String value;


}
