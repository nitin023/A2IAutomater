package com.twitter.demo.Resources.Email;

import java.io.*;
import java.util.Properties;
import java.util.stream.Stream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.twitter.demo.Constant.ApplicationConstants;
import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.modal.Email;


public class EmailImpl implements IEmail {

    public boolean sendEmail(Email emailInfo) {

        boolean response = false;

        Properties props = getSMTPProperties();
        Session session = getSessionInfo(props);
        String emailContent = getEmailTemplate(emailInfo.getTemplate());

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ApplicationConstants.SENDER_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailInfo.getReciepent()));
            message.setSubject(emailInfo.getSubject());
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

    public String getEmailTemplate(String template) {
        StringBuilder htmlTemplate = new StringBuilder("");
        try {
            FileReader fileReader = new FileReader("src/main/resources/templates/Email/" + getTemplateType(template));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Stream<String> lines = bufferedReader.lines();

            lines.forEach(htmlTemplate::append);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return htmlTemplate.toString();
    }

    public String getTemplateType(String template) {
        String response = "";
        if (template != null && !template.isEmpty())
            switch (template) {
                case EmailTemplate.APP_VERIFY:
                    response = "AccountVerification.html";
                    break;
            }
        return response;

    }
}
