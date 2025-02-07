package com.spring.school.service;
import com.spring.school.entity.School;
import com.spring.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public List<School> getAllSchools() {
        return (List<School>) schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public School saveSchool(School school) {
        schoolRepository.save(school);
        return school;
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return schoolRepository.existsById(id);
    }
}