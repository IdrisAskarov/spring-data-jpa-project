package com.mightycoder.spring.data.jpa.project.repository;

import com.mightycoder.spring.data.jpa.project.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
