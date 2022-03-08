package com.js.spreadsheet.sheet.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TotalSearch {

    String uuid;
    String category;
    String sheet;
    String index;
    String value;
}
