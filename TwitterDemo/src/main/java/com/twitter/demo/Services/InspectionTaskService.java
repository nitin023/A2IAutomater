package com.twitter.demo.Services;


import com.twilio.twiml.voice.Sms;
import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.*;
import com.twitter.demo.Resources.Email.EmailImpl;
import com.twitter.demo.Resources.Email.IEmail;
import com.twitter.demo.sms.service.SmsSender;
import com.twitter.demo.utils.TemplateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InspectionTaskService {

    @Autowired
    private RestService restService;

    @Autowired
    private IEmail emailService;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private MessageDeliveryStatusRepository messageDeliveryStatusRepository;

    public void inspect() {
        List<InspectionDTO> inspectionDTOList = fetchAllInspections();
        if(!CollectionUtils.isEmpty(inspectionDTOList)){
               String templateName = EmailTemplate.BOOKING_INFORMATION;
               String emailTemplate = getEmailContentTemplate(templateName);
               List<CommunicationData> communicationDataList = new ArrayList<>();
                for (InspectionDTO inspectionDTO : inspectionDTOList){
                    CommunicationData communicationData = new CommunicationData();
                    communicationData.setTemplateName(templateName);
                    communicationData.setContent(emailTemplate);
                    communicationData.setSubject(EmailTemplate.BOOKING_INFORMATION);
                    ContactDTO contactDTO = inspectionDTO.getContact();
                    if(null != contactDTO){
                        communicationData.setToEmailId(contactDTO.getEmail());
                        communicationData.setToPhoneNumber(contactDTO.getPhone());
                        communicationData.setContent(emailTemplate);
                        communicationData.setName(contactDTO.firstname + " "+contactDTO.lastname);
                    }
                    List<AppointmentDTO> appointmentDTOList = inspectionDTO.getAppointments();
                    for(AppointmentDTO appointmentDTO:appointmentDTOList)
                    {
                            communicationData.setAppointmentDTO(appointmentDTO);
                            String bookingDate = appointmentDTO.getCreatedAt();
                            String appointmentDate = appointmentDTO.getDate();
                    }
                    communicationDataList.add(communicationData);
                }
        }
    }



    private List<InspectionDTO> fetchAllInspections() {
        List<InspectionDTO> inspectionDTOList = null;
        try {
            InspectionResponse inspectionResponse = restService.getInspectionData();
            if(null != inspectionResponse && null != inspectionResponse.getData() && null != inspectionResponse.getData().getCarLeadData()){
                inspectionDTOList =  inspectionResponse.getData().getCarLeadData().getInspectionDTOList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inspectionDTOList;
    }

    private void executeSMSService(CommunicationData communicationData) {
            if(communicationData.getToPhoneNumber() != null) {
                smsSender.sendSMS(communicationData);

        }
    }

    private void executeWhatsappService(CommunicationData communicationData) {
    }

    private void executeEmailService(CommunicationData communicationData) {
            if(communicationData.getToEmailId() != null) {
                String content = TemplateUtils.replaceContnetInTemplate(communicationData);
                communicationData.setContent(content);
                emailService.sendEmail(communicationData);
        }
    }

    private String getEmailContentTemplate(String templateName) {
        {
            return TemplateUtils.getEmailTemplate(templateName);
        }
    }

    private void processBooking(String appointmentDate,CommunicationData communicationData, String bookingDate) throws ParseException {
        DateTime apd = new DateTime(appointmentDate);
        DateTime booking = new DateTime(bookingDate);//new SimpleDateFormat("dd/MM/yyyy").parse(bookingDate);
        DateTime date =  new DateTime();
        String templateName="";
        if(booking.equals(date)) //tday
        {
            templateName = EmailTemplate.BOOKING_INFORMATION;
            communicationData.setTemplateName(templateName);
            communicationData.setSubject(EmailTemplate.BOOKING_INFORMATION);
            executeEmailService(communicationData);
            executeSMSService(communicationData);
            executeWhatsappService(communicationData);
        }
        if(apd.equals(date))
        {
            //send dday
             templateName = EmailTemplate.USER_INTERACTIVE_TEMPLATE;
             communicationData.setTemplateName(templateName);
             communicationData.setSubject("Inspection Reminder");
             executeEmailService(communicationData);
             executeSMSService(communicationData);
             executeWhatsappService(communicationData);
        }
       if(apd.minusDays(1).equals(date))
       {
           //d-1 day


       }

    }
}
