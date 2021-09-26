package com.a2i.demo.service.serviceImpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.a2i.demo.entity.InspectionTask;
import com.a2i.demo.repository.InspectionTaskRepository;
import com.a2i.demo.Constant.ApplicationConstants;
import com.a2i.demo.DTO.CommunicationData;
import com.a2i.demo.service.EmailService;
import com.a2i.demo.utils.TemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  @Autowired
  InspectionTaskRepository inspectionTaskRepository;

    //@Async("asyncExecutor")
    public void sendEmailAndCreateTask(CommunicationData communicationData) {
        try {
            if (communicationData.getToEmailId() != null) {
                communicationData.setContent(TemplateUtils.replaceContnetInTemplate(communicationData));
                if (sendEmail(communicationData)) {
                    InspectionTask inspectionTask = inspectionTaskRepository.findByAppointmentId(communicationData.getAppointmentDTO().getBookingId());
                    if(inspectionTask==null){
                        inspectionTask = new InspectionTask();
                    }
                    inspectionTask.setEmailId(communicationData.getToEmailId());
                    inspectionTask.setAppointmentId(communicationData.getAppointmentDTO().getBookingId());
                    SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = smd.parse(communicationData.getAppointmentDTO().getDate());
                    Timestamp ts = new Timestamp(parsedDate.getTime());
                    inspectionTask.setAppointmentDate(ts);
                    inspectionTask.setName(communicationData.getName());
                    inspectionTask.setPhoneNumber(communicationData.getToPhoneNumber());
                    inspectionTask.setStatus(communicationData.getStatus());
                    inspectionTask.setState(communicationData.getState());
                    inspectionTaskRepository.save(inspectionTask);
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

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
            response = true;
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

    @Override
    public Session getSessionInfo(Properties props) {
        return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ApplicationConstants.SENDER_NAME, ApplicationConstants.SENDER_PASSWORD);
            }
        });
    }


}
