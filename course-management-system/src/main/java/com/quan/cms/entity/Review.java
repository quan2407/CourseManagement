package com.quan.cms.entity;

import com.quan.cms.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "reviews",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"course_id", "student_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Column(nullable = false)
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;
}