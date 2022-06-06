package com.springboot.university.web.controller;

import com.springboot.university.entity.Teacher;
import com.springboot.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/find/id/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable("id") Long id){
        Teacher teacher = teacherService.findById(id);
        if(teacher!=null){
            return new ResponseEntity<>(teacher, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<Teacher> findByName(@PathVariable("name") String name){
        Teacher teacher = teacherService.findByName(name);
        if(teacher!=null){
            return new ResponseEntity<>(teacher,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<Teacher> findByEmail(@PathVariable("email") String email){
        Teacher teacher = teacherService.findByEmail(email);
        if(teacher!=null){
            return new ResponseEntity<>(teacher,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Teacher>> findAll(){
        if(teacherService.findAll()!=null){
            return new ResponseEntity<>(teacherService.findAll(),HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher){
        Teacher t = teacherService.save(teacher);
        if(t!=null){
            return new ResponseEntity<>(t,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Teacher teacher = findById(id).getBody();
        if(teacher!=null){
            teacher.getCourses().stream().forEach(course -> {
                course.deleteTeacher();
                teacher.deleteCourse(course);
            });
            teacherService.delete(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAll(){
        if(!findAll().getBody().isEmpty()){
            findAll().getBody().stream().forEach(teacher -> {
                teacher.getCourses().stream().forEach(course -> {
                    course.deleteTeacher();
                    teacher.deleteCourse(course);
                });
            });
            teacherService.deleteAll();
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
