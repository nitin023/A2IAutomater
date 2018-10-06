package com.twitter.demo.modal;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;


@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long userProfileId;


    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Size(max = 15)
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Email
    @Column(name = "email_id")
    private String emailId;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Calendar dateOfBirth;

    @Column(name = "is_email_approved")
    private boolean isEmailApproved;

    @Column(name = "address")
    private String address;


    @OneToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "fk_user_id" , nullable = false)
    private User user;

    public Long getUserProfileId() {
        return userProfileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmailId() {
        return emailId;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isEmailApproved() {
        return isEmailApproved;
    }

    public User getUser() {
        return user;
    }

    public String isAddress() {
        return address;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmailApproved(boolean emailApproved) {
        isEmailApproved = emailApproved;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserProfile() {

    }

    public UserProfile(String firstName, String middleName,
                       String lastName, String phone,
                       String emailId, Calendar dateOfBirth,
                       String address , boolean isEmailApproved) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.emailId = emailId;
        this.isEmailApproved = isEmailApproved;
        this.dateOfBirth = dateOfBirth;
        this.address = address;

    }


}
