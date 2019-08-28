package com.company.levelupservice.service;

import com.company.levelupservice.dao.LevelUpDao;
import com.company.levelupservice.dao.LevelUpDaoJdbcTemplateImpl;
import com.company.levelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class LevelUpServiceTest {

    LevelUpDao dao;
    LevelUpService service;

    @Before
    public void setUp() throws Exception {

        setUpLevelUpMock();

        service = new LevelUpService(dao);
    }

    private void setUpLevelUpMock() {
        dao = mock(LevelUpDaoJdbcTemplateImpl.class);

        LevelUp levelUp = new LevelUp();
        levelUp.setId(1);
        levelUp.setCustomerId(1);
        levelUp.setPoints(25);
        levelUp.setMemberDate(LocalDate.of(2009, 12, 12));

        LevelUp levelUp1 = new LevelUp();
        levelUp1.setCustomerId(1);
        levelUp1.setPoints(25);
        levelUp1.setMemberDate(LocalDate.of(2009, 12, 12));

        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);

        doReturn(levelUp).when(dao).addLevelUp(levelUp1);
        doReturn(levelUp).when(dao).getLevelUp(1);
        doReturn(levelUpList).when(dao).getAllLevelUps();
        doReturn(levelUp).when(dao).getLevelUpByCustomerId(1);
    }

    @Test
    public void saveFindFindAllLevelUp() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setMemberDate(LocalDate.of(2009, 12, 12));
        levelUp.setPoints(25);
        levelUp = service.saveLevelUp(levelUp);

        LevelUp levelUp1 = service.getLevelUp(levelUp.getId());
        List<LevelUp> levelUpList = service.getAllLevelUps();
        assertEquals(levelUpList.size(), 1);
        assertEquals(levelUp1, levelUp);

    }

    @Test
    public void getLevelUpByCustomerId() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setMemberDate(LocalDate.of(2009, 12, 12));
        levelUp.setPoints(25);
        levelUp = dao.addLevelUp(levelUp);
        List<LevelUp> levelUp1 = dao.getLevelUpByCustomerId(levelUp.getCustomerId());


        assertEquals(levelUp1.size(),
                1);
    }
}