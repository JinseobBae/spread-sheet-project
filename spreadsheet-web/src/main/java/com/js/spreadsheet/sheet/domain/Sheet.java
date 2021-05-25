package com.js.spreadsheet.sheet.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sheet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    String sheetName;


    @OneToMany
    @JoinColumn(name = "rowId")
    List<Row> rows;


    @Builder
    public Sheet(String sheetName){
        this.sheetName = sheetName;
    }

}
