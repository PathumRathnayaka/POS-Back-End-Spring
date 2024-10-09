package org.example.springposnew.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/health")
public class HealthCheckController {
    @GetMapping
    public String healthTest(){
        return "Note controller is working";
    }
}
