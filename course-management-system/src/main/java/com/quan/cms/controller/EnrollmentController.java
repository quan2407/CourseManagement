package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateEnrollmentRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.EnrollmentResponse;
import com.quan.cms.service.EnrollmentService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ApiResponse<EnrollmentResponse> enrollCourse(

            @Valid
            @RequestBody
            CreateEnrollmentRequest request,

            Authentication authentication
    ) {

        return ResponseUtil.success(
                "Course enrolled successfully",
                enrollmentService.enrollCourse(
                        request,
                        authentication.getName()
                )
        );
    }
    @GetMapping
    public ApiResponse<List<EnrollmentResponse>> getMyEnrollments(
            Authentication authentication
    ) {

        return ResponseUtil.success(
                "Enrollments retrieved successfully",
                enrollmentService.getMyEnrollments(
                        authentication.getName()
                )
        );
    }
}