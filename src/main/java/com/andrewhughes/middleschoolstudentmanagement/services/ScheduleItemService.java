package com.andrewhughes.middleschoolstudentmanagement.services;

import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleItemEntity;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import com.andrewhughes.middleschoolstudentmanagement.repositories.ScheduleItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleItemService {

    @Autowired
    private ScheduleItemRepository scheduleItemRepository;

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
        if(scheduleItemRepository.findById(scheduleItemEntity.getId()).isPresent()) {
            return scheduleItemRepository.save(scheduleItemEntity);
        }
        return null;

    }

}
