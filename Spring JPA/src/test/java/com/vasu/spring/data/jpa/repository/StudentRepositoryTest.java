package com.vasu.spring.data.jpa.repository;

import com.vasu.spring.data.jpa.entity.Guardian;
import com.vasu.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("vasu7yadav@gmail.com")
                .firstName("Vasu")
                .lastName("Yadav")
//                .guardianName("xyz")
//                .guardianEmail("xyz@gmail.com")
//                .guardianMobile("999999999")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("abc")
                .email("abc@gmail.com")
                .mobileNumber("7999999990")
                .build();

        Student student = Student.builder()
                .firstName("Daksh")
                .lastName("Malik")
                .emailId("malik@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }


    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("Vasu");
        System.out.println("students = " + studentList);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("D");
        System.out.println("students = " + studentList);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> studentList = studentRepository.findByGuardianName("xyz");
        System.out.println("students = " + studentList);
    }

    @Test
    public void printgetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("malik@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudentFirstNameByEmailAddress(
                "vasu7yadav@gmail.com"
        );

        System.out.println("firstName = " + firstName);
    }

    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("vasu7yadav@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printgetStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("vasu7yadav@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId("Daksh","malik@gmail.com");
    }

}