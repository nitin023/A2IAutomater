package com.a2i.demo.service.serviceImpl;

import com.a2i.demo.entity.InspectionTask;
import com.a2i.demo.repository.InspectionTaskRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.a2i.demo.DTO.CommunicationData;
import com.a2i.demo.utils.TemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;

@Service
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
                    Date date = new Date();
                    Timestamp ts = new Timestamp(date.getTime());
                    inspectionTask.setAppointmentDate(ts);
                    inspectionTask.setName(communicationData.getName());
                    inspectionTask.setPhoneNumber(communicationData.getToPhoneNumber());
                    inspectionTask.setStatus(communicationData.getStatus());
                    inspectionTask.setState(communicationData.getState());
                    inspectionTaskRepository.save(inspectionTask);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean sendSMS(CommunicationData communicationData) {
        //TODO :: Komal will update
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
            exception.printStackTrace();
        }
        return isSMSSent;


    }


}
