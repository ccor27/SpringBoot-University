package com.springboot.university.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany
    @JoinTable(
            name="students_enrolled",
            joinColumns = @JoinColumn(name ="course_id"),
            inverseJoinColumns = @JoinColumn(name ="student_id")
    )
    private Set<Student> students = new HashSet<>();

    private String name;
    private String description;

    public Course(Teacher teacher, String name, String description) {
        this.teacher = teacher;
        this.name = name;
        this.description = description;
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

}
