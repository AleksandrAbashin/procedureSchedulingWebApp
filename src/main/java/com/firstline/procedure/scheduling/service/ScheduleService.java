package com.firstline.procedure.scheduling.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public String scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 1000;
      /*  System.out.println(
                "Fixed rate task with one second initial delay - " + now);*/

        return new String("Fixed rate task with one second initial delay - " + now);
    }
}
