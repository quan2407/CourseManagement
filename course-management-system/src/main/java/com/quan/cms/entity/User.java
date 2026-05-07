package com.quan.cms.entity;

import com.quan.cms.entity.base.BaseEntity;
import com.quan.cms.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder.Default
    private Boolean isActive = true;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "student")
    private List<Review> reviews;
}
