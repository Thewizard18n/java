package com.example.firstApi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="estudante")
public class Studencontroller {
    private final Studentservice studentservice;
    @Autowired
    public Studencontroller(Studentservice studentservice) {this.studentservice = studentservice; }
    @GetMapping
    public List<Student> getStudents() { return studentservice.getStudents(); }
    @PostMapping(path="criar")
    public void addStudent (@RequestBody Student student) { studentservice.addNewStudent(student);}
    @DeleteMapping(path = "edit/{id}")
    public String deletedStudent(@PathVariable("id") Long id){
        return studentservice.deletedStudent(id);
    }
    @PutMapping(path = "edit/{id}")
    public Student editStudent(@PathVariable("id") Long id , @RequestBody Student student){return studentservice.editUpdate(id , student);}
}
