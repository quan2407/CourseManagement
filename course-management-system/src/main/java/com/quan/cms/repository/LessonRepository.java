package com.quan.cms.repository;

import com.quan.cms.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseCourseIdAndIsPublishedTrue(
            Long courseId
    );
    Optional<Lesson> findByLessonIdAndIsPublishedTrue(
            Long lessonId
    );
}