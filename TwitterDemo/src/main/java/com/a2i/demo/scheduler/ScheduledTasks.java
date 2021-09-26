package com.twitter.demo.scheduler;

import com.twitter.demo.service.serviceImpl.BookingService;
import com.twitter.demo.service.serviceImpl.InspectionTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    BookingService bookingService;

    @Autowired
    InspectionTaskService inspectionTaskService;

    @Scheduled(fixedRate = 360000)
    public void runBookingCron() {
        bookingService.inspect();
    }

    //@Scheduled(cron = "0 22 * * * ....")
    @Scheduled(fixedRate = 3600000, initialDelay = 10000)
    public void runInspectionTaskCron() {
        try {
            inspectionTaskService.processBooking();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
