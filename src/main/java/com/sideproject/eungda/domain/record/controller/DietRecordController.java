package com.sideproject.eungda.domain.record.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sideproject.eungda.domain.record.dto.DietRecordRequestDto;
import com.sideproject.eungda.domain.record.dto.DietRecordResponseDto;
import com.sideproject.eungda.domain.record.service.DietRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "식단 기록", description = "식단 기록 관련 API")
@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class DietRecordController {

    private final DietRecordService dietRecordService;

    @Operation(summary = "식단 기록 저장", description = "새로운 식단 기록을 저장합니다.")
    @PostMapping("/diet")
    public ResponseEntity<String> saveDietRecord(@RequestBody DietRecordRequestDto requestDto) {
        Long savedId = dietRecordService.saveDietRecord(requestDto);
        return ResponseEntity.ok("식단 기록이 성공적으로 저장되었습니다. (ID: " + savedId + ")");
    }

    @Operation(summary = "오늘 식단 기록 조회", description = "특정 회원의 오늘 식단 기록을 조회합니다.")
    @GetMapping("/diet/today")
    public ResponseEntity<List<DietRecordResponseDto>> getTodayDietRecords(
            @Parameter(description = "회원 ID", example = "1") @RequestParam Long memberId) {
        List<DietRecordResponseDto> records = dietRecordService.getTodayRecords(memberId);
        return ResponseEntity.ok(records);
    }

    @Operation(summary = "기간별 식단 기록 조회", description = "특정 회원의 지정된 기간 내 식단 기록을 조회합니다.")
    @GetMapping("/diet")
    public ResponseEntity<List<DietRecordResponseDto>> getDietRecordsByRange(
            @Parameter(description = "회원 ID", example = "1") @RequestParam Long memberId,
            @Parameter(description = "조회 시작일 (YYYY-MM-DD)", example = "2026-05-01") @org.springframework.web.bind.annotation.RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate startDate,
            @Parameter(description = "조회 종료일 (YYYY-MM-DD)", example = "2026-05-07") @org.springframework.web.bind.annotation.RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate endDate) {
        List<DietRecordResponseDto> records = dietRecordService.getRecordsByDateRange(memberId, startDate, endDate);
        return ResponseEntity.ok(records);
    }
}