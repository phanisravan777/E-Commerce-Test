package com.bezkoder.spring.restapi;

import com.bezkoder.spring.restapi.SpringBoot3RestApiExampleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.SpringApplication;

@SpringBootTest
class SpringBoot3RestApiExampleApplicationTests {

    @Test
    void contextLoads() {
        SpringApplication.run(SpringBoot3RestApiExampleApplication.class, new String[]{});
    }

    @Test
    void testMain() {
        SpringBoot3RestApiExampleApplication.main(new String[] {});
    }

    @Test
    void testMainWithArgs() {
        SpringBoot3RestApiExampleApplication.main(new String[] {"arg1", "arg2"});
    }

    @Test
    void testMainWithNullArgs() {
        try {
            SpringBoot3RestApiExampleApplication.main(null);
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e.getClass());
        }
    }
}