package com.andrewhughes.middleschoolstudentmanagement.controllers;

import com.andrewhughes.middleschoolstudentmanagement.dtos.StudentDTO;
import com.andrewhughes.middleschoolstudentmanagement.dtos.StudentScheduleDTO;
import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleEntity;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.services.ScheduleService;
import com.andrewhughes.middleschoolstudentmanagement.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Note that a Student must be created first in order to use these routes.
 * These endpoints can create a basic schedule container for ScheduleItems.
 * Should use ScheduleItemController endpoints to fill the Schedule up.
 */
@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScheduleService scheduleService;


    @GetMapping
    public ResponseEntity<List<ScheduleEntity>> getAllSchedules(@RequestParam(name="studentid", required=false) Long id) {
        if(id != null) {
            return ResponseEntity.ok(scheduleService.getAllSchedulesForStudent(id));
        }
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ScheduleEntity> getScheduleById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(scheduleService.getScheduleById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ScheduleEntity> createSchedule(@RequestParam(name="student_id") long studentId,
                                                        @RequestBody StudentScheduleDTO scheduleDTO) throws Exception {


        return ResponseEntity.ok(scheduleService.createSchedule(studentId, scheduleDTO));
    }

    /** Since Schedule onlly has 1 unique field now, this will update the semester term
     * Might need to change later to add ScheduleItems. In that case, I'd need to
     * add RequestBody w/ ScheduleItem List
     * */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntity> updateScheduleById(@PathVariable("id") long id,
                                                             @RequestBody StudentScheduleDTO schedule) {
        return ResponseEntity.ok(scheduleService.updateScheduleById(id, schedule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable("id") long id) {
        if(scheduleService.deleteScheduleById(id)) {
            return ResponseEntity.ok("deleted successfully!");
        }
        return ResponseEntity.ok("schedule does not exist");
    }



}

