package be.ipam.student.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "name")
    private String name;


    //Default Fetch is Lazy
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Student_Course",
            joinColumns = @JoinColumn(name = "StudentId"),
            inverseJoinColumns = @JoinColumn(name = "CourseId")
    )
    //@Transient
    private Set<Course> courses = new HashSet<>();

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
