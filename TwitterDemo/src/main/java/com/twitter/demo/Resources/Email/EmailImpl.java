package com.twitter.demo.Resources.Email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.twitter.demo.Constant.ApplicationConstants;
import com.twitter.demo.DTO.CommunicationData;
import org.springframework.stereotype.Service;

@Service
public class EmailImpl implements IEmail {

    public boolean sendEmail(CommunicationData communicationData) {

        boolean response = false;

        Properties props = getSMTPProperties();
        Session session = getSessionInfo(props);
        String emailContent = communicationData.getContent();

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ApplicationConstants.SENDER_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(communicationData.getToEmailId()));
            message.setSubject(communicationData.getSubject());
            message.setContent(emailContent, "text/html");
            Transport.send(message);
            System.out.println("send success");
        } catch (MessagingException mexp) {
            mexp.printStackTrace();
        }
        return response;
    }

    public Properties getSMTPProperties() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        return props;
    }

    public Session getSessionInfo(Properties props) {
        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ApplicationConstants.SENDER_NAME, ApplicationConstants.SENDER_PASSWORD);
            }
        });
    }


}
