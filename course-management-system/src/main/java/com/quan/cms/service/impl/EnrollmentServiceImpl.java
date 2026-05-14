package com.quan.cms.service.impl;

import com.quan.cms.dto.request.CreateEnrollmentRequest;
import com.quan.cms.dto.response.EnrollmentResponse;
import com.quan.cms.entity.Course;
import com.quan.cms.entity.Enrollment;
import com.quan.cms.entity.User;
import com.quan.cms.enums.CourseStatus;
import com.quan.cms.enums.EnrollmentStatus;
import com.quan.cms.enums.Role;
import com.quan.cms.exception.BadRequestException;
import com.quan.cms.exception.ResourceNotFoundException;
import com.quan.cms.mapper.EnrollmentMapper;
import com.quan.cms.repository.CourseRepository;
import com.quan.cms.repository.EnrollmentRepository;
import com.quan.cms.repository.UserRepository;
import com.quan.cms.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl
        implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse enrollCourse(
            CreateEnrollmentRequest request,
            String username
    ) {

        User student = userRepository.findByUsername(
                        username
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        if (student.getRole() != Role.STUDENT) {

            throw new BadRequestException(
                    "Only students can enroll courses"
            );
        }

        Course course = courseRepository.findById(
                        request.getCourseId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found"
                        )
                );

        if (course.getStatus()
                != CourseStatus.PUBLISHED) {

            throw new BadRequestException(
                    "Cannot enroll unpublished course"
            );
        }

        boolean alreadyEnrolled =
                enrollmentRepository
                        .existsByStudentUserIdAndCourseCourseId(
                                student.getUserId(),
                                course.getCourseId()
                        );

        if (alreadyEnrolled) {

            throw new BadRequestException(
                    "You already enrolled this course"
            );
        }

        Enrollment enrollment =
                Enrollment.builder()
                        .student(student)
                        .course(course)
                        .enrollmentDate(LocalDateTime.now())
                        .status(EnrollmentStatus.ENROLLED)
                        .progressPercentage(BigDecimal.ZERO)
                        .build();

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(
                savedEnrollment
        );
    }
    @Override
    public List<EnrollmentResponse> getMyEnrollments(
            String username
    ) {

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentUsername(
                        username
                );

        return enrollments.stream()
                .map(enrollmentMapper::toResponse)
                .toList();
    }
}