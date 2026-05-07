package com.quan.cms.entity;

import com.quan.cms.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String title;

    private String contentUrl;

    @Column(columnDefinition = "TEXT")
    private String textContent;

    @Column(nullable = false)
    private Integer orderIndex;

    @Builder.Default
    private Boolean isPublished = false;
    @OneToMany(mappedBy = "lesson")
    private List<LessonProgress> lessonProgresses;
}
