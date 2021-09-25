package com.a2i.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_action")
public class UserAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneno;
//    @Enumerated(EnumType.STRING)
    private String action;
    private String feedback;
    private String suggestion;
    private String appointmentId;

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneNo(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
