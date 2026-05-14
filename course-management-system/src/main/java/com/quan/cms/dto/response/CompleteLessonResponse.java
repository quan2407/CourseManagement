package com.quan.cms.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompleteLessonResponse {

    private Long enrollmentId;

    private BigDecimal progressPercentage;

    private String status;
}