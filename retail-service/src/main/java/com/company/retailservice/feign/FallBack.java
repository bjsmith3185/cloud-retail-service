package com.company.retailservice.feign;

import com.company.retailservice.dto.LevelUp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FallBack implements LevelUpServiceClient {
    @Override
    public int getTotalPoints(int customerId) {
        return 0;
    }

    @Override
    public LevelUp createLevelUp(LevelUp levelUp) {
        return null;
    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return null;
    }

    @Override
    public LevelUp getLevelUpById(int id) {
        return null;
    }

    @Override
    public void updateLevelUp(int id, LevelUp levelUp) {

    }

    @Override
    public void deleteLevelUp(int id) {

    }

    @Override
    public List<LevelUp> getLevelUpByCustomer(int customerId) {

                System.out.println("$$$$$$$$$$$  is this being called ???????????");

        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(customerId);
        levelUp.setPoints(0);
        levelUp.setMemberDate(LocalDate.now());

        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);

        return levelUpList;


    }

    //    public List<LevelUp> getLevelUpPointsWithOutService(int id) {
//
//        System.out.println("$$$$$$$$$$$  is this being called ???????????");
//
//        LevelUp levelUp = new LevelUp();
//        levelUp.setCustomerId(id);
//        levelUp.setPoints(0);
//        levelUp.setMemberDate(LocalDate.now());
//
//        List<LevelUp> levelUpList = new ArrayList<>();
//        levelUpList.add(levelUp);
//
//        return levelUpList;
//
//    }




}
