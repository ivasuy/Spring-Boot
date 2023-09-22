package com.vasu.spring.data.jpa.repository;

import com.vasu.spring.data.jpa.entity.Course;
import com.vasu.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseDBMS = Course.builder()
                .title("DBMS")
                .credit(6)
                .build();

        Course courseJava = Course.builder()
                .title("Java")
                .credit(8)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Prateek")
                .lastName("Thakral")
//                .courses(List.of(courseDBMS, courseJava))
                .build();

        teacherRepository.save(teacher);
    }

}