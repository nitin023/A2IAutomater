package com.twitter.demo.modal;

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

    @Override
    public String toString() {
        return "UserAction:{" +
                "phoneNo='" + phoneno + '\'' +
                ", action=" + action +
                ", feedback='" + feedback + '\'' +
                ", suggestion='" + suggestion + '\'' +
                '}';
    }

}
