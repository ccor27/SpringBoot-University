package com.springboot.university.repository;

import com.springboot.university.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<Course> findById(Long id);
    List<Course> findAll();
    @Override
    Course save(Course course);
    @Override
    void delete(Course course);
    void deleteAll();
}
