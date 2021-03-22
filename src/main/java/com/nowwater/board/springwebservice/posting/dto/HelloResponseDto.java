package com.nowwater.board.springwebservice.posting.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get 메소드를 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 생성된 생성자를 생성해줌.
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
