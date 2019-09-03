package com.company.adminservice.controller;

import com.company.adminservice.dto.Customer;
import com.company.adminservice.dto.LevelUp;
import com.company.adminservice.service.CustomerServiceLayer;
import com.company.adminservice.service.LevelUpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelUpController {

    @Autowired
    private LevelUpServiceLayer service;




    @RequestMapping(value = "/administration/levelup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody LevelUp levelUp) {
        levelUp = service.createLevelUp(levelUp);
        return levelUp;
    }

    @RequestMapping(value = "/administration/levelup", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() {
        List<LevelUp> levelUp = service.findAllLevelUps();
        return levelUp;
    }

    @RequestMapping(value = "/administration/levelup/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUpById(@PathVariable("id") int id) {
        LevelUp levelUp = service.findLevelUp(id);
        return levelUp;
    }

    @RequestMapping(value = "/administration/levelup/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLevelUp(@PathVariable("id") int id, @RequestBody LevelUp levelUp) {
        service.updateILevelUp(id, levelUp);
    }

    @RequestMapping(value = "/administration/levelup/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable(name = "id") int id) {
        service.deleteLevelUp(id);
    }




}
