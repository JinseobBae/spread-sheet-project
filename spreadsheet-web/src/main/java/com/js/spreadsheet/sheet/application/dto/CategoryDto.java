package com.js.spreadsheet.sheet.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {

    public CategoryDto(String categoryName){
        this.categoryName = categoryName;
    }

    String categoryName;

}
