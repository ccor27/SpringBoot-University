package com.springboot.university.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ApiModel("Class that represent a teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("It's create by the application, it's to identify the teacher")
    private Long id;
    @ApiModelProperty("It's the name of the teacher")
    private String name;
    @ApiModelProperty("It's the last name of the teacher")
    private String lastName;
    @ApiModelProperty("It's the email of the teacher")
    private String email;
    @ApiModelProperty("It's the certification of the teacher")
    private String degree;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>();

    public Teacher(String name, String lastName, String email, String degree) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.degree = degree;
    }

    public Teacher(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }
    public void deleteCourse(Course course){
        this.courses.remove(course);
    }
}
