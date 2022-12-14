package com.andrewhughes.middleschoolstudentmanagement.services;

import com.andrewhughes.middleschoolstudentmanagement.dtos.StudentDTO;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentEntity> getAllStudents() {

        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(long id) throws Exception {
        return studentRepository.findById(id).orElseThrow(() -> new Exception("User with id: " +
                id + " does not exist"));

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StudentEntity createStudent(StudentEntity student) {
        return studentRepository.save(student);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteStudentById(long id) {
        if(studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public StudentEntity updateStudentByDTO(StudentDTO student) {
        if(studentRepository.findById(student.getStudentId()).isPresent()) {
            StudentEntity studentEntity = studentRepository.findById(student.getStudentId()).get();
            studentEntity.setAddress(student.getAddress());
            studentEntity.setFirstName(student.getFirstName());
            studentEntity.setLastName(student.getLastName());
            studentEntity.setDateOfBirth(student.getDateOfBirth());
            return studentRepository.save(studentEntity);
        }
        return null;

    }
}
