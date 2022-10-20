package com.andrewhughes.middleschoolstudentmanagement.services;

import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleItemEntity;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.repositories.ScheduleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ScheduleItemService {

    @Autowired
    private ScheduleItemRepository scheduleItemRepository;

    @Autowired
    private StudentService studentService;

    public List<ScheduleItemEntity> getAllScheduleItems() {

        return scheduleItemRepository.findAll();
    }

    public ScheduleItemEntity getScheduleItemById(long id) throws Exception {
        return scheduleItemRepository.findById(id).orElseThrow(() -> new Exception("User with id: " +
                id + " does not exist"));

    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ScheduleItemEntity createScheduleItem(ScheduleItemEntity student) {
        return scheduleItemRepository.save(student);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteScheduleItemById(long id) {
        if(scheduleItemRepository.findById(id).isPresent()) {
            scheduleItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ScheduleItemEntity updateScheduleItemById(ScheduleItemEntity scheduleItemEntity) {
        if(scheduleItemRepository.findById(scheduleItemEntity.getScheduleItemId()).isPresent()) {
            return scheduleItemRepository.save(scheduleItemEntity);
        }
        return null;

    }

    public ScheduleItemEntity addStudentToScheduleItemByScheduleItemID(long studentID, long id) throws Exception {
        ScheduleItemEntity scheduleItem = getScheduleItemById(id);
        scheduleItem.addStudent(studentService.getStudentById(studentID));
        scheduleItemRepository.save(scheduleItem);
        return scheduleItem;
    }

    public ScheduleItemEntity removeStudentToScheduleItemByScheduleItemID(long studentID, long id) throws Exception {
        ScheduleItemEntity scheduleItem = getScheduleItemById(id);
        scheduleItem.removeStudent(studentService.getStudentById(studentID));
        scheduleItemRepository.save(scheduleItem);
        return scheduleItem;
    }
}
