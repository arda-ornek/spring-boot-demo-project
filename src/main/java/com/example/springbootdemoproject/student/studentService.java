package com.example.springbootdemoproject.student;

/* import java.time.LocalDate;
import java.time.Month; */
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class studentService {

    private final studentRepository studentRepository;

    @Autowired
    public studentService(com.example.springbootdemoproject.student.studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public List<Student> getStudents(){
		return studentRepository.findAll();
	}



    public void addNewStudent(Student student) {
        Optional <Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent())
            throw new IllegalStateException("email token");

        studentRepository.save(student);

        System.out.println(student);
    }



    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        
        studentRepository.deleteById(studentId);
    }


    @Transactional
    public void updateStudent(Long studentId,String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student does not exist"));

        if(name!=null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if(studentOptional.isPresent()){
                throw new IllegalStateException("email token");
            }
            
            student.setEmail(email);
        }
    }
}
