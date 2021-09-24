package com.twitter.demo.Services;


import com.twitter.demo.DTO.CommunicationData;
import com.twitter.demo.DTO.ContactDTO;
import com.twitter.demo.DTO.InspectionDTO;
import com.twitter.demo.DTO.InspectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InspectionTaskService {

    @Autowired
    private RestService restService;

    public void inspect() {
        List<InspectionDTO> inspectionDTOList = fetchAllInspections();
        if(!CollectionUtils.isEmpty(inspectionDTOList)){
               String emailTemplate = getEmailContentTemplate();
               List<CommunicationData> communicationDataList = new ArrayList<>();
                for (InspectionDTO inspectionDTO : inspectionDTOList){
                    CommunicationData communicationData = new CommunicationData();
                    communicationData.setEmailContent(emailTemplate);
                    ContactDTO contactDTO = inspectionDTO.getContact();
                    if(null != contactDTO){
                        communicationData.setToEmailId(contactDTO.getEmail());
                        communicationData.setSubject("emailTemplate se subject nikal lo");
                        communicationData.setToPhoneNumber(contactDTO.getPhone());
                        communicationData.setEmailContent(emailTemplate);
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
    }

    private void executeWhatsappService(List<CommunicationData> communicationDataList) {
    }

    private void executeEmailService(List<CommunicationData> communicationDataList) {
    }

    private String getEmailContentTemplate() {
        return null;
    }
}
