package com.quan.cms.service;

import com.quan.cms.dto.request.CreateCourseRequest;
import com.quan.cms.dto.response.CourseResponse;

public interface CourseService {

    CourseResponse createCourse(
            CreateCourseRequest request
    );
}