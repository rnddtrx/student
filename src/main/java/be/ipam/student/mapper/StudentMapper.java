package be.ipam.student.mapper;

import be.ipam.student.dto.CourseAndStudentsDTO;
import be.ipam.student.dto.CourseDTO;
import be.ipam.student.dto.StudentDTO;
import be.ipam.student.model.CourseEntity;
import be.ipam.student.model.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {
    public StudentDTO convertToDTO(StudentEntity studentEntity) {
        return StudentDTO.builder()
                .studentId(studentEntity.getStudentId())
                .name(studentEntity.getName())
                .login(studentEntity.getLogin())
                .courses(studentEntity.getCourses() != null ?
                            studentEntity.getCourses().stream().map(
                            courseEntity -> CourseDTO.builder()
                                .courseId(courseEntity.getCourseId())
                                .title(courseEntity.getTitle())
                                .build()
                            ).collect(Collectors.toSet())
                    : null)
                .build();
    }
    public StudentEntity convertToEntity(StudentDTO studentDTO) {
        return StudentEntity.builder()
                .studentId(studentDTO.getStudentId())
                .name(studentDTO.getName())
                .login(studentDTO.getLogin())
                .password(studentDTO.getPassword())
                .courses(studentDTO.getCourses() != null
                        ? studentDTO.getCourses().stream().map(
                        courseDTO -> CourseEntity.builder()
                                .courseId(courseDTO.getCourseId())
                                .title(courseDTO.getTitle())
                                .build()
                        ).collect(Collectors.toSet())
                        : null)
                .build();
    }
}
