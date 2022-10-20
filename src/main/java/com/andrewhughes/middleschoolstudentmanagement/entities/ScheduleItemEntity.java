package com.andrewhughes.middleschoolstudentmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="schedule_item")
public class ScheduleItemEntity {

    @Id
    @Column(name = "schedule_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleItemId;



    /**
     * Which time slot the schedule item will start. If a school has 8 blocks total in a day,
     * and each period_size has a minimum of 1. Then this value can range from 1 - 7.
     */
    @Column(name = "period_start", nullable = false)
    private int periodStart;

    @Column(name = "enrollment_period", nullable = false)
    private String enrollmentPeriod;

    /**
        Period size represents the timelength of this schedule_item. For example, a school may
        have 8 blocks total in a day. This number can range from 1 - max blocks (so 8 in this case)
     */
    @Column(name = "period_size", nullable = false)
    private int periodSize;

    @ManyToMany(mappedBy = "scheduleItems")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<StudentEntity> students = new HashSet<>();


    public void addStudent(StudentEntity student) {
        this.students.add(student);
        student.getScheduleItems().add(this);
    }

    public void removeStudent(StudentEntity student) {
        this.students.remove(student);
        student.getScheduleItems().remove(this);
    }

}
