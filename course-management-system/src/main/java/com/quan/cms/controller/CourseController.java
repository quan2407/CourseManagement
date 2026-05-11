package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateCourseRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.CourseResponse;
import com.quan.cms.service.CourseService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<CourseResponse> createCourse(

            @Valid
            @RequestBody
            CreateCourseRequest request
    ) {

        return ResponseUtil.success(
                "Course created successfully",
                courseService.createCourse(request)
        );
    }
}