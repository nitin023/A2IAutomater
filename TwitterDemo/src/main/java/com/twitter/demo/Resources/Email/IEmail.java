package com.twitter.demo.Resources.Email;

import com.twitter.demo.modal.Email;
import java.util.Properties;
import javax.mail.Session;

public interface IEmail {

    Session getSessionInfo(Properties props);

    Properties getSMTPProperties();

    String getEmailTemplate(String template);

    String getTemplateType(String template);

    boolean sendEmail(Email emailInfo);

}
