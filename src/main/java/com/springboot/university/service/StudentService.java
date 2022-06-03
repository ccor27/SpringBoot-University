package com.springboot.university.service;

import com.springboot.university.entity.Student;
import com.springboot.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student){

            if(studentRepository.findAll().contains(student)){
                //the student exist, you can't have duplicate
                return null;
            }else{
                // the student not exist, you can register him
                return studentRepository.save(student);
            }

    }
    public Student findById(Long id){

        if(studentRepository.findAll().isEmpty()){
            // the list is empty
            return null;
        }else{
            Student student = studentRepository.findById(id).get();
            if(student!=null){
                return student;
            }else{
             return null;
            }
        }
    }
    public Student findByName(String name){

        if(studentRepository.findAll().isEmpty()){
            // the list is empty
            return null;
        }else{
            Student student =studentRepository.findByName(name);
            if(student!=null){
                return student;
            }else{
                return null;
            }
        }

    }
    public Student findByEmail(String email){

        if(studentRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            Student student = studentRepository.findByEmail(email);
            if(student!=null){
                return student;
            }else{
                return null;
            }
        }
    }
    public List<Student> findAll(){

        if(studentRepository.findAll().isEmpty()){
            // the list is empty
            return null;
        }else{
            return studentRepository.findAll();
        }
    }
    public boolean delete(Long id){
        Student s = findById(id);
        if(s!=null){
            studentRepository.delete(s);
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteAll(){
        if(studentRepository.findAll().isEmpty()){
            return false;
        }else{
            studentRepository.deleteAll();
            return true;
        }
    }

}
