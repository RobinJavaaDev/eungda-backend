package com.sideproject.eungda.domain.record.dto;

import com.sideproject.eungda.enums.StoolColor;
import com.sideproject.eungda.enums.StoolShape;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StoolRecordRequestDto {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "색깔은 필수입니다.")
    private StoolColor color;

    @NotNull(message = "형태는 필수입니다.")
    private StoolShape shape;

    private LocalDateTime recordedAt;
}