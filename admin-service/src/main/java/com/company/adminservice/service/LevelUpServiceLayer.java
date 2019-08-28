package com.company.adminservice.service;

import com.company.adminservice.dto.LevelUp;
import com.company.adminservice.feign.LevelUpServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelUpServiceLayer {


    @Autowired
    private LevelUpServiceClient levelUpServiceClient;


    // CREATE LEVELUP
    public LevelUp createLevelUp(LevelUp levelUp) {
        return levelUpServiceClient.createLevelUp(levelUp);
    }


    // FIND LEVELUP BY ID
    public LevelUp findLevelUp(int id) {
        return levelUpServiceClient.getLevelUpById(id);
    }

    // FINDALL LEVELUP
    public List<LevelUp> findAllLevelUps() {
        return levelUpServiceClient.getAllLevelUps();
    }

    // UPDATE LEVELUP
    public void updateILevelUp(int id, LevelUp levelUp) {
        levelUpServiceClient.updateLevelUp(id, levelUp);
    }

    // DELETE LEVELUP BY ID
    public void deleteLevelUp(int id) {
        levelUpServiceClient.deleteLevelUp(id);
    }


    // FIND LEVELUP BY CUSTOMER ID
    public LevelUp findLevelUpByCustomerId(int id) {
        return levelUpServiceClient.getLevelUpByCustomerId(id);
    }










}
