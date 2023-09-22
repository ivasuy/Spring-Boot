package com.vasu.spring.data.jpa.repository;

import com.vasu.spring.data.jpa.entity.Course;
import com.vasu.spring.data.jpa.entity.Student;
import com.vasu.spring.data.jpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Arvind")
                .lastName("Kumar")
                .build();

        Course course = Course.builder()
                .title("SEP")
                .credit(4)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1,2);

//        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
//        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
//        long totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
//
//        System.out.println("totalPages = " + totalPages);
//        System.out.println("totalElements = " + totalElements);
//        System.out.println("courses = " + courses);
        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();
        long totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0,2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();

        System.out.println("courses = " + courses);

    }

    @Test
    public void printfindByTitleContaining(){

        Pageable firstPageTenRecords = PageRequest.of(0, 10);
        List<Course> courses = courseRepository.findByTitleContaining(
                "D",
                firstPageTenRecords
        ).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Ekta")
                .lastName("Yadav")
                .build();

        Student student = Student.builder()
                .firstName("Himanshu")
                .lastName("Patel")
                .emailId("xyz@gmail.com")
                .build();

        Course course = Course.builder()
                .title("OOPS")
                .credit(2)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);

    }

}