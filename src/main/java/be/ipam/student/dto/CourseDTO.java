package be.ipam.student.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CourseDTO {
    private Long courseId;
    private String title;
    @JsonIgnore
    private Set<StudentDTO> students;
}
