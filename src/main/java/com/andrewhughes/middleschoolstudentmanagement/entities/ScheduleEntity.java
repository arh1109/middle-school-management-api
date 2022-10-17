package com.andrewhughes.middleschoolstudentmanagement.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="student_schedules")
public class ScheduleEntity {
    @Id
    @Column(name = "schedule_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable=false)
    private StudentEntity student;

    @Column(name="enrollment_period")
    private String enrollment_period;

//    @OneToMany(
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @JoinColumns(
//            @JoinColumn(name="id", referencedColumnName = "id")
//    )
//    private List<ScheduleItemEntity> scheduleItems;



}
