package com.springboot.demo.demoapp.rest;

import com.springboot.demo.demoapp.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final Coach myCoach;
    private final Coach anotherCoach;

    @Autowired
    public DemoController(
            @Qualifier("tennisCoach") Coach theCoach,
            @Qualifier("tennisCoach") Coach theAnotherCoach
    ) {
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    @GetMapping("/check")
    public String check() {
        return "Comparing beans: myCoach == anotherCoach, "+ (myCoach == anotherCoach);
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}