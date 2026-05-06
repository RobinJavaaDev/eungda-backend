package com.sideproject.eungda.domain.stool.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoolSummaryResponseDto {
    private long totalCount;  // 총 배변 횟수
    private long goldCount;   // 황금색 횟수
    private long bananaCount; // 바나나 모양 횟수
}