package com.quan.cms.repository;

import com.quan.cms.entity.Course;
import com.quan.cms.enums.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStatus(CourseStatus status);
}