package com.sideproject.eungda.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoolColor {
    BROWN("갈색"),
    BLACK("검은색"),
    RED("붉은색"),
    GREEN("녹색"),
    YELLOW("노란색");

    private final String description;
}
