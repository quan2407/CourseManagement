package com.quan.cms.service.impl;

import com.quan.cms.dto.request.CreateCourseRequest;
import com.quan.cms.dto.request.UpdateCourseRequest;
import com.quan.cms.dto.request.UpdateCourseStatusRequest;
import com.quan.cms.dto.response.CourseDetailResponse;
import com.quan.cms.dto.response.CourseResponse;
import com.quan.cms.dto.response.LessonResponse;
import com.quan.cms.entity.Course;
import com.quan.cms.entity.Lesson;
import com.quan.cms.entity.User;
import com.quan.cms.enums.CourseStatus;
import com.quan.cms.enums.Role;
import com.quan.cms.exception.BadRequestException;
import com.quan.cms.exception.ResourceNotFoundException;
import com.quan.cms.mapper.CourseMapper;
import com.quan.cms.mapper.LessonMapper;
import com.quan.cms.repository.CourseRepository;
import com.quan.cms.repository.UserRepository;
import com.quan.cms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final CourseMapper courseMapper;

    private final LessonMapper lessonMapper;
    @Override
    public CourseResponse createCourse(
            CreateCourseRequest request
    ) {

        User teacher = userRepository.findById(
                        request.getTeacherId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found"
                        )
                );

        if (teacher.getRole() != Role.TEACHER) {

            throw new BadRequestException(
                    "Assigned user is not a teacher"
            );
        }

        Course course = courseMapper.toEntity(request);

        course.setTeacher(teacher);

        course.setStatus(CourseStatus.DRAFT);

        Course savedCourse =
                courseRepository.save(course);

        return courseMapper.toResponse(savedCourse);
    }

    @Override
    public List<CourseResponse> getAllCourses(
            CourseStatus status
    ) {

        List<Course> courses;

        if (status != null) {

            courses = courseRepository.findByStatus(status);

        } else {

            courses = courseRepository.findAll();
        }

        return courses.stream()
                .map(courseMapper::toResponse)
                .toList();
    }

    @Override
    public CourseDetailResponse getCourseById(
            Long courseId
    ) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found"
                        )
                );

        List<LessonResponse> publishedLessons =
                course.getLessons()
                        .stream()
                        .filter(Lesson::getIsPublished)
                        .map(lessonMapper::toResponse)
                        .toList();

        CourseDetailResponse response =
                courseMapper.toDetailResponse(course);

        response.setLessons(publishedLessons);

        return response;
    }

    @Override
    public CourseResponse updateCourse(
            Long courseId,
            UpdateCourseRequest request
    ) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found"
                        )
                );

        User teacher = userRepository.findById(
                        request.getTeacherId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found"
                        )
                );

        if (teacher.getRole() != Role.TEACHER) {

            throw new BadRequestException(
                    "Assigned user is not a teacher"
            );
        }

        courseMapper.updateCourseFromRequest(
                request,
                course
        );

        course.setTeacher(teacher);

        Course updatedCourse =
                courseRepository.save(course);

        return courseMapper.toResponse(updatedCourse);
    }
    @Override
    public CourseResponse updateCourseStatus(
            Long courseId,
            UpdateCourseStatusRequest request
    ) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found"
                        )
                );

        course.setStatus(request.getStatus());

        Course updatedCourse =
                courseRepository.save(course);

        return courseMapper.toResponse(updatedCourse);
    }
    @Override
    public void deleteCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found"
                        )
                );

        courseRepository.delete(course);
    }
}