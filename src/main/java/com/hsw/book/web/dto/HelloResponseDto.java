package com.hsw.book.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
