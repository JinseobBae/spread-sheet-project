package com.js.spreadsheet.sheet.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class TotalSearch {

    String uuid = UUID.randomUUID().toString();

    String category;
    String sheet;
    String index;
    String value;
}
