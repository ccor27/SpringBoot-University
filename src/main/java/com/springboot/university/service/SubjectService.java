package com.springboot.university.service;

import com.springboot.university.entity.Subject;
import com.springboot.university.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject findById(Long id){
        if(subjectRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return subjectRepository.findById(id).get();
        }
    }
    public Subject findByName(String name){
        if(subjectRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return subjectRepository.findByName(name);
        }
    }
    public Subject save(Subject subject){

            if(subjectRepository.findAll().contains(subject)){
                //the subject exist, you can't duplicate
                return null;
            }else{
                return subjectRepository.save(subject);
            }

    }

    public List<Subject> findAll(){
        if(subjectRepository.findAll().isEmpty()){
            //the list is empty
            return null;
        }else{
            return subjectRepository.findAll();
        }
    }

    public boolean delete(Long id){
        Subject s = findById(id);
        if(s!=null){
            //the subject exist
            subjectRepository.delete(s);
            return true;
        }else{
            //the subject not exist
            return false;
        }
    }

    public boolean deleteAll(){
        if(subjectRepository.findAll().isEmpty()){
            //the list is empty
            return false;
        }else{
            return true;
        }
    }
}
