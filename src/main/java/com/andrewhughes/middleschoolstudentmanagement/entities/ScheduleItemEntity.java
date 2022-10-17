package com.andrewhughes.middleschoolstudentmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="schedule_item")
public class ScheduleItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    /**
     * Which time slot the schedule item will start. If a school has 8 blocks total in a day,
     * and each period_size has a minimum of 1. Then this value can range from 1 - 7.
     */
    @Column(name = "period_start", nullable = false)
    private int period_start;

    /**
        Period size represents the timelength of this schedule_item. For example, a school may
        have 8 blocks total in a day. This number can range from 1 - max blocks (so 8 in this case)
     */
    @Column(name = "period_size", nullable = false)
    private int period_size;

}
