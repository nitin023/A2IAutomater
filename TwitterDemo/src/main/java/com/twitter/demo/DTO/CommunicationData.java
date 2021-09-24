package com.twitter.demo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class CommunicationData {

    private String toEmailId;
    private String fromEmailId;
    private String subject;
    private String emailContent;
    private String toPhoneNumber;
    private String fromPhoneNumber;
    private String name;

}