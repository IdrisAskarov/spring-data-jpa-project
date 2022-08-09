package com.mightycoder.spring.data.jpa.project.repository;

import com.github.javafaker.Faker;
import com.mightycoder.spring.data.jpa.project.entity.Course;
import com.mightycoder.spring.data.jpa.project.entity.CourseMaterial;
import org.assertj.core.api.Assertions;
import org.hibernate.PersistentObjectException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourseMaterial() {
        System.out.println("saveCourseMaterial");
        Course course = Course.builder()
                .courseTitle("DSA")
                .credit(6)
                .build();
        CourseMaterial courseMaterial = CourseMaterial
                .builder()
                .url(new Faker().internet().url())
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);

        System.out.println("courseMaterial: " + courseMaterial);

        assertNotNull(courseMaterial.getCourseMaterialId());
    }

    @Test
    public void saveCourseMaterialWithExistingCourse() {
        System.out.println("saveCourseMaterialWithExistingCourse");

        Course course = courseRepository.findAll().stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("failed to get any Course the table is empty"));

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url(new Faker().internet().url())
                .course(course)
                .build();


        assertThrows(InvalidDataAccessApiUsageException.class, () -> courseMaterialRepository.save(courseMaterial));
    }

    @Test
    public void findByCourse_CourseId() {
        System.out.println("findByCourse_CourseId");

        List<CourseMaterial> courseMaterials = courseMaterialRepository.findByCourse_CourseId(2l);

        System.out.println("courseMaterials:" + courseMaterials);

        assertTrue(!courseMaterials.isEmpty());
    }

    @Test
    public void printAllCourseMaterials(){
        System.out.println("printAllCourseMaterials");
        var  courseMaterials = courseMaterialRepository.findAll();

        System.out.println(courseMaterials);
        courseMaterials.forEach(courseMaterial -> System.out.println(courseMaterial.getCourse()));


    }

}