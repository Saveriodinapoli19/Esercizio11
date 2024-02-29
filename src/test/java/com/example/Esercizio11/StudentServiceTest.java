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
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Test
    void studentIsWorking() throws Exception{
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1L);
        studentEntity.setName("Michele");
        studentEntity.setSurname("Bianco");
        studentEntity.setWorking(true);

        StudentEntity studentFromDB = studentRepository.save(studentEntity);
        assertThat(studentFromDB).isNotNull();
        assertThat(studentFromDB.getId()).isNotNull();

        StudentEntity studentFromService = studentService.setStudentIsWorking(studentFromDB.getId(), false);
        assertThat(studentFromService).isNotNull();
        assertThat(studentFromService.getId()).isNotNull();

    }
}
