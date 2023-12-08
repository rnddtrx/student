package be.ipam.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    //Default Fetch is Lazy
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Student_Course",
            joinColumns = @JoinColumn(name = "StudentId"),
            inverseJoinColumns = @JoinColumn(name = "CourseId")
    )
    //@Transient
    private Set<CourseEntity> courses = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Student_Role",
            joinColumns = @JoinColumn(name = "StudentId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId")
    )
    private Set<RoleEntity> roles = new HashSet<>();


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

    public Set<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseEntity> courses) {
        this.courses = courses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
