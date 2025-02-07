package com.spring.student.Service;
import com.spring.student.dto.SchoolDto;
import com.spring.student.dto.StudentDto;
import com.spring.student.dto.StudentWithSchoolDto;
import org.springframework.web.client.RestTemplate;
import com.spring.student.entities.Student;
import com.spring.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    private final RestTemplate restTemplate;

    public StudentService(StudentRepository studentRepository, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public StudentWithSchoolDto getStudentWithSchool(String studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) return null;

        String url = "http://school/school/" + student.getSchoolId();
        SchoolDto school = restTemplate.getForObject(url, SchoolDto.class);
        StudentWithSchoolDto dto = new StudentWithSchoolDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setGenre(student.getGenre());
        dto.setSchool(school);

        return dto;
    }

    public Student saveStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public boolean existsById(String id) {
       return studentRepository.existsById(id);
    }
}