package com.quan.cms.service;

import com.quan.cms.dto.request.CreateLessonRequest;
import com.quan.cms.dto.request.UpdateLessonPublishRequest;
import com.quan.cms.dto.request.UpdateLessonRequest;
import com.quan.cms.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {

    LessonResponse createLesson(
            Long courseId,
            CreateLessonRequest request,
            String username
    );
    List<LessonResponse> getLessonsByCourse(
            Long courseId
    );
    LessonResponse updateLessonPublishStatus(
            Long lessonId,
            UpdateLessonPublishRequest request,
            String username
    );
    LessonResponse getLessonById(
            Long lessonId
    );
    LessonResponse updateLesson(
            Long lessonId,
            UpdateLessonRequest request,
            String username
    );
}