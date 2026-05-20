package com.sideproject.eungda.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoolShape {
    HARD_LUMP("토끼똥처럼 딱딱한 덩어리"),
    LUMPY_SAUSAGE("울퉁불퉁한 소시지 모양"),
    SMOOTH_SAUSAGE("매끈하고 부드러운 소시지 모양"),
    SOFT_BLOBS("형태가 뚜렷하지 않은 부드러운 덩어리"),
    WATERY("형태가 없는 묽은 변");

    private final String description;
}
