package com.spring.school.controller;
import com.spring.school.entity.School;
import com.spring.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> school = (List<School>) schoolService.getAllSchools();
        return new ResponseEntity<>(school, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable long id) {
        Optional<School> school = Optional.ofNullable(schoolService.getSchoolById(id));
        return school.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        School saveSchool = schoolService.saveSchool(school);
        return new ResponseEntity<>(saveSchool, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable long id) {
        if (schoolService.existsById(id)) {
            schoolService.deleteSchool(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}