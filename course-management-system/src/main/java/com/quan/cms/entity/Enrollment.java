package com.quan.cms.entity;

import com.quan.cms.entity.base.BaseEntity;
import com.quan.cms.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"student_id", "course_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDateTime enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @Column(nullable = false)
    private BigDecimal progressPercentage;
    @OneToMany(mappedBy = "enrollment")
    private List<LessonProgress> lessonProgresses;
}