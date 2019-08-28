package com.company.levelupservice.controller;

import com.company.levelupservice.exception.NotFoundException;
import com.company.levelupservice.model.LevelUp;
import com.company.levelupservice.service.LevelUpService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/levelup")
@RefreshScope
@EnableCircuitBreaker
public class LevelUpController {

    @Autowired
    LevelUpService service;

    @GetMapping("/points/{customerid}")
    @ResponseStatus(HttpStatus.OK)
    public int getTotalPoints(@PathVariable("customerid") int customerId) {
        return service.getTotalPoints(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody LevelUp levelUp) {
        return service.saveLevelUp(levelUp);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUp() {
        return service.getAllLevelUps();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUp(@PathVariable("id") int id) {
        LevelUp levelUp = service.getLevelUp(id);
        if (levelUp == null)
            throw new NotFoundException("Not found");
        return levelUp;
    }

    @GetMapping("/customerid/{customerid}")
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getLevelUpByCustomer(@PathVariable("customerid") int customerId) {
        List<LevelUp> levelUp = service.getLevelUpByCustomerId(customerId);
        if (levelUp == null)
            throw new NotFoundException("Not found");
        return levelUp;
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable("id") int id) {
        service.deleteLevelUp(id);
    }

    @PutMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody LevelUp levelUp, @PathVariable int id) {
        service.updateLevelUp(levelUp);
        if (levelUp == null)
            throw new NotFoundException("Not found");
    }
}
