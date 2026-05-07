package com.quan.cms.entity;

import com.quan.cms.entity.base.BaseEntity;
import com.quan.cms.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer durationHours;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
    @OneToMany(mappedBy = "course")
    private List<Review> reviews;
}
