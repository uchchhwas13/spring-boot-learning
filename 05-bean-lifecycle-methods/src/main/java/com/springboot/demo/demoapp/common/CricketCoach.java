package com.springboot.demo.demoapp.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{

    public CricketCoach() {
        System.out.println("In Constructor: "+getClass().getSimpleName());
    }
    //define our init method
    @PostConstruct
    public void doMyStartUpStaff() {
        System.out.println("In doMyStartUpStaff(): "+getClass().getSimpleName());
    }

    //define our destroy method
    @PreDestroy
    public void doMyCleanUpStaff() {
        System.out.println("In doMyCleanUpStaff(): "+getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes :)";
    }
}
