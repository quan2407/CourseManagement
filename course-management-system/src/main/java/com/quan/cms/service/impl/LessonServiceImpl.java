package com.quan.cms.service.impl;

import com.quan.cms.dto.request.CreateLessonRequest;
import com.quan.cms.dto.request.UpdateLessonPublishRequest;
import com.quan.cms.dto.request.UpdateLessonRequest;
import com.quan.cms.dto.response.LessonResponse;
import com.quan.cms.entity.Course;
import com.quan.cms.entity.Lesson;
import com.quan.cms.entity.User;
import com.quan.cms.enums.Role;
import org.springframework.security.access.AccessDeniedException;
import com.quan.cms.exception.ResourceNotFoundException;
import com.quan.cms.mapper.LessonMapper;
import com.quan.cms.repository.CourseRepository;
import com.quan.cms.repository.LessonRepository;
import com.quan.cms.repository.UserRepository;
import com.quan.cms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final LessonMapper lessonMapper;

    @Override
    public LessonResponse createLesson(
            Long courseId,
            CreateLessonRequest request,
            String username
    ) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found"
                        )
                );

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        boolean isAdmin =
                currentUser.getRole() == Role.ADMIN;

        boolean isCourseTeacher =
                course.getTeacher()
                        .getUserId()
                        .equals(currentUser.getUserId());

        if (!isAdmin && !isCourseTeacher) {

            throw new AccessDeniedException(
                    "You are not allowed to add lessons to this course"
            );
        }

        Lesson lesson = lessonMapper.toEntity(request);

        lesson.setCourse(course);

        lesson.setIsPublished(false);

        Lesson savedLesson =
                lessonRepository.save(lesson);

        return lessonMapper.toResponse(savedLesson);
    }

    @Override
    public List<LessonResponse> getLessonsByCourse(
            Long courseId
    ) {

        if (!courseRepository.existsById(courseId)) {

            throw new ResourceNotFoundException(
                    "Course not found"
            );
        }

        List<Lesson> lessons =
                lessonRepository
                        .findByCourseCourseIdAndIsPublishedTrue(
                                courseId
                        );

        return lessons.stream()
                .map(lessonMapper::toResponse)
                .toList();
    }

    @Override
    public LessonResponse updateLessonPublishStatus(
            Long lessonId,
            UpdateLessonPublishRequest request,
            String username
    ) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found"
                        )
                );

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        boolean isAdmin =
                currentUser.getRole() == Role.ADMIN;

        boolean isCourseTeacher =
                lesson.getCourse()
                        .getTeacher()
                        .getUserId()
                        .equals(currentUser.getUserId());

        if (!isAdmin && !isCourseTeacher) {

            throw new AccessDeniedException(
                    "You are not allowed to update this lesson"
            );
        }

        lesson.setIsPublished(
                request.getIsPublished()
        );

        Lesson updatedLesson =
                lessonRepository.save(lesson);

        return lessonMapper.toResponse(updatedLesson);
    }

    @Override
    public LessonResponse getLessonById(
            Long lessonId
    ) {

        Lesson lesson =
                lessonRepository
                        .findByLessonIdAndIsPublishedTrue(
                                lessonId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson not found"
                                )
                        );

        return lessonMapper.toResponse(lesson);
    }

    @Override
    public LessonResponse updateLesson(
            Long lessonId,
            UpdateLessonRequest request,
            String username
    ) {

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lesson not found"
                        )
                );

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        boolean isAdmin =
                currentUser.getRole() == Role.ADMIN;

        boolean isCourseTeacher =
                lesson.getCourse()
                        .getTeacher()
                        .getUserId()
                        .equals(currentUser.getUserId());

        if (!isAdmin && !isCourseTeacher) {

            throw new AccessDeniedException(
                    "You are not allowed to update this lesson"
            );
        }

        lessonMapper.updateLessonFromRequest(
                request,
                lesson
        );

        Lesson updatedLesson =
                lessonRepository.save(lesson);

        return lessonMapper.toResponse(updatedLesson);
    }
}