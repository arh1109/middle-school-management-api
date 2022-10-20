package com.andrewhughes.middleschoolstudentmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
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
    private Date dateOfBirth;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToMany
    @JoinTable(name = "student_schedules",
        joinColumns = { @JoinColumn(name = "student_id")},
        inverseJoinColumns = {@JoinColumn(name = "schedule_item_id")})
    Set<ScheduleItemEntity> scheduleItems = new HashSet<>();

    public void addScheduleItem(ScheduleItemEntity scheduleItem) {
        this.scheduleItems.add(scheduleItem);
        scheduleItem.getStudents().add(this);
    }

    public void removeScheduleItem(ScheduleItemEntity scheduleItem) {
        this.scheduleItems.remove(scheduleItem);
        scheduleItem.getStudents().remove(this);
    }


}
