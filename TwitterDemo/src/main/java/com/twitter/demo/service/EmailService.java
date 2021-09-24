package com.twitter.demo.service;

import com.twitter.demo.DTO.CommunicationData;
import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.mail.Session;

@Service
public interface EmailService {

    void sendEmailAndCreateTask(CommunicationData communicationData);

    Session getSessionInfo(Properties props);

    Properties getSMTPProperties();

    boolean sendEmail(CommunicationData communicationData);

}
