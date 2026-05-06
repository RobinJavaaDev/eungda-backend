package com.sideproject.eungda.domain.record.repository;

import com.sideproject.eungda.domain.record.entity.StoolRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StoolRecordRepository extends JpaRepository<StoolRecord, Long> {
    List<StoolRecord> findByMemberIdAndRecordedAtBetween(Long memberId, LocalDateTime start, LocalDateTime end);
}