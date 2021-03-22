package com.nowwater.board.springwebservice;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.nowwater.board.springwebservice.posting.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class) // 테스트를 진행 시 JUnit 에 내장된 실행자 외에 다른 실행자 실행 - SpringRunner 라는 스프링 실행자 사용
@WebMvcTest(controllers = HelloController.class) // 웹에 집중할 수 있는 테스트 어노테이션
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API 테스트 시 사용, 스프링 MVC 테스트의 시작점.
    // HTTP GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 GET 요청
                .andExpect(status().isOk()) // HTTP Header 의 Status 를 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용 검증, Controller 에서 "hello"를 리턴하기 때문에 값이 맞는지 검증

        // andExpect : mvc.perform의 결과를 검증,

    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        // param : API 테스트 시 사용될 요청 파라미터 설정
        // 값은 String 만 허용, 따라서 숫자/날짜 데이터 등록 시 문자열로 변경해야한다.

        // jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드
        // $ 를 기준으로 필드명을 명시
        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.amount", is(amount)));
        System.out.println(name + " " + amount);
    }
}
