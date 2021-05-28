package com.js.spreadsheet.sheet.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Row {

    @Id
    long rowId;

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



}

