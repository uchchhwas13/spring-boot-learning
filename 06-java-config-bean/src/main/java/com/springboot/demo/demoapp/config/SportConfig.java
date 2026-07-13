package com.springboot.demo.demoapp.config;

import com.springboot.demo.demoapp.common.Coach;
import com.springboot.demo.demoapp.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SportConfig {
    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
