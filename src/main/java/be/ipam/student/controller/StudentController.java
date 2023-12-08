package be.ipam.student.controller;

import be.ipam.student.dto.StudentDTO;
import be.ipam.student.mapper.StudentMapper;
import be.ipam.student.model.StudentEntity;
import be.ipam.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;


    //@GetMapping("/withoutDTO")
    //public ResponseEntity<List<StudentEntity>> getAllStudentsWithoutDTO(){
    //    List<StudentEntity> students = studentService.getAllStudents().stream().toList();
    //    return ResponseEntity.ok(students);
    //}

    //GET all students
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        List<StudentDTO> students = studentService.getAllStudents().stream().map(studentMapper::convertToDTO).toList();
                //.map(s -> studentMapper.convertToDTO(s)).toList();

        return ResponseEntity.ok(students);
    }

    //GET student by id -- cRud
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        Optional<StudentEntity> student = studentService.getStudentById(studentId);
        //To avoid null

        //if (student.isPresent()) {
        //    return ResponseEntity.ok(studentMapper.convertToDTO(student.get()));
        //} else {
        //    return ResponseEntity.notFound().build();
        //}

        return student.map(studentEntity ->
                        ResponseEntity.ok(studentMapper.convertToDTO(studentEntity)))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    //POST Create student -- Crud
    @PostMapping("/student")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        StudentEntity createdStudent = studentService.createStudent(studentMapper.convertToEntity(studentDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.convertToDTO(createdStudent));
    }

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody StudentDTO studentDTO){
        StudentEntity createdStudent = studentService.createStudent(studentMapper.convertToEntity(studentDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.convertToDTO(createdStudent));
    }

    //PUT Update student -- crUd
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentEntity> updateStudent( @PathVariable Long studentId, @RequestBody StudentDTO studentToUpdate){
        Optional<StudentEntity> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isPresent()) {
            studentToUpdate.setStudentId(studentId);
            StudentEntity updatedStudent = studentService.updateStudent(studentId,studentMapper.convertToEntity(studentToUpdate));
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //PATCH Update student -- crUd
    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentEntity> patchStudent( @PathVariable Long studentId, @RequestBody StudentDTO studentToUpdate){
        Optional<StudentEntity> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isPresent()) {
            studentToUpdate.setStudentId(studentId);
            StudentEntity updatedStudent = studentService.patchStudent(studentId,studentMapper.convertToEntity(studentToUpdate));
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE Delete student -- cruD
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId){
        Optional<StudentEntity> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isPresent()) {
            studentService.deleteStudent(studentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
