package com.sideproject.eungda.domain.record.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DietRecordRequestDto {

    private Long memberId;
    
    private String mealType; // "BREAKFAST", "LUNCH", "DINNER"
    
    private Boolean hasRice;
    
    private Boolean hasFiber;
    
    private Boolean hasProtein;
    
    private Boolean hasSpicy;
    
    private Boolean hasFlour;
    
    private Boolean hasWater;
    
    private LocalDateTime recordedDate;
}