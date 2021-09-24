package com.twitter.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "inspection_task")
@Data
public class InspectionTask {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String appointmentId;
    private Timestamp appointmentDate;
    private String emailId;
    private String phoneNumber;
    private String status;
    private String state;
    private String name;
    private String taskType;
    private Integer attemptCount;


}
