package com.quan.cms.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopCourseResponse {

    private Long courseId;

    private String courseTitle;

    private Long enrollmentCount;
}