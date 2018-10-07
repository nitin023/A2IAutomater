package com.twitter.demo.Services;

import com.twitter.demo.Resources.Email.EmailImpl;
import com.twitter.demo.modal.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void setSendMail(Email emailInfo)
    {
        EmailImpl emailImpl = new EmailImpl();
        emailImpl.sendEmail(emailInfo);
    }

}
