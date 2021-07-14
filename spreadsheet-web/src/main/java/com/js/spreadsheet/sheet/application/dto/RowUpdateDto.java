package com.js.spreadsheet.sheet.application.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RowUpdateDto extends RowDto{

    String updatedCol;
    String value;


}
