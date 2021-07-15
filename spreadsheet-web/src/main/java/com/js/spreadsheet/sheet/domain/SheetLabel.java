package com.js.spreadsheet.sheet.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SheetLabel {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    long id;

    String col1;
    String col2;
    String col3;
    String col4;
    String col5;
    String col6;
    String col7;
    String col8;
    String col9;
    String col10;

    @OneToOne
    @JoinColumn(name = "sheet_id")
    Sheet sheet;

}
