package com.quan.cms.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentLessonResponse {

    private Long lessonId;

    private String title;

    private Boolean completed;
}