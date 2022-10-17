package com.andrewhughes.middleschoolstudentmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String first_name;
    private String last_name;
    private String address;
    private Date date_of_birth;


}
