package com.twitter.demo.Resources.Email;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.twitter.demo.Resources.Email.Constant.EmailConstants;
import com.twitter.demo.Resources.Email.Constant.EmailTemplate;
import com.twitter.demo.modal.Email;


public class EmailImpl implements IEmail {

    public boolean sendEmail(Email emailInfo) {

        boolean response = false;

        Properties props = getSMTPProperties();
        Session session = getSessionInfo(props);
        String emailContent = getEmailTemplate(emailInfo.getTemplate());

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailConstants.SENDER_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailInfo.getReciepent()));
            message.setSubject(emailInfo.getSubject());
            message.setContent(emailContent, "text/html");

            Transport.send(message);
            System.out.println("send sucess");
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
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConstants.SENDER_NAME, EmailConstants.SENDER_PASSWORD);
            }
        });
        return session;
    }

    public String getEmailTemplate(String template) {
        File file = new File("/home/nitin/IdeaProjects/TwitterDemo/src/main/java/com/twitter/demo/Resources/Email/Templates/" + getTemplateType(template));
        String html_trimmed = "";
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(file));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String tmp = s.trim();
                html_trimmed = html_trimmed.concat(tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return html_trimmed;
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
