package com.example.Esercizio11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentEntity setStudentIsWorking(Long studentId, Boolean working) {
        Optional<StudentEntity> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            student.get().setWorking(working);
            return studentRepository.saveAndFlush(student.get());
        } else return null;
    }
}
