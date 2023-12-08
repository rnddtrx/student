package be.ipam.student.service;

import be.ipam.student.dto.StudentDTO;
import be.ipam.student.model.StudentEntity;
import be.ipam.student.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    //cRud

    public Optional<StudentEntity> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    //Crud

    public StudentEntity createStudent(StudentEntity student) {
        //Hash Password
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
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

    public StudentEntity patchStudent(Long studentId, StudentEntity updatedStudent) {
        Optional<StudentEntity> existingStudentOptional = studentRepository.findById(studentId);
        if (existingStudentOptional.isPresent()) {
            StudentEntity existingStudent = existingStudentOptional.get();
            if (updatedStudent.getName() != null) {
                existingStudent.setName(updatedStudent.getName());
            }
            return studentRepository.save(existingStudent);
        } else {
            throw new EntityNotFoundException("Student with ID " + studentId + " not found.");
        }
    }

    //cruD

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    //find by login
    public Optional<StudentEntity> findByLogin(String login){
        return studentRepository.findByLogin(login);
    }

}
