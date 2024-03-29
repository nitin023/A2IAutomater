package com.a2i.demo.service.serviceImpl;

import com.a2i.demo.DTO.CommunicationData;
import com.a2i.demo.entity.InspectionTask;
import com.a2i.demo.repository.InspectionTaskRepository;
import com.a2i.demo.utils.TemplateUtils;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class SmsService {

    @Autowired
    InspectionTaskRepository inspectionTaskRepository;

    public static final String ACCOUNT_SID = "AC38c43e7eced8d9099d8a9677156066d8";
    public static final String AUTH_TOKEN = "2fa915a45d664917c468c65a8f8867cb";
    public static final String from_number = "+13467014948";


    public void sendSMSAndCreateTask(CommunicationData communicationData) {
        try {

            if (communicationData.getToPhoneNumber() != null) {
                if (sendSMS(communicationData)) {
                    InspectionTask inspectionTask = inspectionTaskRepository.findByAppointmentId(communicationData.getAppointmentDTO().getBookingId());
                    if (inspectionTask == null) {
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
        } catch (Exception ex) {
            log.warn("Trial version Twillio Auth warning.");
        }
    }

    public boolean sendSMS(CommunicationData communicationData) {
        boolean isSMSSent = false;
        try {
            String str = TemplateUtils.readFile(communicationData.getTemplateName());
            communicationData.setContent(str);
            String content = TemplateUtils.replaceContnetInTemplate(communicationData);
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message.creator(
                    new com.twilio.type.PhoneNumber(communicationData.getToPhoneNumber()),
                    new com.twilio.type.PhoneNumber(from_number),
                    content).create();
            isSMSSent = true;
        } catch (Exception exception) {
            log.warn("Trial version Twillio Auth warning.");
        }
        return isSMSSent;


    }


}
