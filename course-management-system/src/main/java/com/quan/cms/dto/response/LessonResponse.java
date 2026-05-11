package com.quan.cms.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonResponse {

    private Long lessonId;

    private String title;

    private Integer orderIndex;

    private Boolean isPublished;
}