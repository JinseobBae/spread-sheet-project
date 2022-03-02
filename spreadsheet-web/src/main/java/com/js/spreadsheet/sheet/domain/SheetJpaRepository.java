package com.js.spreadsheet.sheet.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SheetJpaRepository extends JpaRepository<Sheet, Long> {

    Optional<Sheet> findBySheetName(String sheetName);

    List<Sheet> findAllByOrderByIdAsc();

}
