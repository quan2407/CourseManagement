package com.quan.cms.service;

import com.quan.cms.dto.response.TopCourseResponse;

import java.util.List;

public interface ReportService {

    List<TopCourseResponse> getTopCourses();
}