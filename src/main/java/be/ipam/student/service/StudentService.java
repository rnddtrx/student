package be.ipam.student.service;

import be.ipam.student.dto.StudentDTO;
import be.ipam.student.model.StudentEntity;
import be.ipam.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    //cRud

    public Optional<StudentEntity> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    //Crud

    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    //crUd

    public StudentEntity updateStudent(Long studentId, StudentEntity updatedStudent) {
        Optional<StudentEntity> existingStudentOptional = studentRepository.findById(studentId);
        if (existingStudentOptional.isPresent()) {
            StudentEntity existingStudent = existingStudentOptional.get();
            existingStudent.setName(updatedStudent.getName());
            return studentRepository.save(existingStudent);
        } else {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found.");
        }
    }

    //cruD

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
