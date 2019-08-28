package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.model.LevelUp;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class LevelUpService {

    LevelUpDao dao;

    @Autowired
    public LevelUpService(LevelUpDao dao) {
        this.dao = dao;
    }

    public int getTotalPoints(int customerId) {
        return dao.getTotalLevelUpPointsByCustomerId(customerId);
    }

    @Transactional
    public LevelUp saveLevelUp(LevelUp levelUp) {
        return dao.addLevelUp(levelUp);
    }

    public void updateLevelUp(LevelUp levelUp) {
        dao.updateLevelUp(levelUp);
    }

    public void deleteLevelUp(int id) {
        dao.deleteLevelUp(id);
    }

    public List<LevelUp> getAllLevelUps() {
        return dao.getAllLevelUps();
    }

    public LevelUp getLevelUp(int id) {
        System.out.println("This is from the circuit breaker");
        return dao.getLevelUp(id);
    }

    public List<LevelUp> getLevelUpByCustomerId(int customerId) {
        return dao.getLevelUpByCustomerId(customerId);
    }
}
