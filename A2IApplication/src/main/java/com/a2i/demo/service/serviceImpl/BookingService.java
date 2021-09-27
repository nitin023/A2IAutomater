package com.a2i.demo.service.serviceImpl;


import com.a2i.demo.Constant.EmailTemplate;
import com.a2i.demo.DTO.AppointmentDTO;
import com.a2i.demo.DTO.CommunicationData;
import com.a2i.demo.DTO.ContactDTO;
import com.a2i.demo.DTO.InspectionDTO;
import com.a2i.demo.DTO.InspectionResponse;
import com.a2i.demo.enums.StateEnum;
import com.a2i.demo.enums.StatusEnum;
import com.a2i.demo.service.EmailService;
import com.a2i.demo.service.RestService;
import com.a2i.demo.utils.TemplateUtils;
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
    private EmailService emailService;

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
                    communicationData.setState(StateEnum.TRANSACTIONAL.name());
                    communicationData.setStatus(StatusEnum.IN_PROGRESS.name());
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
