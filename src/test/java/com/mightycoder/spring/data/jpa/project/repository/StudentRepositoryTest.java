package com.mightycoder.spring.data.jpa.project.repository;

import com.github.javafaker.Faker;
import com.mightycoder.spring.data.jpa.project.entity.Guardian;
import com.mightycoder.spring.data.jpa.project.entity.Student;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private static Student student;
    private static Guardian guardian;

    @BeforeAll
    public static void setUp() {
        Faker faker = new Faker();
        guardian = Guardian.builder()
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .mobile(faker.phoneNumber().cellPhone())
                .build();
        student = Student.builder()
                .emailId(faker.internet().emailAddress())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .guardian(guardian)
                .build();
    }

    @Test
    @Order(1)
    public void saveStudentWithGuardianDetails() {
        System.out.println("saveStudentWithGuardianDetails");
        studentRepository.save(student);
        assertNotNull(student.getStudentId());
    }

    @Test
    @Order(2)
    public void printAllStudents() {
        System.out.println("printAllStudents");
        var students = studentRepository.findAll();
        students.forEach(student -> System.out.println("student: " + student));
        assertTrue(!students.isEmpty());
    }

    @Test
    @Order(3)
    public void findByName() {
        System.out.println("findByName");
        var students = studentRepository.findByFirstName(student.getFirstName());
        students.forEach(System.out::println);
        assertTrue(!students.isEmpty());
    }

    @Test
    @Order(4)
    public void findByLastNameContaining() {
        System.out.println("findByLastNameContaining");
        String lastName = student.getLastName().substring(1, student.getLastName().length() - 1).toUpperCase();
        System.out.println("lastName: " + lastName);
        var students = studentRepository.findByLastNameContainingIgnoreCase(lastName);
        students.forEach(System.out::println);
        assertTrue(!students.isEmpty());
    }

    @Test
    @Order(5)
    public void findByLastNameNotNull() {
        System.out.println("findByLastNameNotNull");
        var students = studentRepository.findByLastNameNotNull();
        students.forEach(System.out::println);
        assertTrue(!students.isEmpty());
    }

    @Test
    @Order(6)
    public void findByGuardianName() {
        System.out.println("findByLastNameNotNull");
        var students = studentRepository.findByGuardianName(guardian.getName());
        students.forEach(System.out::println);

        assertTrue(!students.isEmpty());
    }

    @Test
    @Order(7)
    public void findByFirstNameAndLastName() {
        System.out.println("findByFirstNameAndLastName: ");
        var students = studentRepository
                .findByFirstNameAndLastName(student.getFirstName(), student.getLastName());
        students.forEach(System.out::println);

        assertTrue(!students.isEmpty());
    }

    @Test
    @Order(8)
    public void getStudentByEmailAddress() {
        System.out.println("getStudentByEmailAddress");
        var studentDetails = studentRepository.getStudentFirstNameByEmailAddress(student.getEmailId());
        System.out.println("dbStudent: " + studentDetails);

        assertTrue(studentDetails != null);
    }

    @Test
    @Order(9)
    public void getStudentByEmailAddressNative() {
        System.out.println("getStudentByEmailAddressNative");
        var studentDetails = studentRepository.getStudentByEmailAddressNative(student.getEmailId());
        System.out.println("studentDetails: " + studentDetails);

        assertTrue(studentDetails != null);
    }

    @Test
    @Order(10)
    public void getStudentByEmailAddressNativeNamedParam() {
        System.out.println("getStudentByEmailAddressNativeNamedParam");
        var studentDetails = studentRepository.
                getStudentByEmailAddressNativeNamedParam(student.getEmailId());
        System.out.println("studentDetails: " + studentDetails);

        assertTrue(studentDetails != null);
    }

    @Test
    @Order(11)
    public void updateStudent() {
        System.out.println("updateStudent");
        var status = studentRepository.updateStudent(student.getEmailId(), "John", "Cronin");
        System.out.println(status);

        assertTrue(status == 0);
    }

    @Test
    @Order(12)
    public void updateStudentNameByEmailId() {
        System.out.println("updateStudentNameByEmailId");
        System.out.println("firstName: " + student.getFirstName() + "; studentId: " + student.getStudentId());
        var result = studentRepository.updateStudentNameByEmailId(new Faker().name().firstName(), student.getEmailId());

        System.out.println("result: " + result);
        assertEquals(1, result);
    }
}