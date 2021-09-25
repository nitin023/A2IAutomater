package com.a2i.demo.DTO;

import lombok.Data;

@Data
public class CommunicationData {

    private String toEmailId;
    private String fromEmailId;
    private String subject;
    private String content;
    private String toPhoneNumber;
    private String fromPhoneNumber;
    private String templateName;
    private String name;
    private AppointmentDTO appointmentDTO;
    private String state;
    private String status;

}
