package com.sideproject.eungda.domain.record.entity;

import com.sideproject.eungda.enums.StoolColor;
import com.sideproject.eungda.enums.StoolShape;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class StoolRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StoolColor color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StoolShape shape;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    @Builder
    public StoolRecord(Long memberId, StoolColor color, StoolShape shape, LocalDateTime recordedAt) {
        this.memberId = memberId;
        this.color = color;
        this.shape = shape;
        this.recordedAt = recordedAt;
    }
}