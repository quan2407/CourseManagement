package com.quan.cms.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDetailResponse {

    private Long courseId;

    private String title;

    private String description;

    private String teacherName;

    private BigDecimal price;

    private Integer durationHours;

    private String status;

    private List<LessonResponse> lessons;
}