package com.quan.cms.mapper;

import com.quan.cms.dto.request.CreateLessonRequest;
import com.quan.cms.dto.response.LessonResponse;
import com.quan.cms.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponse toResponse(Lesson lesson);

    @Mapping(target = "lessonId", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "isPublished", ignore = true)
    @Mapping(target = "lessonProgresses", ignore = true)
    Lesson toEntity(CreateLessonRequest request);
}