package com.example.Esercizio11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @PostMapping("/create")
    public StudentEntity createStudent(@RequestBody StudentEntity studentEntity){
        studentRepository.save(studentEntity);
     return studentEntity;
    }
   @GetMapping("/listStudent")
    public List<StudentEntity> studentList(){
        return studentRepository.findAll();
    }
    @GetMapping("students/{id}")
    public Optional<StudentEntity> studentById(@PathVariable Long id){
    return studentRepository.findById(id);
    }
    @PutMapping("/update/{id}")
    public StudentEntity update(@PathVariable Long id, @RequestBody StudentEntity students){
        StudentEntity studentEntity = studentRepository.findById(id).orElse(null);
        if(studentEntity != null){
            students.setId(id);
            return  studentRepository.save(students);
        }
     return null;
    }
    @PutMapping("/updateStatus/{id}")
    public void upStatus(@PathVariable Long id, @RequestParam boolean working){
        studentService.setStudentWorking(id,working);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        studentRepository.deleteById(id);
    }
}
