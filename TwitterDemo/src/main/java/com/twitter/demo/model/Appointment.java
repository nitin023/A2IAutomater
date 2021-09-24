package com.twitter.demo.model;

import com.twitter.demo.entity.User;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInspectionPointCity() {
        return inspectionPointCity;
    }

    public void setInspectionPointCity(String inspectionPointCity) {
        this.inspectionPointCity = inspectionPointCity;
    }

    public String getInspectionPointName() {
        return inspectionPointName;
    }

    public void setInspectionPointName(String inspectionPointName) {
        this.inspectionPointName = inspectionPointName;
    }

    public String getRejectionReasons() {
        return rejectionReasons;
    }

    public void setRejectionReasons(String rejectionReasons) {
        this.rejectionReasons = rejectionReasons;
    }

    public String getBookingSource() {
        return bookingSource;
    }

    public void setBookingSource(String bookingSource) {
        this.bookingSource = bookingSource;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedByUserEmail() {
        return createdByUserEmail;
    }

    public void setCreatedByUserEmail(String createdByUserEmail) {
        this.createdByUserEmail = createdByUserEmail;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public String getCancelledInStatus() {
        return cancelledInStatus;
    }

    public void setCancelledInStatus(String cancelledInStatus) {
        this.cancelledInStatus = cancelledInStatus;
    }

    public String getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(String cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public String getAssignedToName() {
        return assignedToName;
    }

    public void setAssignedToName(String assignedToName) {
        this.assignedToName = assignedToName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPurchaseCoordinator() {
        return purchaseCoordinator;
    }

    public void setPurchaseCoordinator(String purchaseCoordinator) {
        this.purchaseCoordinator = purchaseCoordinator;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", leadId='" + leadId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", inspectionPointCity='" + inspectionPointCity + '\'' +
                ", inspectionPointName='" + inspectionPointName + '\'' +
                ", rejectionReasons='" + rejectionReasons + '\'' +
                ", bookingSource='" + bookingSource + '\'' +
                ", createdAt=" + createdAt +
                ", createdByUserEmail='" + createdByUserEmail + '\'' +
                ", updatedAt=" + updatedAt +
                ", placeId='" + placeId + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", cancellationReason='" + cancellationReason + '\'' +
                ", callStatus='" + callStatus + '\'' +
                ", cancelledInStatus='" + cancelledInStatus + '\'' +
                ", cancelledAt='" + cancelledAt + '\'' +
                ", assignedToName='" + assignedToName + '\'' +
                ", type='" + type + '\'' +
                ", service='" + service + '\'' +
                ", address='" + address + '\'' +
                ", purchaseCoordinator='" + purchaseCoordinator + '\'' +
                '}';
    }
}
