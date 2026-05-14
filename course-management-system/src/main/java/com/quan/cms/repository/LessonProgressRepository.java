package com.quan.cms.repository;

import com.quan.cms.entity.LessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {
    Optional<LessonProgress>
    findByEnrollmentEnrollmentIdAndLessonLessonId(
            Long enrollmentId,
            Long lessonId
    );
    long countByEnrollmentEnrollmentId(
            Long enrollmentId
    );
}