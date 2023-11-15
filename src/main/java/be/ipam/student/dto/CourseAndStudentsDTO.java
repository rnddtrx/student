package be.ipam.student.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Builder
public class CourseAndStudentsDTO {
    private Long courseId;
    private String title;
    private Set<StudentDTO> students;
}
