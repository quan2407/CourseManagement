package com.quan.cms.dto.request;

import com.quan.cms.enums.CourseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseStatusRequest {

    @NotNull(message = "Status is required")
    private CourseStatus status;
}