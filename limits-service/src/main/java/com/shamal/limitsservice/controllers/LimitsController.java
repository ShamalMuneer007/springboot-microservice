package com.shamal.limitsservice.controllers;

import com.shamal.limitsservice.configurations.Configuration;
import com.shamal.limitsservice.pojos.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class LimitsController {
    private final Configuration configuration;

    public LimitsController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return  new Limits(configuration.getMinimum(),configuration.getMaximum());
    }
}
