package com.sideproject.eungda.domain.record.dto;

import com.sideproject.eungda.domain.record.entity.StoolRecord;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StoolRecordResponseDto {
    private Long id;
    private Long memberId;
    private String color;
    private String shape;
    private LocalDateTime recordedAt;

    public static StoolRecordResponseDto from(StoolRecord entity) {
        return StoolRecordResponseDto.builder()
                .id(entity.getId())
                .memberId(entity.getMemberId())
                .color(entity.getColor())
                .shape(entity.getShape())
                .recordedAt(entity.getRecordedAt())
                .build();
    }
}