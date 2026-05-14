package com.quan.cms.repository;

import com.quan.cms.entity.Course;
import com.quan.cms.enums.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStatus(CourseStatus status);
    List<Course>
    findByStatusAndTitleContainingIgnoreCaseOrStatusAndDescriptionContainingIgnoreCase(

            CourseStatus status1,

            String titleKeyword,

            CourseStatus status2,

            String descriptionKeyword
    );
    List<Course>
    findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(

            String titleKeyword,

            String descriptionKeyword
    );
    List<Course> findByTeacherUserId(
            Long teacherId
    );
    List<Course> findByTeacherUserIdAndStatus(
            Long teacherId,
            CourseStatus status
    );
}