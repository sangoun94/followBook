package com.hsw.book.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        String name = "test";
        int amount = 100;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }
}
