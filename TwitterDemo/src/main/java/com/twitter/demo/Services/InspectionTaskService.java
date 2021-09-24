package com.twitter.demo.Services;


import com.twilio.twiml.voice.Sms;
import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.*;
import com.twitter.demo.Resources.Email.EmailImpl;
import com.twitter.demo.Resources.Email.IEmail;
import com.twitter.demo.sms.service.SmsSender;
import com.twitter.demo.utils.TemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InspectionTaskService {

    @Autowired
    private RestService restService;

    @Autowired
    private IEmail emailService;

    @Autowired
    private SmsSender smsSender;

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
                        communicationDataList.add(communicationData);
                    }
                }
               executeEmailService(communicationDataList);
               executeWhatsappService(communicationDataList);
               executeSMSService(communicationDataList);
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

    private void executeSMSService(List<CommunicationData> communicationDataList) {
        for(CommunicationData communicationData : communicationDataList) {
            if(communicationData.getToPhoneNumber() != null) {
                smsSender.sendSMS(communicationData);
            }
        }
    }

    private void executeWhatsappService(List<CommunicationData> communicationDataList) {
    }

    private void executeEmailService(List<CommunicationData> communicationDataList) {
        for(CommunicationData communicationData : communicationDataList) {
            if(communicationData.getToEmailId() != null) {
                String content = TemplateUtils.replaceContnetInTemplate(communicationData);
                communicationData.setContent(content);
                emailService.sendEmail(communicationData);
            }
        }
    }

    private String getEmailContentTemplate(String templateName) {
        {
            return TemplateUtils.getEmailTemplate(templateName);
        }
    }
}
