package com.sideproject.eungda.domain.record.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sideproject.eungda.domain.member.repository.MemberRepository;
import com.sideproject.eungda.domain.record.dto.DietRecordRequestDto;
import com.sideproject.eungda.domain.record.dto.DietRecordResponseDto;
import com.sideproject.eungda.domain.record.entity.DietRecord;
import com.sideproject.eungda.domain.record.repository.DietRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class DietRecordService {

    private final DietRecordRepository dietRecordRepository;
    private final MemberRepository memberRepository;

    /**
     * 1. 식단 기록 저장 (Create)
     */
    @Transactional // 데이터 변경이 일어나므로 ReadOnly를 해제
    public Long saveDietRecord(DietRecordRequestDto requestDto) {

        // 회원 검증
        memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // DTO를 엔티티로 변환 (Builder 패턴 사용)
        DietRecord dietRecord = DietRecord.builder()
                .memberId(requestDto.getMemberId())
                .mealType(requestDto.getMealType())
                .hasRice(requestDto.getHasRice() != null ? requestDto.getHasRice() : false)
                .hasFiber(requestDto.getHasFiber() != null ? requestDto.getHasFiber() : false)
                .hasProtein(requestDto.getHasProtein() != null ? requestDto.getHasProtein() : false)
                .hasSpicy(requestDto.getHasSpicy() != null ? requestDto.getHasSpicy() : false)
                .hasFlour(requestDto.getHasFlour() != null ? requestDto.getHasFlour() : false)
                .hasWater(requestDto.getHasWater() != null ? requestDto.getHasWater() : false)
                .recordedDate(requestDto.getRecordedDate() != null ? requestDto.getRecordedDate() : LocalDateTime.now())
                .build();

        DietRecord savedRecord = dietRecordRepository.save(dietRecord);
        
        return savedRecord.getId();
    }

    /**
     * 2. 오늘의 식단 조회 (Read - Today)
     */
    public List<DietRecordResponseDto> getTodayRecords(Long memberId) {
        // 오늘 자정부터 밤 11시 59분까지의 범위를 계산
        LocalDateTime startOfDay = java.time.LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = java.time.LocalDate.now().atTime(23, 59, 59);

        return dietRecordRepository.findByMemberIdAndRecordedDateBetween(memberId, startOfDay, endOfDay)
                .stream()
                .map(DietRecordResponseDto::from)
                .toList();
    }

    /**
     * 3. 특정 기간 식단 조회 (Read - Range)
     */
    public List<DietRecordResponseDto> getRecordsByDateRange(Long memberId, java.time.LocalDate startDate, java.time.LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);

        return dietRecordRepository.findByMemberIdAndRecordedDateBetween(memberId, start, end)
                .stream()
                .map(DietRecordResponseDto::from)
                .toList();
    }

    /**
     * 4. 특정 회원의 모든 식단 기록 조회 (Read - All by Member)
     */
    public List<DietRecordResponseDto> getDietRecordsByMember(Long memberId) {
        return dietRecordRepository.findAllByMemberId(memberId)
                .stream()
                .map(DietRecordResponseDto::from)
                .toList();
    }
}