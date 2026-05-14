package com.quan.cms.service.impl;

import com.quan.cms.dto.request.CreateEnrollmentRequest;
import com.quan.cms.dto.response.EnrollmentDetailResponse;
import com.quan.cms.dto.response.EnrollmentLessonResponse;
import com.quan.cms.dto.response.EnrollmentResponse;
import com.quan.cms.entity.Course;
import com.quan.cms.entity.Enrollment;
import com.quan.cms.entity.Lesson;
import com.quan.cms.entity.User;
import com.quan.cms.enums.CourseStatus;
import com.quan.cms.enums.EnrollmentStatus;
import com.quan.cms.enums.Role;
import com.quan.cms.exception.BadRequestException;
import com.quan.cms.exception.ResourceNotFoundException;
import com.quan.cms.mapper.EnrollmentMapper;
import com.quan.cms.repository.CourseRepository;
import com.quan.cms.repository.EnrollmentRepository;
import com.quan.cms.repository.LessonProgressRepository;
import com.quan.cms.repository.UserRepository;
import com.quan.cms.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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
    private final LessonProgressRepository lessonProgressRepository;
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
    @Override
    public EnrollmentDetailResponse getEnrollmentDetail(
            Long enrollmentId,
            String username
    ) {

        Enrollment enrollment =
                enrollmentRepository.findById(enrollmentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Enrollment not found"
                                )
                        );

        if (!enrollment.getStudent()
                .getUsername()
                .equals(username)) {

            throw new AccessDeniedException(
                    "You are not allowed to view this enrollment"
            );
        }

        List<EnrollmentLessonResponse> lessons =
                enrollment.getCourse()
                        .getLessons()
                        .stream()
                        .filter(Lesson::getIsPublished)
                        .map(lesson -> {

                            boolean completed =
                                    lessonProgressRepository
                                            .findByEnrollmentEnrollmentIdAndLessonLessonId(
                                                    enrollmentId,
                                                    lesson.getLessonId()
                                            )
                                            .isPresent();

                            return EnrollmentLessonResponse
                                    .builder()
                                    .lessonId(
                                            lesson.getLessonId()
                                    )
                                    .title(
                                            lesson.getTitle()
                                    )
                                    .completed(completed)
                                    .build();
                        })
                        .toList();

        return EnrollmentDetailResponse.builder()
                .enrollmentId(
                        enrollment.getEnrollmentId()
                )
                .courseTitle(
                        enrollment.getCourse().getTitle()
                )
                .progressPercentage(
                        enrollment.getProgressPercentage()
                )
                .status(
                        enrollment.getStatus().name()
                )
                .lessons(lessons)
                .build();
    }
}