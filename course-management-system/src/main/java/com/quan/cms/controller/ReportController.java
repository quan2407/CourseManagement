package com.quan.cms.controller;

import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.TopCourseResponse;
import com.quan.cms.service.ReportService;
import com.quan.cms.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/top_courses")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<TopCourseResponse>>
    getTopCourses() {

        return ResponseUtil.success(
                "Top courses retrieved successfully",
                reportService.getTopCourses()
        );
    }
}