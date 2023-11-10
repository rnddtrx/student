package be.ipam.student.service;

import be.ipam.student.model.CourseEntity;
import be.ipam.student.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<CourseEntity> getAllCourses(){
        return courseRepository.findAll();
    }
}
