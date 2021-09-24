package com.twitter.demo.Resources.Email;

import com.twitter.demo.DTO.CommunicationData;
import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.mail.Session;

@Service
public interface IEmail {

    Session getSessionInfo(Properties props);

    Properties getSMTPProperties();

    boolean sendEmail(CommunicationData communicationData);

}
