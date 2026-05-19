package com.quan.cms.repository;

import com.quan.cms.dto.response.TopCourseResponse;
import com.quan.cms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudentUserIdAndCourseCourseId(
            Long studentId,
            Long courseId
    );
    List<Enrollment> findByStudentUsername(
            String username
    );
    @Query("""
    SELECT
        new com.quan.cms.dto.response.TopCourseResponse(
            c.courseId,
            c.title,
            COUNT(e)
        )
    FROM Enrollment e
    JOIN e.course c
    GROUP BY c.courseId, c.title
    ORDER BY COUNT(e) DESC
""")
    List<TopCourseResponse> getTopCourses();
}