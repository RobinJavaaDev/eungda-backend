package com.sideproject.eungda.domain.record.controller;

import com.sideproject.eungda.domain.record.dto.StoolRecordRequestDto;
import com.sideproject.eungda.domain.record.dto.StoolRecordResponseDto;
import com.sideproject.eungda.domain.record.service.StoolRecordService;
import com.sideproject.eungda.domain.stool.dto.StoolSummaryResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "배변 기록", description = "배변 기록 관련 API")
@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class StoolRecordController {

    private final StoolRecordService stoolRecordService;

    @Operation(summary = "배변 기록 저장", description = "새로운 배변 기록을 저장합니다.")
    @PostMapping("/stool")
    public ResponseEntity<String> saveStoolRecord(@Valid @RequestBody StoolRecordRequestDto requestDto) {
        Long savedId = stoolRecordService.saveStoolRecord(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("배변 기록이 성공적으로 저장되었습니다. (ID: " + savedId + ")");
    }

    @Operation(summary = "기간별 배변 기록 조회", description = "특정 회원의 지정된 기간 내 배변 기록을 조회합니다.")
    @GetMapping("/stool")
    public ResponseEntity<List<StoolRecordResponseDto>> getStoolRecordsByRange(
            @Parameter(description = "회원 ID", example = "1") @RequestParam Long memberId,
            @Parameter(description = "조회 시작일 (YYYY-MM-DD)", example = "2026-05-01") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "조회 종료일 (YYYY-MM-DD)", example = "2026-05-07") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<StoolRecordResponseDto> records = stoolRecordService.getStoolRecordsByDateRange(memberId, startDate, endDate);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/summary")
    @Operation(summary = "주간 배변 통계", description = "특정 회원의 최근 7일간 배변 통계를 요약합니다.")
    public ResponseEntity<StoolSummaryResponseDto> getWeeklySummary(
            @Parameter(description = "조회할 회원의 고유 ID", example = "1")
            @RequestParam Long memberId) {
        
        StoolSummaryResponseDto summary = stoolRecordService.getWeeklySummary(memberId);
        return ResponseEntity.ok(summary);
    }
}