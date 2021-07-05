package com.example.firstApi.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class Studentservice {
    private final StudentRepository studentRepository;
    @Autowired
    public Studentservice(StudentRepository studentRepository) {this.studentRepository = studentRepository; }

    public List<Student> getStudents() {return studentRepository.findAll();}
    public void addNewStudent (Student newStudent) {
        Optional<Student> exists = studentRepository.findStudentByEmail(newStudent.getEmail());
        if(exists.isPresent()){
            throw new IllegalStateException("email already exists");
        }
        studentRepository.save(newStudent);
    }

    public String deletedStudent(Long id) {
        boolean Id = studentRepository.existsById(id);
        if(!Id) {
            throw new IllegalStateException("id nao encontrado");
        }
        studentRepository.deleteById(id);
        return "aluno deletado com successo";
    }
    @Transactional
    public Student editUpdate(Long id, Student student) {
        Student Id = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("nao existe o id"));
                Id.setName(student.getName());
                Id.setEmail(student.getEmail());
                Id.setAge(student.getAge());
                return Id;
    }
}
