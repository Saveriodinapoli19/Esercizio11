package com.example.Esercizio11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity setStudentWorking(Long studentId, boolean isWorking){
        Optional<StudentEntity> student = studentRepository.findById(studentId);

        if(!student.isPresent()) return null;
        student.get().setWorking(isWorking);
        studentRepository.save(student.get());
        return null;
    }
}
