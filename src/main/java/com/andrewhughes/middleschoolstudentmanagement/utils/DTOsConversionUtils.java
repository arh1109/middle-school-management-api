package com.andrewhughes.middleschoolstudentmanagement.utils;

import com.andrewhughes.middleschoolstudentmanagement.dtos.StudentDTO;
import com.andrewhughes.middleschoolstudentmanagement.dtos.StudentScheduleDTO;
import com.andrewhughes.middleschoolstudentmanagement.entities.ScheduleEntity;
import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DTOsConversionUtils {

    @Autowired
    private ModelMapper modelMapper;


    public StudentDTO convertStudentEntityToDTO(StudentEntity studentEntity) {
        StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
        return studentDTO;
    }

    public ScheduleEntity convertToEntity(StudentScheduleDTO scheduleDto) {

        ScheduleEntity schedule = modelMapper.map(scheduleDto, ScheduleEntity.class);
        return schedule;
    }

}
