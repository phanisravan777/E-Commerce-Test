package com.bezkoder.spring.restapi;

import com.bezkoder.spring.restapi.SpringBoot3RestApiExampleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.SpringApplication;

@SpringBootTest
public class SpringBoot3RestApiExampleApplicationTests {

    @Test
    public void contextLoads() {
        SpringApplication.run(SpringBoot3RestApiExampleApplication.class, new String[]{});
    }

    @Test
    public void testMain() {
        SpringBoot3RestApiExampleApplication.main(new String[] {});
    }

    @Test
    public void testMainWithArgs() {
        SpringBoot3RestApiExampleApplication.main(new String[] {"arg1", "arg2"});
    }

    @Test
    public void testMainWithNullArgs() {
        try {
            SpringBoot3RestApiExampleApplication.main(null);
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testMainWithEmptyArgs() {
        SpringBoot3RestApiExampleApplication.main(new String[] {"", ""});
    }

    @Test
    public void testMainWithLargeArgs() {
        String[] largeArgs = new String[1000];
        for (int i = 0; i < 1000; i++) {
            largeArgs[i] = "arg" + i;
        }
        SpringBoot3RestApiExampleApplication.main(largeArgs);
    }
}