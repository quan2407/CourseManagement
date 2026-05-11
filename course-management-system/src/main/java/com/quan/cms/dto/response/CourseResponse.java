package com.quan.cms.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private Long courseId;

    private String title;

    private String description;

    private String teacherName;

    private BigDecimal price;

    private Integer durationHours;

    private String status;
}