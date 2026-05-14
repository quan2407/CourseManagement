package com.quan.cms.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDetailResponse {

    private Long enrollmentId;

    private String courseTitle;

    private BigDecimal progressPercentage;

    private String status;

    private List<EnrollmentLessonResponse> lessons;
}