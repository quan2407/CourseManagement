package com.quan.cms.service;

import com.quan.cms.dto.request.CreateEnrollmentRequest;
import com.quan.cms.dto.response.EnrollmentResponse;

public interface EnrollmentService {

    EnrollmentResponse enrollCourse(
            CreateEnrollmentRequest request,
            String username
    );
}