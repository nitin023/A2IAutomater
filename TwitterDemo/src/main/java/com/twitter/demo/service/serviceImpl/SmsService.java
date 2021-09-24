package com.twitter.demo.service.serviceImpl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.CommunicationData;
import com.twitter.demo.entity.InspectionTask;
import com.twitter.demo.repository.InspectionTaskRepository;
import com.twitter.demo.utils.TemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
                    //TODO :: Save Inspection Task In DB :: Sangita
                    InspectionTask inspectionTask = new InspectionTask();
                    inspectionTask.setEmailId(communicationData.getFromEmailId());
                    inspectionTask.setAppointmentId(communicationData.getAppointmentDTO().getBookingId());
                    Date date1;
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(communicationData.getAppointmentDTO().getDate());
                    inspectionTask.setAppointmentDate(date1);
                    inspectionTask.setFName(communicationData.getName());
                    inspectionTask.setPhoneNumber(communicationData.getFromPhoneNumber());
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
