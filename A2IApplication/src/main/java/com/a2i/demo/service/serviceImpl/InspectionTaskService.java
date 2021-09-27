package com.a2i.demo.service.serviceImpl;

import com.a2i.demo.Constant.EmailTemplate;
import com.a2i.demo.DTO.AppointmentDTO;
import com.a2i.demo.DTO.CommunicationData;
import com.a2i.demo.entity.InspectionTask;
import com.a2i.demo.repository.InspectionTaskRepository;
import com.a2i.demo.utils.TemplateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public void processBooking() {
        try {
            String status = "Cancelled";
            SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String dateStr = smd.format(date);
            Timestamp ts = Timestamp.valueOf(dateStr+" 0:0:0.00");
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 1);
            Date datePlusOne = c.getTime();
            String datePlusOneStr = smd.format(datePlusOne);
            Timestamp tsP = Timestamp.valueOf(datePlusOneStr+" 0:0:0.00");
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
                updateAppointentDto(communicationData,inspectionTask);
                String currDate = convertDateToString(date);
                String bookingDate = convertDateToString(appointmentDate);
                String previousToBookingDate = convertDateToString(adpDate);
                executorService.submit(() -> {
                    if (bookingDate.equals(currDate) || previousToBookingDate.equals(currDate)) {
                        //d-1 day and d day
                        emailService.sendEmailAndCreateTask(communicationData);
                        smsService.sendSMSAndCreateTask(communicationData);
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }





    }

    private void updateAppointentDto(CommunicationData communicationData,InspectionTask inspectionTask) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setBookingId(inspectionTask.getAppointmentId());
        appointmentDTO.setDate(inspectionTask.getAppointmentDate().toString());
        communicationData.setAppointmentDTO(appointmentDTO);
    }

    private String convertDateToString(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    private void updateEmailMetaData(CommunicationData communicationData, String emailContent) {
        communicationData.setTemplateName(EmailTemplate.USER_INTERACTIVE_TEMPLATE);
        communicationData.setContent(emailContent);
        communicationData.setSubject("Inspection Reminder");
    }

    private void updateContactDetails(CommunicationData communicationData, InspectionTask inspectionTask) {
        communicationData.setToEmailId(inspectionTask.getEmailId());
        communicationData.setToPhoneNumber(inspectionTask.getPhoneNumber());
        communicationData.setName(inspectionTask.getName());
    }
}
