package com.js.spreadsheet.sheet.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SheetDataRepository extends JpaRepository<SheetData, String> {

}
