package com.twitter.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    private String id;
    private String bookingId;
    private String leadId;
    private String date;
    private String time;
    private String inspectionPointCity;
    private String inspectionPointName;
    private String rejectionReasons;
    public String bookingSource;
    public Date createdAt;
    public String createdByUserEmail;
    public Date updatedAt;
    public String placeId;
    public String status;
    public String comments;
    public String cancellationReason;
    public String callStatus;
    public String cancelledInStatus;
    public String cancelledAt;
    public String assignedToName;
    public String type;
    public String service;
    public String address;
    public String purchaseCoordinator;

}
