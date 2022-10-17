package com.andrewhughes.middleschoolstudentmanagement.services;

import com.andrewhughes.middleschoolstudentmanagement.dtos.StudentScheduleDTO;
import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleEntity;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private StudentService studentService;
    @Autowired
    ScheduleRepository scheduleRepository;

    public List<ScheduleEntity> getAllSchedules() {

        return scheduleRepository.findAll();
    }

    public ScheduleEntity getScheduleById(long id) throws Exception {
        return scheduleRepository.findById(id).orElseThrow(() -> new Exception("Student Schedule with id: " +
                id + " does not exist"));

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ScheduleEntity createSchedule(long studentId, StudentScheduleDTO schedule) throws Exception {

        StudentEntity student = studentService.getStudentById(studentId);
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setEnrollment_period(schedule.getEnrollment_period());
        scheduleEntity.setStudent(student);

        return scheduleRepository.save(scheduleEntity);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteScheduleById(long id) {
        if(scheduleRepository.findById(id).isPresent()) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ScheduleEntity updateScheduleById(Long id, StudentScheduleDTO scheduleDTO) {
        String newEnrollment = scheduleDTO.getEnrollment_period();
        Optional<ScheduleEntity> scheduleEntity = scheduleRepository.findById(id);
        if(scheduleEntity.isPresent()) {
            scheduleEntity.get().setEnrollment_period(newEnrollment);
            return scheduleRepository.save(scheduleEntity.get());
        }
        return null;

    }


    public List<ScheduleEntity> getAllSchedulesForStudent(long id) {
        return scheduleRepository.findByScheduleId(id);
    }
}
