package com.project.fpt.sfm.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Khắc Vỹ on 10/8/2015.
 */
@Service
public class ScheduledTasks {

    @Autowired
    TaskScheduler taskScheduler;
/*
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }*/
/*
    @PostConstruct
    public void init(){
        taskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
            }
        }, new Date(System.currentTimeMillis() + 60 * 1000));

       // taskScheduler.schedule()
    }*/



}
