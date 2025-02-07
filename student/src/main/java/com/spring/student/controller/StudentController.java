package com.spring.student.controller;

import com.spring.student.Service.StudentService;
import com.spring.student.dto.StudentWithSchoolDto;
import com.spring.student.entities.Student;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentWithSchoolDto>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();

        List<StudentWithSchoolDto> studentWithSchoolDtos = new ArrayList<>();

        for (Student student : students) {
            String studentId = student.getId();
            StudentWithSchoolDto studentWithSchoolDto = studentService.getStudentWithSchool(studentId);
            if (studentWithSchoolDto != null) {
                studentWithSchoolDtos.add(studentWithSchoolDto);
            }
        }

        return new ResponseEntity<>(studentWithSchoolDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Optional<Student> student = Optional.ofNullable(studentService.getStudentById(id));
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        if (studentService.existsById(id)) {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}