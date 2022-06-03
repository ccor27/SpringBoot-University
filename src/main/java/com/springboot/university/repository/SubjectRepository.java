package com.springboot.university.repository;

import com.springboot.university.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    Optional<Subject> findById(Long id);
    Subject findByName(String name);
    Subject save(Subject subject);
    List<Subject> findAll();
    void delete(Subject subject);
    void deleteAll();
}
