package com.quan.cms.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {

    private Long enrollmentId;

    private String studentName;

    private String courseTitle;

    private BigDecimal progressPercentage;
}