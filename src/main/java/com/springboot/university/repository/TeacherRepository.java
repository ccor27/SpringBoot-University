package com.springboot.university.repository;

import com.springboot.university.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Optional<Teacher> findById(Long id);
    Teacher findByName(String name);
    Teacher findByEmail(String email);
    List<Teacher> findAll();
    Teacher save(Teacher teacher);
    void delete(Teacher teacher);
    void deleteAll();
}
