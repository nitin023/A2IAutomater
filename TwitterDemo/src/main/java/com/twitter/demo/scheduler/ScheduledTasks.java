package com.twitter.demo.scheduler;

import com.twitter.demo.Services.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log  = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    BookingService bookingService;

    @Scheduled(fixedRate = 60000)
    public void runCron()
    {
          bookingService.inspect();
    }
}
