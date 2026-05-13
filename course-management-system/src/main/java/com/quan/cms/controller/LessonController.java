package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateLessonRequest;
import com.quan.cms.dto.request.UpdateLessonPublishRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.LessonResponse;
import com.quan.cms.service.LessonService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/courses/{courseId}/lessons")
    public ApiResponse<LessonResponse> createLesson(

            @PathVariable Long courseId,

            @Valid
            @RequestBody
            CreateLessonRequest request,

            Authentication authentication
    ) {

        return ResponseUtil.success(
                "Lesson created successfully",
                lessonService.createLesson(
                        courseId,
                        request,
                        authentication.getName()
                )
        );
    }
    @GetMapping("/courses/{courseId}/lessons")
    public ApiResponse<List<LessonResponse>> getLessonsByCourse(
            @PathVariable Long courseId
    ) {

        return ResponseUtil.success(
                "Lessons retrieved successfully",
                lessonService.getLessonsByCourse(courseId)
        );
    }

    @PutMapping("/lessons/{lessonId}/publish")
    public ApiResponse<LessonResponse> updateLessonPublishStatus(

            @PathVariable Long lessonId,

            @Valid
            @RequestBody
            UpdateLessonPublishRequest request,

            Authentication authentication
    ) {

        return ResponseUtil.success(
                "Lesson publish status updated successfully",
                lessonService.updateLessonPublishStatus(
                        lessonId,
                        request,
                        authentication.getName()
                )
        );
    }
    @GetMapping("/lessons/{lessonId}")
    public ApiResponse<LessonResponse> getLessonById(
            @PathVariable Long lessonId
    ) {

        return ResponseUtil.success(
                "Lesson retrieved successfully",
                lessonService.getLessonById(lessonId)
        );
    }
}