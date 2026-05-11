package com.quan.cms.mapper;

import com.quan.cms.dto.request.CreateCourseRequest;
import com.quan.cms.dto.response.CourseDetailResponse;
import com.quan.cms.dto.response.CourseResponse;
import com.quan.cms.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "courseId", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    Course toEntity(CreateCourseRequest request);

    @Mapping(
            target = "teacherName",
            expression = "java(course.getTeacher().getFullName())"
    )
    @Mapping(
            target = "status",
            expression = "java(course.getStatus().name())"
    )
    CourseResponse toResponse(Course course);
    @Mapping(
            target = "teacherName",
            expression = "java(course.getTeacher().getFullName())"
    )
    @Mapping(
            target = "status",
            expression = "java(course.getStatus().name())"
    )
    CourseDetailResponse toDetailResponse(Course course);
}