package com.example.techeazy.Service;

import com.example.techeazy.Entity.Student;
import com.example.techeazy.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
}