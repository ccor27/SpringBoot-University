package com.springboot.university.service;

import com.springboot.university.entity.Course;
import com.springboot.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course findById(Long id){
        if(courseRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            Course course = courseRepository.findById(id).get();
            if(course!=null){
                return course;
            }else{
                return null;
            }
        }
    }
    public List<Course> findAll(){
        if (courseRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return courseRepository.findAll();
        }
    }

    public Course save(Course course){

            if (courseRepository.findAll().contains(course)){
                //the course exit, you can't have duplicate
                return null;
            }else{
                //the course not exist, you can register it
                return courseRepository.save(course);
            }
    }

    public boolean delete(Long id){
        Course c = findById(id);
        if(c!=null){
            //the course exist
            courseRepository.delete(c);
            return true;
        }else{
            //the course not exist
            return false;
        }
    }

    public boolean deleteAll(){
        if(courseRepository.findAll().isEmpty()){
            //the list is empty
            return false;
        }else{
            courseRepository.deleteAll();;
            return true;
        }
    }
}
