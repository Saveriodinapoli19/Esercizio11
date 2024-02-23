package com.example.Esercizio11;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    @Test
    void checkStudentWorking() throws Exception{
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setWorking(true);
        studentEntity.setName("Mario");
        studentEntity.setSurname("Rossi");
        assertThat(studentEntity.getName()).isNotNull();
        assertThat(studentEntity.getSurname()).isNotNull();
        assertThat(studentEntity.isWorking()).isTrue();
    }
}
