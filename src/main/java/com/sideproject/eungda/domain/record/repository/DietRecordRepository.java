package com.sideproject.eungda.domain.record.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sideproject.eungda.domain.record.entity.DietRecord;

@Repository
public interface DietRecordRepository extends JpaRepository<DietRecord, Long> {
    List<DietRecord> findAllByMemberId(Long memberId);
    
    // 특정 회원의 특정 기간(시작일시 ~ 종료일시) 식단 기록을 조회하는 쿼리 메서드
    List<DietRecord> findByMemberIdAndRecordedDateBetween(Long memberId, LocalDateTime start, LocalDateTime end);
}