package com.spring.school.repository;

import com.spring.school.entity.School;
import org.springframework.data.repository.CrudRepository;

public interface SchoolRepository extends CrudRepository<School, Long> {

}
