package be.ipam.student.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class StudentDTO {
    private Long studentId;
    private String name;
    private String login;
    private String password;
    private Set<CourseDTO> courses;
}
