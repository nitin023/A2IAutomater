package com.a2i.demo.scheduler;

import com.a2i.demo.service.serviceImpl.BookingService;
import com.a2i.demo.service.serviceImpl.InspectionTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    BookingService bookingService;

    @Autowired
    InspectionTaskService inspectionTaskService;

    //@Scheduled(fixedRate = 60000)
    public void runBookingCron() {
        bookingService.inspect();
    }

    @Scheduled(cron = "0 2 2 * * *")
    @Scheduled(fixedRate = 60000)
    public void runInspectionTaskCron() {
        try {
            inspectionTaskService.processBooking();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
