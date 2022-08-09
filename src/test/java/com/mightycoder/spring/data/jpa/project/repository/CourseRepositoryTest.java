package com.mightycoder.spring.data.jpa.project.repository;

import com.github.javafaker.Faker;
import com.mightycoder.spring.data.jpa.project.entity.Course;
import com.mightycoder.spring.data.jpa.project.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        var courses = courseRepository.findAll();
        courses.forEach(System.out::println);
    }

    @Test
    public void saveCourse(){
        Course course = Course.builder()
                .credit(1)
                .courseTitle("ALG")
                .build();
        courseRepository.save(course);
        assertNotNull(course.getCourseId());
    }

    @Test
    public void saveCourseWithTeacherObject(){
        Faker faker = new Faker();
        Course course = Course
                .builder()
                .courseTitle("python")
                .credit(6)
                .teacher(Teacher
                        .builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .build())
                .build();

        courseRepository.save(course);
        assertNotNull(course.getCourseId());
    }
}