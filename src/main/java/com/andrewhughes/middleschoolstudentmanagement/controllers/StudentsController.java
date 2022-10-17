package com.andrewhughes.middleschoolstudentmanagement.controllers;

import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/api/students")
public class StudentsController {
    Logger logger = LoggerFactory.getLogger(StudentsController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getAllStudents(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(studentService.getStudentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student) {
        logger.info(student.toString());
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @PutMapping
    public ResponseEntity<StudentEntity> updateStudentById(@RequestBody StudentEntity student) {
        return ResponseEntity.ok(studentService.updateStudentById(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") long id) {
        if(studentService.deleteStudentById(id)) {
            return ResponseEntity.ok("deleted successfully!");
        }
        return ResponseEntity.ok("user does not exist");
    }

}
