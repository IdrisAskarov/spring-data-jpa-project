package com.mightycoder.spring.data.jpa.project.repository;

import com.github.javafaker.Faker;
import com.mightycoder.spring.data.jpa.project.entity.Course;
import com.mightycoder.spring.data.jpa.project.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTacher() {
        System.out.println("saveTacher()");
        Faker faker = new Faker();
        Course courseDBA = Course.builder()
                .courseTitle("DBA")
                .credit(5)
                .build();
        Course courseJava = Course.builder()
                .courseTitle("Java")
                .credit(6)
                .build();

        Teacher teacher = Teacher
                .builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().firstName())
//                .courses(List.of(courseDBA,courseJava))
                .build();

        teacherRepository.save(teacher);

        assertNotNull(teacher.getTeacherId());
    }

}