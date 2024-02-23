package com.example.Esercizio11;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @Test
    void testController() {
        assertThat(studentController).isNotNull();
    }
}
