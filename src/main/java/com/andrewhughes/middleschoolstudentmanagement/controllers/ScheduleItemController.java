package com.andrewhughes.middleschoolstudentmanagement.controllers;

import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleItemEntity;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.repositories.ScheduleItemRepository;
import com.andrewhughes.middleschoolstudentmanagement.services.ScheduleItemService;
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
