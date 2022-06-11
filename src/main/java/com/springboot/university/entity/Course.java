package com.springboot.university.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ApiModel("Class that represent a Course")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("It's create by the application, it's to identify the course")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    @ApiModelProperty("It's the teacher that learn in this course")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="students_enrolled",
            joinColumns = @JoinColumn(name ="course_id"),
            inverseJoinColumns = @JoinColumn(name ="student_id")
    )
    @ApiModelProperty("It's the list of the students that are enrolled in this course")
    private Set<Student> students = new HashSet<>();

    @ApiModelProperty("It's the name of the course")
    private String name;
    @ApiModelProperty("It's the description of the course")
    private String description;

    public Course(Teacher teacher, String name, String description) {
        this.teacher = teacher;
        this.name = name;
        this.description = description;
    }

    public Course(){

    }
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }
    public void deleteStudent(Student student){
        this.students.remove(student);
    }
    public void deleteTeacher(){
        this.teacher=null;
    }

}
