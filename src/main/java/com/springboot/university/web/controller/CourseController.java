package com.springboot.university.web.controller;

import com.springboot.university.entity.Course;
import com.springboot.university.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("Method to find a Course by id")
    @GetMapping("/find/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") Long id){
        Course c = courseService.findById(id);
        if(c!=null){
            return new ResponseEntity<>(c, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(c, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Method to find all courses")
    @GetMapping("/find/all")
    public ResponseEntity<List<Course>> findAll(){
        List<Course> list = courseService.findAll();
        if(list!=null){
            return new ResponseEntity<>(list,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation("Method to create a Course")
    @PostMapping("/create")
    public ResponseEntity<Course> save(@RequestBody Course course){
        Course c = courseService.save(course);
        if(c!=null){
            return new ResponseEntity<>(c,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @ApiOperation("Method to delete a Course by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Course course = findById(id).getBody();
        if(course!=null){
            course.getStudents()
                    .stream().forEach(student -> {
                      course.deleteStudent(student);
                      student.deleteCourse(course);
                    });
            courseService.delete(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation("Method to delete all courses")
    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAll(){
        if(findAll().getBody()!=null){
            findAll().getBody().stream().forEach(course -> {
                course.getStudents().stream().forEach(student -> {
                    course.deleteStudent(student);
                    student.deleteCourse(course);
                });
            });
            courseService.deleteAll();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
