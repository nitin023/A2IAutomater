package com.twitter.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "inspection_task")
public class InspectionTask {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private Integer appointmentId;
    private Integer customerId;
    private String status;
    private String taskType;
    private String targetUrl;
    private String callbackUrl;
    private Integer attemptCount;
    private String state ="Cancelled";


}
