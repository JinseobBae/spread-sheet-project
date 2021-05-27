package com.js.spreadsheet.sheet.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SheetJpaRepository extends JpaRepository<Sheet, Long> {

    Optional<Sheet> findBySheetName(String sheetName);
}
