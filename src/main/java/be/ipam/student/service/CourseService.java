package be.ipam.student.service;

import be.ipam.student.model.CourseEntity;
import be.ipam.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    private final StudentService studentService;

    @Autowired
    public CourseService(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }


    public Optional<CourseEntity> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }


    public CourseEntity createCourse(CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }


    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public CourseEntity updateCourse(Long courseId, CourseEntity updatedCourse) {
        Optional<CourseEntity> existingCourseOptional = courseRepository.findById(courseId);
        if (existingCourseOptional.isPresent()) {
            CourseEntity existingCourse = existingCourseOptional.get();
            existingCourse.setTitle(updatedCourse.getTitle());
            return courseRepository.save(existingCourse);
        } else {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found.");
        }
    }

}
