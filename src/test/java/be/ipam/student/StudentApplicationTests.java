package be.ipam.student;

import be.ipam.student.model.CourseEntity;
import be.ipam.student.model.StudentEntity;
import be.ipam.student.service.CourseService;
import be.ipam.student.service.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class StudentApplicationTests {

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;

    @Test
    void contextLoads() {
        List<StudentEntity> stuList= studentService.getAllStudents();
        for (StudentEntity s: stuList) {
            System.out.println(s.getName());
        }
        List<CourseEntity> courseEntityList = courseService.getAllCourses();
        for(CourseEntity c: courseEntityList){
            System.out.println(c.getTitle());
        }

    }


    @Test
    @Transactional
    void studentCoursesTest(){
        StudentEntity myStudentEntity = studentService.getAllStudents().get(0);

        Set<CourseEntity> courseEntitySet = myStudentEntity.getCourses();

        for(CourseEntity c : courseEntitySet){
            System.out.println(myStudentEntity.getName() + " est inscrit en " + c.getTitle());
        }

    }

}
