package com.quan.cms.service;

import com.quan.cms.dto.request.CreateLessonRequest;
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
}