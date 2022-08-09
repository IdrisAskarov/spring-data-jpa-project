package com.mightycoder.spring.data.jpa.project.repository;

import com.mightycoder.spring.data.jpa.project.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
