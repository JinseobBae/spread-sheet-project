package com.js.spreadsheet.sheet.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Cell implements Serializable {

    Long index;
    String value;

    String background;
    String color;

    Long fontSize;
    String fontFamily;

    boolean bold;
    boolean italic;
    boolean underline;

}
