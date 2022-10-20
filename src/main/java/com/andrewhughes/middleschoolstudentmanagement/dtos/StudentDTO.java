package com.andrewhughes.middleschoolstudentmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private long studentId;
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;


}
