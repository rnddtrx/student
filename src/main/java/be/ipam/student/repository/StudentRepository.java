package be.ipam.student.repository;

import be.ipam.student.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    Optional<StudentEntity> findByLogin(String login);










}
