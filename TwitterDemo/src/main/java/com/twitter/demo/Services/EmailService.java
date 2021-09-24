package com.twitter.demo.Services;

import com.twitter.demo.DTO.CommunicationData;
import com.twitter.demo.Resources.Email.EmailImpl;
import com.twitter.demo.modal.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void setSendMail(CommunicationData communicationData)
    {
        EmailImpl emailImpl = new EmailImpl();
        emailImpl.sendEmail(communicationData);
    }

}
