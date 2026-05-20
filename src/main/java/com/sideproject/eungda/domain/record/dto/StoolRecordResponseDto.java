package com.sideproject.eungda.domain.record.dto;

import java.time.LocalDateTime;

import com.sideproject.eungda.domain.record.entity.StoolRecord;
import com.sideproject.eungda.enums.StoolColor;
import com.sideproject.eungda.enums.StoolShape;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoolRecordResponseDto {
    private Long id;
    private Long memberId;
    private StoolColor color;
    private StoolShape shape;
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