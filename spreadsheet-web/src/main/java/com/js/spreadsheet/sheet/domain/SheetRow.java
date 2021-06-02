package com.js.spreadsheet.sheet.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SheetRow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long rowId;

    long rowSeq;

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

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "sheet_id")
     Sheet sheet;


}

