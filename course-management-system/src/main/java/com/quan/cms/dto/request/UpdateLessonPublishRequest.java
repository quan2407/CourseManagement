package com.quan.cms.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLessonPublishRequest {

    @NotNull(message = "Publish status is required")
    private Boolean isPublished;
}