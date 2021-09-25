package com.a2i.demo.service.serviceImpl;

import com.a2i.demo.entity.InspectionTask;
import com.a2i.demo.repository.InspectionTaskRepository;
import com.a2i.demo.Constant.EmailTemplate;
import com.a2i.demo.DTO.CommunicationData;
import com.a2i.demo.utils.TemplateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
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
    public final static Logger logger = LogManager.getLogger(InspectionTaskService.class);

    public void processBooking() {
        try {
            String status = "Cancelled";
            DateTime currentDate = new DateTime();
            DateTime currentDatePlusOneDay = currentDate.plusDays(1);

            Timestamp ts = new Timestamp(currentDate.getMillis());
            Timestamp tsP = new Timestamp(currentDatePlusOneDay.getMillis());

            List<InspectionTask> inspectionTaskList = inspectionTaskRepository.findByAppointmentDateAndStatus(status, ts, tsP);
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            for (InspectionTask inspectionTask : inspectionTaskList) {
                Date appointmentDate = inspectionTask.getAppointmentDate();

                DateTime dayBeforeDate = new DateTime(appointmentDate).minusDays(1);
                Date adpDate = new Date(dayBeforeDate.getMillis());

                CommunicationData communicationData = new CommunicationData();
                String emailContent = TemplateUtils.getEmailTemplate(EmailTemplate.USER_INTERACTIVE_TEMPLATE);
                updateEmailMetaData(communicationData, emailContent);
                updateContactDetails(communicationData, inspectionTask);
                executorService.submit(() -> {
                    //adpDate.compareTo(new Date(currentDate.getMillis()))
                    if (true || adpDate.equals(currentDate) || appointmentDate.equals(currentDate)) {
                        //d-1 day
                        logger.info("email and sms is send to user: "+ communicationData.getToEmailId()
                                + " "+ communicationData.getToPhoneNumber());
                        emailService.sendEmailAndCreateTask(communicationData);
                        smsService.sendSMSAndCreateTask(communicationData);
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        communicationData.setName(inspectionTask.getName());
    }
}
