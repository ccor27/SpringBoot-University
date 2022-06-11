package com.springboot.university;

import com.springboot.university.entity.Course;
import com.springboot.university.entity.Student;
import com.springboot.university.entity.Teacher;
import com.springboot.university.repository.CourseRepository;
import com.springboot.university.repository.StudentRepository;
import com.springboot.university.repository.TeacherRepository;
import com.springboot.university.service.CourseService;
import com.springboot.university.service.StudentService;
import com.springboot.university.service.TeacherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class UniversityApplication {

	public static void main(String[] args) {
        ApplicationContext context =SpringApplication.run(UniversityApplication.class, args);

        TeacherService teacherService = context.getBean(TeacherService.class);
        StudentService studentService= context.getBean(StudentService.class);
        CourseService courseService = context.getBean(CourseService.class);

        Teacher teacher = new Teacher("John","Perez","john@gmail.com","system engineer");

        Student student1 = new Student("Alex","Torres","alex@gmail.com");
        Student student2 = new Student("Maria","Colorado","maria@gmail.com");
        Student student3 = new Student("Hana","Beaker","hana@gmail.com");
        Student student4 = new Student("Charlie","Brown","charlie@gmail.com");
        Student student5 = new Student("Emilia","Cena","emilia@gmail.com");

        Course course = new Course(teacher,"Java II","course to learn java level II");

        course.addStudent(student1);
        course.addStudent(student2);
        course.addStudent(student3);
        course.addStudent(student4);
        course.addStudent(student5);

        student1.addCourse(course);
        student2.addCourse(course);
        student3.addCourse(course);
        student4.addCourse(course);
        student5.addCourse(course);

        teacher.addCourse(course);

        courseService.save(course);
        teacherService.save(teacher);
        studentService.save(student1);
        studentService.save(student2);
        studentService.save(student3);
        studentService.save(student4);
        studentService.save(student5);


	}

}
