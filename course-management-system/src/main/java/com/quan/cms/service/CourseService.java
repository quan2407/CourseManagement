package com.quan.cms.service;

import com.quan.cms.dto.request.CreateCourseRequest;
import com.quan.cms.dto.request.UpdateCourseRequest;
import com.quan.cms.dto.request.UpdateCourseStatusRequest;
import com.quan.cms.dto.response.CourseDetailResponse;
import com.quan.cms.dto.response.CourseResponse;
import com.quan.cms.enums.CourseStatus;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(
            CreateCourseRequest request
    );
    List<CourseResponse> getAllCourses(
            CourseStatus status
    );
    CourseDetailResponse getCourseById(
            Long courseId
    );
    CourseResponse updateCourse(
            Long courseId,
            UpdateCourseRequest request
    );
    CourseResponse updateCourseStatus(
            Long courseId,
            UpdateCourseStatusRequest request
    );
    void deleteCourse(Long courseId);
}