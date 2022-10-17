package com.andrewhughes.middleschoolstudentmanagement.dtos;

import com.andrewhughes.middleschoolstudentmanagement.entities.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * For now, just has a String representing the Semester term and year
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentScheduleDTO {
    Long id;
    String enrollment_period;
    StudentEntity studentEntity;

}
