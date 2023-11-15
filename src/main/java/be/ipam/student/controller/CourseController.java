package be.ipam.student.controller;

import be.ipam.student.dto.CourseAndStudentsDTO;
import be.ipam.student.dto.CourseDTO;
import be.ipam.student.mapper.CourseMapper;
import be.ipam.student.model.CourseEntity;
import be.ipam.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseMapper courseMapper;


    // GET all courses
    @GetMapping
    public ResponseEntity<List<CourseAndStudentsDTO>> getAllCourses() {
        List<CourseAndStudentsDTO> courses = courseService.getAllCourses().stream().map(courseMapper::convertToDTO).toList();
        return ResponseEntity.ok(courses);
    }

    // GET course by id
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseAndStudentsDTO> getCourseById(@PathVariable Long courseId) {
        Optional<CourseEntity> course = courseService.getCourseById(courseId);
        return course.map(courseEntity ->
                        ResponseEntity.ok(courseMapper.convertToDTO(courseEntity)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // POST Create course
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        //CourseEntity createdCourse = courseService.createCourse(courseService.convertToEntity(courseDTO));
        //return ResponseEntity.status(HttpStatus.CREATED).body(courseService.convertToDTO(createdCourse));
        return null;
    }

    // PUT Update course
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO courseToUpdate) {
        Optional<CourseEntity> existingCourse = courseService.getCourseById(courseId);
        if (existingCourse.isPresent()) {
            courseToUpdate.setCourseId(courseId);
            //CourseEntity updatedCourse = courseService.updateCourse(courseId, courseMapper.convertToEntity(courseToUpdate));
            //return ResponseEntity.ok(updatedCourse);
            return null;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE Delete course
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        Optional<CourseEntity> existingCourse = courseService.getCourseById(courseId);
        if (existingCourse.isPresent()) {
            courseService.deleteCourse(courseId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
