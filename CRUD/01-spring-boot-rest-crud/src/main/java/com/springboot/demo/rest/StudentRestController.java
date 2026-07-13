package com.springboot.demo.rest;

import com.springboot.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students = new ArrayList<>();

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("Tom", "Hanks"));
        students.add(new Student("Peter", "Parker"));
        students.add(new Student("Tom", "Roy"));
    }
    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student is not found - " + studentId);
        }
        return students.get(studentId);
    }
}
