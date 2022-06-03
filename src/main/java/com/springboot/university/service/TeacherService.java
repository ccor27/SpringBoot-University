package com.springboot.university.service;

import com.springboot.university.entity.Teacher;
import com.springboot.university.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher findById(Long id){
        if(teacherRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return teacherRepository.findById(id).get();
        }
    }
    public Teacher findByName(String name){
        if(teacherRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return teacherRepository.findByName(name);
        }
    }
    public Teacher findByEmail(String email){
        if(teacherRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return teacherRepository.findByEmail(email);
        }
    }
    public List<Teacher> findAll(){
        if(teacherRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return teacherRepository.findAll();
        }
    }
    public Teacher save(Teacher teacher){
            if(teacherRepository.findAll().contains(teacher)){
                //the teacher exist, you can't have duplicate
                return null;
            }else {
                return teacherRepository.save(teacher);
            }

    }

    public boolean delete(Long id){
        Teacher t = findById(id);
        if(t!=null){
            //the teacher exist
            teacherRepository.delete(t);
            return true;
        }else{
            // the teacher not exist
            return false;
        }
    }

    public boolean deleteAll(){
        if(teacherRepository.findAll().isEmpty()){
            //the list is empty
            return false;
        }else{
            teacherRepository.deleteAll();
            return true;
        }
    }
}
