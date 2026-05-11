package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateCourseRequest;
import com.quan.cms.dto.request.UpdateCourseRequest;
import com.quan.cms.dto.request.UpdateCourseStatusRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.CourseDetailResponse;
import com.quan.cms.dto.response.CourseResponse;
import com.quan.cms.enums.CourseStatus;
import com.quan.cms.service.CourseService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ApiResponse<List<CourseResponse>> getAllCourses(

            @RequestParam(required = false)
            CourseStatus status
    ) {

        return ResponseUtil.success(
                "Courses retrieved successfully",
                courseService.getAllCourses(status)
        );
    }
    @GetMapping("/{courseId}")
    public ApiResponse<CourseDetailResponse> getCourseById(
            @PathVariable Long courseId
    ) {

        return ResponseUtil.success(
                "Course retrieved successfully",
                courseService.getCourseById(courseId)
        );
    }
    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<CourseResponse> updateCourse(

            @PathVariable Long courseId,

            @Valid
            @RequestBody
            UpdateCourseRequest request
    ) {

        return ResponseUtil.success(
                "Course updated successfully",
                courseService.updateCourse(
                        courseId,
                        request
                )
        );
    }

    @PutMapping("/{courseId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<CourseResponse> updateCourseStatus(

            @PathVariable Long courseId,

            @Valid
            @RequestBody
            UpdateCourseStatusRequest request
    ) {

        return ResponseUtil.success(
                "Course status updated successfully",
                courseService.updateCourseStatus(
                        courseId,
                        request
                )
        );
    }
}