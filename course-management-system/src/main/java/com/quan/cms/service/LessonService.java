package com.quan.cms.service;

import com.quan.cms.dto.request.CreateLessonRequest;
import com.quan.cms.dto.response.LessonResponse;

public interface LessonService {

    LessonResponse createLesson(
            Long courseId,
            CreateLessonRequest request,
            String username
    );
}