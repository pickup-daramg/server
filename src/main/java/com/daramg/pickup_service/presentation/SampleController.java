package com.daramg.pickup_service.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sample")
@RestController
public class SampleController {

    @GetMapping
    public String getSampleMessage(){
        return "람쥐~";
    }
}
