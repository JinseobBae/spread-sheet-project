package com.js.spreadsheet.sheet.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetJpaRepository extends JpaRepository<Sheet, Long> {
}
