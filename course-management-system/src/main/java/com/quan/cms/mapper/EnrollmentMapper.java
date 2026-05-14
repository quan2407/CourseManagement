package com.quan.cms.mapper;

import com.quan.cms.dto.response.EnrollmentResponse;
import com.quan.cms.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(
            target = "studentName",
            expression = "java(enrollment.getStudent().getFullName())"
    )
    @Mapping(
            target = "courseTitle",
            expression = "java(enrollment.getCourse().getTitle())"
    )
    EnrollmentResponse toResponse(
            Enrollment enrollment
    );
}