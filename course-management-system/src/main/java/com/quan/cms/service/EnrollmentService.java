package com.quan.cms.service;

import com.quan.cms.dto.request.CreateEnrollmentRequest;
import com.quan.cms.dto.response.EnrollmentDetailResponse;
import com.quan.cms.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse enrollCourse(
            CreateEnrollmentRequest request,
            String username
    );
    List<EnrollmentResponse> getMyEnrollments(
            String username
    );
    EnrollmentDetailResponse getEnrollmentDetail(
            Long enrollmentId,
            String username
    );
}