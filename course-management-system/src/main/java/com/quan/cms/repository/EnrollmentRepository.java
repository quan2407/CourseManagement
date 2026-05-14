package com.quan.cms.repository;

import com.quan.cms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentUserIdAndCourseCourseId(
            Long studentId,
            Long courseId
    );
    List<Enrollment> findByStudentUsername(
            String username
    );
}