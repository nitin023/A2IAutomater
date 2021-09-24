package com.twitter.demo.Services;


import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.*;
import com.twitter.demo.Resources.Email.IEmail;
import com.twitter.demo.sms.service.SmsService;
import com.twitter.demo.utils.TemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private RestService restService;

    @Autowired
    private IEmail emailService;

    @Autowired
    private SmsService smsService;

    public void inspect() {
        List<InspectionDTO> inspectionDTOList = fetchAllInspections();
        if(!CollectionUtils.isEmpty(inspectionDTOList)){
               String emailContent = TemplateUtils.getEmailTemplate(EmailTemplate.BOOKING_INFORMATION);
                for (InspectionDTO inspectionDTO : inspectionDTOList){
                    CommunicationData communicationData = new CommunicationData();
                    updateEmailMetaData(communicationData,emailContent);
                    updateContactDetails(communicationData,inspectionDTO);
                    updateAndProcessAppointment(communicationData,inspectionDTO);
                }
        }
    }

    private void updateAndProcessAppointment(CommunicationData communicationData, InspectionDTO inspectionDTO) {
        if(!CollectionUtils.isEmpty(inspectionDTO.getAppointments())){
            for(AppointmentDTO appointmentDTO:inspectionDTO.getAppointments())
            {
                communicationData.setAppointmentDTO(appointmentDTO);
                // Process Async Email/SMS/Whatsapp
                emailService.sendEmailAndCreateTask(communicationData);
                smsService.sendSMSAndCreateTask(communicationData);
            }
        }
    }

    private void updateEmailMetaData(CommunicationData communicationData, String emailContent) {
        communicationData.setTemplateName(EmailTemplate.BOOKING_INFORMATION);
        communicationData.setContent(emailContent);
        communicationData.setSubject(EmailTemplate.BOOKING_SUBJECT_CONFIRMATION);
    }

    private void updateContactDetails(CommunicationData communicationData, InspectionDTO inspectionDTO) {
        if(null != inspectionDTO.getContact()){
            ContactDTO contactDTO = inspectionDTO.getContact();
            communicationData.setToEmailId(contactDTO.getEmail());
            communicationData.setToPhoneNumber(contactDTO.getPhone());
            communicationData.setName(contactDTO.firstname + " "+contactDTO.lastname);
        }
    }


    /**
     * Get All appointment created in last 24 hour
     * @return
     */
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

}
