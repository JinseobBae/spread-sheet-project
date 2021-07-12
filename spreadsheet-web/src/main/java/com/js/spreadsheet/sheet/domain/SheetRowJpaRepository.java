package com.js.spreadsheet.sheet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface SheetRowJpaRepository extends JpaRepository<SheetRow, Long> {

    Optional<SheetRow> findByRowSeqAndSheet(long rowSeq, Sheet sheet);

    List<SheetRow> findAllByRowSeqGreaterThan(Long rowSeq);

    @Override
    @Lock(LockModeType.WRITE)
    <S extends SheetRow> S save(S entity);

    @Lock(LockModeType.WRITE)
    void deleteBySheet(Sheet sheet);

    @Override
    @Lock(LockModeType.WRITE)
    void delete(SheetRow entity);
}
