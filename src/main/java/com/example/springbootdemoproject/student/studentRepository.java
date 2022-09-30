package com.example.springbootdemoproject.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<Student,Long>{
    Optional <Student> findStudentByEmail(String email);
}
