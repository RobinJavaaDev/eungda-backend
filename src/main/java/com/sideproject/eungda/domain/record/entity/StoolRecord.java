package com.sideproject.eungda.domain.record.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class StoolRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String shape;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    @Builder
    public StoolRecord(Long memberId, String color, String shape, LocalDateTime recordedAt) {
        this.memberId = memberId;
        this.color = color;
        this.shape = shape;
        this.recordedAt = recordedAt;
    }
}