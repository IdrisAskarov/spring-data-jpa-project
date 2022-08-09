package com.mightycoder.spring.data.jpa.project.repository;

import com.github.javafaker.Faker;
import com.mightycoder.spring.data.jpa.project.entity.Course;
import com.mightycoder.spring.data.jpa.project.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        var courses = courseRepository.findAll();
        courses.forEach(System.out::println);
    }

    @Test
    public void saveCourse() {
        Course course = Course.builder()
                .credit(1)
                .courseTitle("ALG")
                .build();
        courseRepository.save(course);
        assertNotNull(course.getCourseId());
    }

    @Test
    public void saveCourseWithTeacherObject() {
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

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithThooRecords = PageRequest.of(1, 2);

        var coursePages = courseRepository.findAll(firstPageWithThreeRecords);
        var courses = coursePages.getContent();
        var totalElements = coursePages.getTotalElements();
        var totalPages = coursePages.getTotalPages();
        courses.forEach(course -> System.out.println("courseId: " + course.getCourseId()));
        System.out.println("totalElements: " + totalElements);
        System.out.println("totalPages: " + totalPages);


        coursePages = courseRepository.findAll(secondPageWithThooRecords);
        courses = coursePages.getContent();
        totalElements = coursePages.getTotalElements();
        totalPages = coursePages.getTotalPages();
        courses.forEach(course -> System.out.println("courseId: " + course.getCourseId()));
        System.out.println("totalElements: " + totalElements);
        System.out.println("totalPages: " + totalPages);

    }

    @Test
    public void findAllSorting() {
        var sortByTitle = PageRequest.of(
                0,
                5,
                Sort.by("courseTitle"));

        var sortByCreditDesc = Sort.by("credit").descending();
        var sortByCreditDescPages = PageRequest.of(
                0,
                8,
                sortByCreditDesc);
        var sortByTitleAndCreditDesc = PageRequest.of(
                0,
                20,
                Sort.by("courseTitle").descending().and(Sort.by("credit").descending())
        );

        var pages = courseRepository.findAll(sortByTitleAndCreditDesc);
        var courses = pages.getContent();
        courses.forEach(System.out::println);
    }

    @Test
    public void findByCourseTitleContaining() {
        var firstPage = PageRequest.of(0, 10,Sort.by("courseId").descending());
        var courses = courseRepository.findByCourseTitleContaining("D", firstPage).getContent();
        courses.forEach(System.out::println);
    }

}