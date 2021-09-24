package com.twitter.demo.scheduler;

import com.twitter.demo.Services.InspectionTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ScheduledTasks {

    private static final Logger log  = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    InspectionTaskService inspectionTaskService;

    @Scheduled(fixedRate = 60000)
    public void runCron()
    {
          inspectionTaskService.inspect();
    }
}
