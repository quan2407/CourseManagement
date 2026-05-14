package com.quan.cms.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEnrollmentRequest {

    @NotNull(message = "Course ID is required")
    private Long courseId;
}