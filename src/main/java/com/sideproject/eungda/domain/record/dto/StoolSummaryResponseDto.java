package com.sideproject.eungda.domain.record.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoolSummaryResponseDto {
    private long totalCount;
    private long goldCount;
    private long bananaCount;
}