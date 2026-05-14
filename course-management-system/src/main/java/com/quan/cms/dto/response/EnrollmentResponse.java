package com.quan.cms.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {

    private Long enrollmentId;

    private String studentName;

    private String courseTitle;

    private Integer progressPercent;
}