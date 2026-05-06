package com.sideproject.eungda.domain.record.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diet_record")
public class DietRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_diet_record")
    @SequenceGenerator(name = "seq_diet_record", sequenceName = "SEQ_DIET_RECORD", allocationSize = 1)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "meal_type", nullable = false, length = 20)
    private String mealType; // 예: "BREAKFAST", "LUNCH", "DINNER"

    @Column(name = "has_rice", nullable = false)
    private Boolean hasRice; // 밥/탄수화물

    @Column(name = "has_fiber", nullable = false)
    private Boolean hasFiber; // 식이섬유

    @Column(name = "has_protein", nullable = false)
    private Boolean hasProtein; // 단백질

    @Column(name = "has_spicy", nullable = false)
    private Boolean hasSpicy; // 매운 음식

    @Column(name = "has_flour", nullable = false)
    private Boolean hasFlour; // 밀가루/인스턴트

    @Column(name = "has_water", nullable = false)
    private Boolean hasWater; // 수분 섭취량

    @Column(name = "recorded_date", nullable = false)
    private LocalDateTime recordedDate;

    @Builder
    public DietRecord(Long memberId, String mealType, Boolean hasRice, Boolean hasFiber, Boolean hasProtein, Boolean hasSpicy, Boolean hasFlour, Boolean hasWater, LocalDateTime recordedDate) {
        this.memberId = memberId;
        this.mealType = mealType;
        this.hasRice = hasRice;
        this.hasFiber = hasFiber;
        this.hasProtein = hasProtein;
        this.hasSpicy = hasSpicy;
        this.hasFlour = hasFlour;
        this.hasWater = hasWater;
        this.recordedDate = recordedDate;
    }
}