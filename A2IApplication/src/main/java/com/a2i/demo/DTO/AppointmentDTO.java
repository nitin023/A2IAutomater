package com.a2i.demo.DTO;

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
    public String rejectionReasons;
    public String bookingSource;
    public String createdAt;
    public String createdByUserEmail;
    public String updatedAt;
    public String updatedByUserEmail;
    public String placeId;
    public String status;
    public String comments;
    public String cancellationReason;
    public String callStatus;
    public String cancelledInStatus;
    public String cancelledAt;
    public String assignedTo;
    public String assignedToName;
    public String type;
    public String service;
    public String address;
    public String purchaseCoordinator;
    public List<String> participants;

}
