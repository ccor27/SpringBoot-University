package com.springboot.university.web.controller;

import com.springboot.university.entity.Student;
import com.springboot.university.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @ApiOperation("Method to create a Student")
     @PostMapping("/create")
    public ResponseEntity<Student> save(@RequestBody Student student){
        Student stu = studentService.save(student);
        if(stu!=null){
            return new ResponseEntity<>(stu,HttpStatus.CREATED);
        }else{
            //the student exist
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @ApiOperation("Method to find a Student by id")
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Long id){
       Student student = studentService.findById(id);
        if(student!=null){
            return new ResponseEntity<>(student,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Method to find a Student by name")
    @GetMapping("/find/name/{name}")
    public ResponseEntity<Student> findByName(@PathVariable("name") String name){
        Student student = studentService.findByName(name);
        if(student!=null){
            return new ResponseEntity<>(student,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiOperation("Method to find a Student by email")
    @GetMapping("/find/email/{email}")
    public ResponseEntity<Student> findByEmail(@PathVariable("email") String email){
        Student student = studentService.findByEmail( email);
        if(student!=null){
            return new ResponseEntity<>(student,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Method to find all Students")
    @GetMapping("/find/all")
    public ResponseEntity<List<Student>> findAll(){

        List<Student> list = studentService.findAll();
        if(list!=null){
            return new ResponseEntity<>(list,HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation("Method to delete a Student by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
         Student student = findById(id).getBody();

        if (student!=null){

            student.getCourses().stream().forEach(course -> {
                student.deleteCourse(course);
                course.deleteStudent(student);
              });

            studentService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    @ApiOperation("Method to delete all Students")
    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAll(){
        if (studentService.findAll()!=null){
            findAll().getBody().stream().forEach(student -> {
                student.getCourses().stream().forEach(course -> {
                    student.deleteCourse(course);
                    course.deleteStudent(student);
                });
            });
            studentService.deleteAll();
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
