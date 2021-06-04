package com.js.spreadsheet.sheet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface SheetRowJpaRepository extends JpaRepository<SheetRow, Long> {

    Optional<SheetRow> findByRowSeqAndSheet(long rowSeq, Sheet sheet);

    @Lock(LockModeType.WRITE)
    void deleteBySheet(Sheet sheet);
}
