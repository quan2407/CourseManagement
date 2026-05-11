package com.quan.cms.service.impl;

import com.quan.cms.dto.request.CreateCourseRequest;
import com.quan.cms.dto.response.CourseResponse;
import com.quan.cms.entity.Course;
import com.quan.cms.entity.User;
import com.quan.cms.enums.CourseStatus;
import com.quan.cms.enums.Role;
import com.quan.cms.exception.BadRequestException;
import com.quan.cms.exception.ResourceNotFoundException;
import com.quan.cms.mapper.CourseMapper;
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
}