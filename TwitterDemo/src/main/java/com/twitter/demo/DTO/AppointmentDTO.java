package com.twitter.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {

    public String id;
    public String bookingId;
    public String leadId;
    public String date;
    public String time;
    public String inspectionPointCity;
    public String inspectionPointName;
    public Object rejectionReasons;
    public String bookingSource;
    public String createdAt;
    public Object createdByUserEmail;
    public String updatedAt;
    public Object updatedByUserEmail;
    public String placeId;
    public String status;
    public Object comments;
    public Object cancellationReason;
    public Object callStatus;
    public Object cancelledInStatus;
    public Object cancelledAt;
    public Object assignedTo;
    public Object assignedToName;
    public String type;
    public String service;
    public Object address;
    public Object purchaseCoordinator;
    public List<Object> participants;

}
