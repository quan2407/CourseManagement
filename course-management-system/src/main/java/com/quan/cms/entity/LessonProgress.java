package com.quan.cms.entity;

import com.quan.cms.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "lesson_progress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"enrollment_id", "lesson_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonProgress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Builder.Default
    private Boolean isCompleted = false;

    private LocalDateTime completedAt;

    private LocalDateTime lastAccessedAt;
}