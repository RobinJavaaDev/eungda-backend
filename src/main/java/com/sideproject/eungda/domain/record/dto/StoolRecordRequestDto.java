package com.sideproject.eungda.domain.record.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoolRecordRequestDto {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotBlank(message = "색깔은 필수입니다.")
    private String color;

    @NotBlank(message = "형태는 필수입니다.")
    private String shape;

    private LocalDateTime recordedAt;
}