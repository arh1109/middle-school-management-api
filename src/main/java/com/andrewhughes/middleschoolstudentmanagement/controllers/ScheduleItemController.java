package com.andrewhughes.middleschoolstudentmanagement.controllers;

import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleItemEntity;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.repositories.ScheduleItemRepository;
import com.andrewhughes.middleschoolstudentmanagement.services.ScheduleItemService;
import com.andrewhughes.middleschoolstudentmanagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/api/scheduleItems")
public class ScheduleItemController {

    @Autowired
    private ScheduleItemService scheduleItemService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<ScheduleItemEntity>> getAllStudents() {
        return ResponseEntity.ok(scheduleItemService.getAllScheduleItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleItemEntity> getAllStudents(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(scheduleItemService.getScheduleItemById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/addStudent")
    public ResponseEntity<ScheduleItemEntity> addStudentToScheduleByScheduleIDAndStudentID(
            @RequestParam(name="studentId") long studentId, @PathVariable("id") long scheduleID) throws Exception {
        return ResponseEntity.ok(
                scheduleItemService.addStudentToScheduleItemByScheduleItemID(studentId, scheduleID));
    }

    @PostMapping("/{id}/removeStudent")
    public ResponseEntity<ScheduleItemEntity> removeStudentToScheduleByScheduleIDAndStudentID(
            @RequestParam(name="studentId") long studentId, @PathVariable("id") long scheduleID) throws Exception {
        return ResponseEntity.ok(
                scheduleItemService.removeStudentToScheduleItemByScheduleItemID(studentId, scheduleID));
    }

    @PostMapping
    public ResponseEntity<ScheduleItemEntity> createStudent(@RequestBody ScheduleItemEntity scheduleItem) {

        return ResponseEntity.ok(scheduleItemService.createScheduleItem(scheduleItem));
    }

    @PutMapping
    public ResponseEntity<ScheduleItemEntity> updateScheduleItemById(@RequestBody ScheduleItemEntity scheduleItem) {
        return ResponseEntity.ok(scheduleItemService.updateScheduleItemById(scheduleItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleItemById(@PathVariable("id") long id) {
        if(scheduleItemService.deleteScheduleItemById(id)) {
            return ResponseEntity.ok("deleted successfully!");
        }
        return ResponseEntity.ok("schedule does not exist");
    }


}
