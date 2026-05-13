package com.quan.cms.controller;

import com.quan.cms.dto.request.CreateLessonRequest;
import com.quan.cms.dto.response.ApiResponse;
import com.quan.cms.dto.response.LessonResponse;
import com.quan.cms.service.LessonService;
import com.quan.cms.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}