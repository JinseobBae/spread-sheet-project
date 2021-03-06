package com.js.spreadsheet.sheet.domain;

import com.js.spreadsheet.sheet.legacy.domain.SheetRow;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sheet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    @NotNull
    String sheetName;

    @NotNull
    String category;


    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<SheetRow> rows = new ArrayList<>();


    @Builder
    public Sheet(String sheetName, String category){
        this.sheetName = sheetName;
        this.category = category;
    }

}
