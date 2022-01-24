package com.js.spreadsheet.sheet.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Row implements Serializable {

    Long index;

    Long height;

    List<Cell> cells;



}
