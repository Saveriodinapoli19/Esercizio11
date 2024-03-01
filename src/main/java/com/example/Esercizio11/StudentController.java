package com.example.Esercizio11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public @ResponseBody StudentEntity createStudent(@RequestBody StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
        return studentEntity;
    }

    @GetMapping("/listStudent")
    public List<StudentEntity> studentList() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Optional<StudentEntity> studentByID(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @PutMapping("/update/{id}")
    public StudentEntity updateStudent(@PathVariable Long id, @RequestBody StudentEntity students) {
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        if (studentEntity != null) {
            studentEntity.setId(students.getId());
            return studentRepository.save(studentEntity);
        }
        return null;
    }

    @PutMapping("/isworking/{id}")
    public @ResponseBody StudentEntity updateStatus(@PathVariable Long id, @RequestParam boolean working) {
        return studentService.setStudentIsWorking(id, working);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
