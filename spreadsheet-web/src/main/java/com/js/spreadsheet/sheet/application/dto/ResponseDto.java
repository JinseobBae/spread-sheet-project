package com.js.spreadsheet.sheet.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto implements Serializable {

    String msg;
    String code;
}
