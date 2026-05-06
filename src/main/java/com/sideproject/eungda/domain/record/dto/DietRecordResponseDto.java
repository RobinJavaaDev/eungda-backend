package com.sideproject.eungda.domain.record.dto;

import com.sideproject.eungda.domain.record.entity.DietRecord;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class DietRecordResponseDto {

    private Long id;
    private String mealType;
    private Boolean hasRice;
    private Boolean hasFiber;
    private Boolean hasProtein;
    private Boolean hasSpicy;
    private Boolean hasFlour;
    private Boolean hasWater;
    private LocalDateTime recordedDate;

    // Entity를 DTO로 변환하는 안전한 팩토리 메서드
    public static DietRecordResponseDto from(DietRecord entity) {
        return DietRecordResponseDto.builder()
                .id(entity.getId())
                .mealType(entity.getMealType())
                .hasRice(entity.getHasRice())
                .hasFiber(entity.getHasFiber())
                .hasProtein(entity.getHasProtein())
                .hasSpicy(entity.getHasSpicy())
                .hasFlour(entity.getHasFlour())
                .hasWater(entity.getHasWater())
                .recordedDate(entity.getRecordedDate())
                .build();
    }
}