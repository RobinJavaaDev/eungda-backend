package com.sideproject.eungda.domain.record.service;

import com.sideproject.eungda.domain.member.repository.MemberRepository;
import com.sideproject.eungda.domain.record.dto.StoolRecordRequestDto;
import com.sideproject.eungda.domain.record.dto.StoolRecordResponseDto;
import com.sideproject.eungda.domain.record.entity.StoolRecord;
import com.sideproject.eungda.domain.record.repository.StoolRecordRepository;
import com.sideproject.eungda.domain.stool.dto.StoolSummaryResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoolRecordService {

    private final StoolRecordRepository stoolRecordRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveStoolRecord(StoolRecordRequestDto requestDto) {
        memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        StoolRecord stoolRecord = StoolRecord.builder()
                .memberId(requestDto.getMemberId())
                .color(requestDto.getColor())
                .shape(requestDto.getShape())
                .recordedAt(requestDto.getRecordedAt() != null ? requestDto.getRecordedAt() : LocalDateTime.now())
                .build();

        return stoolRecordRepository.save(stoolRecord).getId();
    }

    public List<StoolRecordResponseDto> getStoolRecordsByDateRange(Long memberId, LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        return stoolRecordRepository.findByMemberIdAndRecordedAtBetween(memberId, start, end)
                .stream()
                .map(StoolRecordResponseDto::from)
                .toList();
    }
    // 맨 위에 import java.time.LocalDateTime; 추가 확인!

    public StoolSummaryResponseDto getWeeklySummary(Long memberId) {
        // 1. 회원 존재 여부 검증 (아까 만든 방어 로직)
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 2. 최근 7일 기간 설정
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusDays(7);

        // 3. 최근 7일 기록 DB에서 싹 가져오기
        List<StoolRecord> records = stoolRecordRepository.findByMemberIdAndRecordedAtBetween(memberId, startDate, endDate);

        // 4. 통계 계산 (Java Stream 활용)
        long totalCount = records.size();
        long goldCount = records.stream().filter(r -> "황금".equals(r.getColor())).count();
        long bananaCount = records.stream().filter(r -> "바나나".equals(r.getShape())).count();

        // 5. 예쁘게 포장해서 반환
        return StoolSummaryResponseDto.builder()
                .totalCount(totalCount)
                .goldCount(goldCount)
                .bananaCount(bananaCount)
                .build();
    }
    
}