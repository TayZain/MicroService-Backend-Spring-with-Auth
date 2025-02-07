package com.spring.student.repositories;
import com.spring.student.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

}