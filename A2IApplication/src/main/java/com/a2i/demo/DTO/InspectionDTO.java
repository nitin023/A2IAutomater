package com.a2i.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InspectionDTO {

    public String id;
    public String country;
    public String make;
    public String model;
    public String leadSource;
    public String externalLeadId;
    public String serviceType;
    public String trim;
    public String plate;
    public String transmission;
    public Integer year;
    public Integer mileage;
    public Integer price;
    public Integer minPrice;
    public Integer maxPrice;
    public String responsible;
    public String responsibleName;
    public String status;
    public String rejectionReasons;
    public Date createdAt;
    public String createdByUserEmail;
    public Date updatedAt;
    public String updatedByUserEmail;
    public String discardedReason;
    public String referralCode;
    public String ownerName;
    public String ownerPhone;
    @JsonProperty("VINnumber")
    public Object vINnumber;
    public String inspectionType;
    public String selfInspection;
    public Integer selfInspectionPrice;
    public List<AppointmentDTO> appointments;
    public String adId;
    public LatestAppointmentDTO latestAppointment;
    public ContactDTO contact;


}
