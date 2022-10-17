package com.andrewhughes.middleschoolstudentmanagement.repositories;

import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findByScheduleId(long id);

}
