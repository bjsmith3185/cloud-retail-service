//package com.company.retailservice.controller;
//
//import com.company.retailservice.dto.LevelUp;
//import com.company.retailservice.service.RetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@EnableCircuitBreaker
//public class LevelUpController {
//    @Autowired
//    RetailService service;
//
//    @Bean
//    public RestTemplate rest(RestTemplateBuilder builder) {
//        return builder.build();
//    }
//
//    @RequestMapping(value = "/getLevelUp/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public LevelUp getLevelUp (@PathVariable("id") int id) {
//        return service.levelUpPoints(id);
//    }
//}
