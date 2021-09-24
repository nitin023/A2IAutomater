package com.twitter.demo.service.serviceImpl;

import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.CommunicationData;
import com.twitter.demo.entity.InspectionTask;
import com.twitter.demo.repository.InspectionTaskRepository;
import com.twitter.demo.utils.TemplateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class InspectionTaskService {

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private InspectionTaskRepository inspectionTaskRepository;

    private void processBooking() throws ParseException {
        //TODO:: Nitin -> Fetch Data from DB :: where appointmentDate <= currentDate &&  appointmentDate == currentDate+1 and status != Cancelled and attemptcount is less than maxLimit
        String status = "Cancelled";
        Date currentDate = new Date();
        List<InspectionTask> inspectionTaskList =  inspectionTaskRepository.findByAppointmentDateAndStatus(status,currentDate);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(InspectionTask inspectionTask : inspectionTaskList){
            String appointmentDate = inspectionTask.getAppointmentDate();
            DateTime apd = new DateTime(appointmentDate);
            CommunicationData communicationData = new CommunicationData();
            String emailContent = TemplateUtils.getEmailTemplate(EmailTemplate.USER_INTERACTIVE_TEMPLATE);
            updateEmailMetaData(communicationData,emailContent);
            updateContactDetails(communicationData,inspectionTask);
            executorService.submit(() -> {
                if(apd.minusDays(1).equals(currentDate) || apd.equals(currentDate))
                {
                    //d-1 day
                    emailService.sendEmailAndCreateTask(communicationData);
                    smsService.sendSMSAndCreateTask(communicationData);
                }
            });
        }





    }

    private void updateEmailMetaData(CommunicationData communicationData, String emailContent) {
        communicationData.setTemplateName(EmailTemplate.BOOKING_INFORMATION);
        communicationData.setContent(emailContent);
        communicationData.setSubject("Inspection Reminder");
    }

    private void updateContactDetails(CommunicationData communicationData, InspectionTask inspectionTask) {
            communicationData.setToEmailId(inspectionTask.getEmailId());
            communicationData.setToPhoneNumber(inspectionTask.getPhoneNumber());
            communicationData.setName(inspectionTask.getFName() + " "+inspectionTask.getLName());
    }
}
