package com.quan.cms.service.impl;

import com.quan.cms.dto.response.TopCourseResponse;
import com.quan.cms.repository.EnrollmentRepository;
import com.quan.cms.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl
        implements ReportService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public List<TopCourseResponse> getTopCourses() {

        return enrollmentRepository.getTopCourses();
    }
}