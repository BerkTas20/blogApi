package com.berktas.blogApi.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class) //If you want to do the unit test using rest assured
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class PlatformTestUnit {
}
