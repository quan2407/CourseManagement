package com.quan.cms.mapper;

import com.quan.cms.dto.request.CreateLessonRequest;
import com.quan.cms.dto.request.UpdateLessonRequest;
import com.quan.cms.dto.response.LessonResponse;
import com.quan.cms.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponse toResponse(Lesson lesson);

    @Mapping(target = "lessonId", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "isPublished", ignore = true)
    @Mapping(target = "lessonProgresses", ignore = true)
    Lesson toEntity(CreateLessonRequest request);

    @Mapping(target = "lessonId", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "isPublished", ignore = true)
    @Mapping(target = "lessonProgresses", ignore = true)
    void updateLessonFromRequest(
            UpdateLessonRequest request,
            @MappingTarget Lesson lesson
    );
}