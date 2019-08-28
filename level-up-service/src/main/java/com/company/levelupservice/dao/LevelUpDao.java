package com.company.levelupservice.dao;

import com.company.levelupservice.model.LevelUp;

import java.util.List;

public interface LevelUpDao {
    LevelUp addLevelUp(LevelUp levelUp);

    LevelUp getLevelUp(int id);

    List<LevelUp> getAllLevelUps();

    void updateLevelUp(LevelUp levelUp);

    void deleteLevelUp(int id);

    List<LevelUp> getLevelUpByCustomerId(int customerId);

    int getTotalLevelUpPointsByCustomerId(int customerId);
}
