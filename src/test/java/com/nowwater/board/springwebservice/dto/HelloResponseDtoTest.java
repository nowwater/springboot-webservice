package com.nowwater.board.springwebservice.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.nowwater.board.springwebservice.posting.dto.HelloResponseDto;
import org.junit.Test;

//@RunWith(SpringRunner.class)
public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);


        // assertThat : assertj 라는 테스트 검증 라이브러리의 검증 메소드. 검증하고 싶은 대상을 메소드 인자로 받는다. 메소드 체이닝 지원
        // isEqualTo : assertj의 동등 비교 메소드. assertThat에 있는 값과 isEqualTo 값을 비교해서 같을 때만 성공
    }
}
