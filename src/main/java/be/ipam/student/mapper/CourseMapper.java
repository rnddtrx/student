package be.ipam.student.mapper;

import be.ipam.student.dto.CourseAndStudentsDTO;
import be.ipam.student.dto.CourseDTO;
import be.ipam.student.dto.StudentDTO;
import be.ipam.student.model.CourseEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class CourseMapper {
    public CourseAndStudentsDTO convertToDTO(CourseEntity courseEntity) {

        return CourseAndStudentsDTO.builder()
                .courseId(courseEntity.getCourseId())
                .title(courseEntity.getTitle())
                .students(courseEntity.getStudents().stream().map(
                        studentEntity -> StudentDTO.builder()
                                .studentId(studentEntity.getStudentId())
                                .name(studentEntity.getName())
                                .build()
                ).collect(Collectors.toSet()))
                .build();
    }

    public CourseEntity convertToEntity(CourseDTO courseDTO) {
        return CourseEntity.builder()
                .courseId(courseDTO.getCourseId())
                .title(courseDTO.getTitle())
                .students(courseDTO.getStudents().stream().map(
                        studentDTO -> be.ipam.student.model.StudentEntity.builder()
                                .studentId(studentDTO.getStudentId())
                                .name(studentDTO.getName())
                                .build()
                ).collect(Collectors.toSet()))
                .build();
    }
}
