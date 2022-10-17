package com.andrewhughes.middleschoolstudentmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="students")
public class StudentEntity {

    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "date_of_birth", nullable = false)
    private Date date_of_birth;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy="student")
    private Set<ScheduleEntity> schedules;




}
