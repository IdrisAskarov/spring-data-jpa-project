package com.mightycoder.spring.data.jpa.project.repository;

import com.mightycoder.spring.data.jpa.project.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {

    @Transactional
    List<CourseMaterial> findByCourse_CourseId(Long courseId);
}
