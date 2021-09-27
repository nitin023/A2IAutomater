package com.a2i.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO {

    public String id;
    public String country;
    public String locale;
    public Object salutation;
    public String firstname;
    public String lastname;
    public String email;
    public String phone;
    public String phone2;
    public String address;
    public Date createdAt;
    public Date updatedAt;
    public String smsMarketing;
    public String emailMarketing;
    public String createdByUserEmail;
    public String city;
    public String postcode;
    public String olxUserId;

}
