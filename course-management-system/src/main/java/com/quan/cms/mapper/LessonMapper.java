package com.quan.cms.mapper;

import com.quan.cms.dto.response.LessonResponse;
import com.quan.cms.entity.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponse toResponse(Lesson lesson);
}